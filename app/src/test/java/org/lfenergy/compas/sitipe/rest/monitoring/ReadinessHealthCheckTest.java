// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.rest.monitoring;

import static org.junit.jupiter.api.Assertions.*;

import org.eclipse.microprofile.health.HealthCheckResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReadinessHealthCheckTest {

    private ReadinessHealthCheck sut;

    @BeforeEach
    public void setUp() {
        sut = new ReadinessHealthCheck();
    }

    @Test
    public void itShouldReturnSystemReady() {
        var result = sut.call();

        assertEquals("System Ready", result.getName());
        assertEquals(HealthCheckResponse.Status.UP, result.getStatus());
    }
}