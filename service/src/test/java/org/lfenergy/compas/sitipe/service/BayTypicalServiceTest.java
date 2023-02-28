// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lfenergy.compas.sitipe.SitipeProperties;
import org.lfenergy.compas.sitipe.data.entity.BayTypical;
import org.lfenergy.compas.sitipe.data.entity.SystemVersion;
import org.lfenergy.compas.sitipe.data.repository.BayTypicalRepository;
import org.lfenergy.compas.sitipe.data.repository.SystemVersionRepository;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BayTypicalServiceTest {

    @Mock
    private BayTypicalRepository bayTypicalRepository;

    @Mock
    private SystemVersionRepository systemVersionRepository;

    @Mock
    private SitipeProperties properties;
    private BayTypicalService sut;

    @BeforeEach
    public void setUp() {
        sut = new BayTypicalService(systemVersionRepository, bayTypicalRepository, properties);
    }

    @Test
    public void itShouldReturnBayTypicals() {
        var version = "1.00";
        var systemVersion = new SystemVersion();
        systemVersion.setAssignedBayTypicals("A,B");

        var bayTypical1 = new BayTypical();
        var bayTypical2 = new BayTypical();

        when(systemVersionRepository.findByVersion(version)).thenReturn(Collections.singletonList(systemVersion));
        when(properties.version()).thenReturn(version);
        when(bayTypicalRepository.findByAccessId("A")).thenReturn(Collections.singletonList(bayTypical1));
        when(bayTypicalRepository.findByAccessId("B")).thenReturn(Collections.singletonList(bayTypical2));

        var res = sut.getBayTypicals();

        assertEquals(2, res.size());
        assertTrue(res.containsAll(asList(bayTypical1, bayTypical2)));
    }

    @Test
    public void itShouldReturnMultipleBayTypicals() {
        var version = "1.00";
        var systemVersion = new SystemVersion();
        systemVersion.setAssignedBayTypicals("A,B");

        var bayTypical1 = new BayTypical();
        var bayTypical2 = new BayTypical();
        var bayTypical3 = new BayTypical();
        var bayTypical4 = new BayTypical();

        when(systemVersionRepository.findByVersion(version)).thenReturn(Collections.singletonList(systemVersion));
        when(properties.version()).thenReturn(version);
        when(bayTypicalRepository.findByAccessId("A")).thenReturn(asList(bayTypical1, bayTypical2));
        when(bayTypicalRepository.findByAccessId("B")).thenReturn(asList(bayTypical3, bayTypical4));

        var res = sut.getBayTypicals();

        assertEquals(4, res.size());
        assertTrue(res.containsAll(asList(bayTypical1, bayTypical2, bayTypical3, bayTypical4)));
    }

    @Test
    public void itShouldReturnEmptyListWhenVersionIsNotFound() {
        var version = "1.00";

        when(systemVersionRepository.findByVersion(version)).thenReturn(emptyList());
        when(properties.version()).thenReturn(version);

        var res = sut.getBayTypicals();

        assertEquals(0, res.size());
    }

    @Test
    public void itShouldReturnEmptyListWhenNoAssignedBayTypicals() {
        var version = "1.00";

        var systemVersion = new SystemVersion();
        systemVersion.setAssignedBayTypicals("A,B");

        when(systemVersionRepository.findByVersion(version)).thenReturn(singletonList(systemVersion));
        when(properties.version()).thenReturn(version);
        when(bayTypicalRepository.findByAccessId(any())).thenReturn(emptyList());

        var res = sut.getBayTypicals();

        assertEquals(0, res.size());
    }

    @Test
    public void itShouldReturnEmptyListWhenAssignedBayTypicalsIsNull() {
        var version = "1.00";

        var systemVersion = new SystemVersion();

        when(systemVersionRepository.findByVersion(version)).thenReturn(singletonList(systemVersion));
        when(properties.version()).thenReturn(version);

        var res = sut.getBayTypicals();

        assertEquals(0, res.size());
    }
}