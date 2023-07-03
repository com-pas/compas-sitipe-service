// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lfenergy.compas.sitipe.data.entity.ImportedComponent;
import org.lfenergy.compas.sitipe.data.repository.ImportedComponentRepository;
import org.lfenergy.compas.sitipe.dto.ImportedComponentDTO;
import org.lfenergy.compas.sitipe.dto.ImportedDataDTO;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;
import java.util.zip.DeflaterOutputStream;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImportedComponentServiceTest {


    @Mock
    private ImportedComponentRepository importedComponentRepository;

    private ImportedComponentService sut;

    @BeforeEach
    void setUp() {
        sut = new ImportedComponentService(importedComponentRepository);
    }

    @Test
    void itShouldReturnListOfImportedComponents() {
        final ImportedComponent importedComponent1 = new ImportedComponent();
        importedComponent1.setId(1);
        final ImportedComponent importedComponent2 = new ImportedComponent();
        importedComponent2.setId(2);

        final String accessId = UUID.randomUUID().toString();

        when(importedComponentRepository.getByAccessId(accessId))
            .thenReturn(asList(importedComponent1, importedComponent2));

        final List<ImportedComponentDTO> result = sut.getByAccessId(accessId);

        assertEquals(2, result.size());
        assertTrue(asList(importedComponent1.getId(), importedComponent2.getId()).contains(result.get(0).getId()));
        assertTrue(asList(importedComponent1.getId(), importedComponent2.getId()).contains(result.get(1).getId()));
    }

    @Test
    void itShouldReturnEmptyListWhenNoImportedComponentsFound() {
        final String accessId = UUID.randomUUID().toString();

        when(importedComponentRepository.getByAccessId(accessId))
            .thenReturn(emptyList());

        final List<ImportedComponentDTO> result = sut.getByAccessId(accessId);

        assertEquals(0, result.size());
    }

    @Test
    void itShouldGetById() throws IOException {
        final Integer id = 1;
        final String data = "TEST DATA";
        ImportedComponent importedComponent = new ImportedComponent();
        importedComponent.setId(id);
        importedComponent.setData(compress(data.getBytes(StandardCharsets.UTF_8)));

        when(importedComponentRepository.getById(id))
            .thenReturn(importedComponent);

        ImportedDataDTO result = sut.getImportedComponentData(id);

        assertEquals(data, result.getData());
    }

    @Test
    void itShouldGetByIdWithSpecialCharacters() throws IOException {
        final Integer id = 1;
        final String data = "<temperature><message>It's currently 5°C outside!</message><northpole> 1μ°C</northpole></temperatur>";
        ImportedComponent importedComponent = new ImportedComponent();
        importedComponent.setId(id);
        importedComponent.setData(compress(data.getBytes(StandardCharsets.UTF_8)));

        when(importedComponentRepository.getById(id))
                .thenReturn(importedComponent);

        ImportedDataDTO result = sut.getImportedComponentData(id);

        assertEquals(data, result.getData());
    }
    @Test
    void itShouldThrowErrorWhenImportedComponentNotFound() {
        when(importedComponentRepository.getById(any())).thenReturn(null);
        assertThrows(RuntimeException.class, () -> sut.getImportedComponentData(1));
    }

    private byte[] compress(byte[] bArray) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (DeflaterOutputStream dos = new DeflaterOutputStream(baos)) {
            dos.write(bArray);
        }
        return baos.toByteArray();
    }
}