package org.acme.datasource.ressources;


import org.acme.datasource.entity.Gare;
import org.acme.datasource.service.GareService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("gare")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class GareRessources {

    @Inject
    GareService gareService;

    @GET
    public List<Gare> gares() {return null;}

    @GET
    @Path("most/objet")
    public Response garesOrderByNbObjetTrouve() {
        return Response.ok(this.gareService.getGareOrderByNbObjetTrouve()).build();
    }
}
