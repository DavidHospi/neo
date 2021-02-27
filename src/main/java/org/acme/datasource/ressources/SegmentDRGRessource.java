package org.acme.datasource.ressources;


import org.acme.datasource.service.SegmentDRGService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("segment-drg")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class SegmentDRGRessource {

    @Inject
    SegmentDRGService segmentDRGService;

    @GET
    @Path("most")
    public Response getSegmentDRGAvecPlusDeGare() {
        return Response.ok(this.segmentDRGService.getSegmentDRGAvecLePlusDeGare()).build();
    }
}
