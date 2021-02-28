package org.acme.datasource.ressources;

import org.acme.datasource.entity.Commune;
import org.acme.datasource.service.CommuneService;
import org.acme.datasource.service.GareService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("commune")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class CommuneRessource {

    @Inject
    CommuneService communeService;

    @GET
    @Path("most")
    public Response communes() {
        return Response.ok(this.communeService.getCommuneAvecLePlusDeGare()).build();
    }

    @GET
    @Path("most/{ville}")
    public Response communesVille(@PathParam String ville) {
        return Response.ok(communeService.getCommuneAvecLePlusDeGareByVille(ville)).build();
    }

    @GET
    @Path("most/objet")
    public Response communeOrderByNbObjet() {
        return Response.ok(communeService.getCommuneOrderByObjetTrouve()).build();
    }


}
