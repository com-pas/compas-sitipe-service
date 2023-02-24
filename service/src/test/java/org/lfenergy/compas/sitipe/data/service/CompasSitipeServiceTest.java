// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0
// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.data.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lfenergy.compas.sitipe.data.repository.SitipeRepository;
import org.lfenergy.compas.sitipe.service.CompasSitipeService;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CompasSitipeServiceTest {

    @Mock
    private SitipeRepository repository;

    private CompasSitipeService sut;

    @BeforeEach
    void beforeEach() {
        sut = new CompasSitipeService(repository);
    }

    @Test
    public void itShouldExist() {
        assertNotNull(sut);
    }
}