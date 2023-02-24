// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.rest.v1;

import io.smallrye.common.annotation.Blocking;
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
@Path("/sitipe/v1")
public class CompasSitipeResource {
    private final CompasSitipeService compasSitipeService;

    @Inject
    public CompasSitipeResource(CompasSitipeService compasSitipeService) {
        this.compasSitipeService = compasSitipeService;
    }

    @GET
    @Blocking
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Uni<String> getHelloWorld() {
        return Uni.createFrom().item("Hello World!");
    }

}
