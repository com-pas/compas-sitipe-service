// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.rest.v2;

import io.smallrye.mutiny.Uni;
import org.lfenergy.compas.sitipe.service.CompasSitipeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// @Authenticated
@RequestScoped
@Path("/sitipe/v2")
public class CompasSitipeResource {
    private final CompasSitipeService compasSitipeService;

    @Inject
    public CompasSitipeResource(CompasSitipeService compasSitipeService) {
        this.compasSitipeService = compasSitipeService;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<String> getHelloWorld() {
        return Uni.createFrom().item("Hello World!");
    }

}
