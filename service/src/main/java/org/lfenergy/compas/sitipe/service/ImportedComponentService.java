// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.service;

import org.lfenergy.compas.sitipe.data.entity.ImportedComponent;
import org.lfenergy.compas.sitipe.data.repository.ImportedComponentRepository;
import org.lfenergy.compas.sitipe.dto.ImportedComponentDTO;
import org.lfenergy.compas.sitipe.dto.ImportedDataDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.NotFoundException;
import java.io.*;
import java.util.List;
import java.util.zip.InflaterInputStream;

@ApplicationScoped
public class ImportedComponentService {

    private final ImportedComponentRepository importedComponentRepository;

    @Inject
    public ImportedComponentService(
        final ImportedComponentRepository importedComponentRepository
    ) {
        this.importedComponentRepository = importedComponentRepository;
    }

    @Transactional
    public List<ImportedComponentDTO> getByAccessId(final String accessId) {
        return importedComponentRepository.getByAccessId(accessId)
            .stream()
            .map(ImportedComponentDTO::new)
            .toList();
    }

    @Transactional
    public ImportedDataDTO getImportedComponentData(final Integer id) {
        final ImportedComponent importedComponent = this.getEntity(id);

        if (importedComponent == null) {
            throw new NotFoundException("Imported BT Component not found");
        }

        return new ImportedDataDTO(this.getData(importedComponent));
    }

    private String getData(final ImportedComponent importedComponent) {
        final ByteArrayInputStream bais = new ByteArrayInputStream(importedComponent.getData());
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final InflaterInputStream iis = new InflaterInputStream(bais);

        int data;
        int stopByte = -1;

        try {
            while (stopByte != (data = iis.read())) {
                baos.write(data);
            }
        } catch (IOException e) {
            throw new InternalServerErrorException(e);
        }

        return baos.toString();
    }

    @Transactional
    public ImportedComponent getEntity(final Integer id) {
        return importedComponentRepository.getById(id);
    }
}
