// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.rest.v2;

import io.quarkus.test.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lfenergy.compas.sitipe.service.CompasSitipeService;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CompasSitipeResourceTest {

    CompasSitipeResource sut;

    @Mock
    CompasSitipeService service;

    @BeforeEach
    public void setUp() {
        sut = new CompasSitipeResource(service);
    }

    @Test
    public void itShouldReturnHelloWorld() {
        var result = sut.getHelloWorld();

        result.subscribe().with((item) -> {
            assertEquals("Hello World!", item);
        });
    }
}