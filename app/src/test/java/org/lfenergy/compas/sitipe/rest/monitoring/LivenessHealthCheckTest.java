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
public class LivenessHealthCheckTest {

    private LivenessHealthCheck sut;

    @BeforeEach()
    public void setUp() {
        sut = new LivenessHealthCheck();
    }

    @Test
    public void itShouldReturnSystemLive() {
        var result = sut.call();

        assertEquals("System Live", result.getName());
        assertEquals(HealthCheckResponse.Status.UP, result.getStatus());
    }
}