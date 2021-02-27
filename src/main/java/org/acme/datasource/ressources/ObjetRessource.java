package org.acme.datasource.ressources;


import org.acme.datasource.service.ObjetService;
import org.acme.datasource.service.SegmentDRGService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("objet")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class ObjetRessource {

    @Inject
    ObjetService objetService;

    @GET
    @Path("most/type")
    public Response getObjetPerduLePlusParType() {
        return Response.ok(this.objetService).build();
    }
}
