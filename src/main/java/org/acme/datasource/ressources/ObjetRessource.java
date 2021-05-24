package org.acme.datasource.ressources;


import org.acme.datasource.service.ObjetService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("objet")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class ObjetRessource {

    @Inject
    ObjetService objetService;

    @GET
    @Path("all")
    public Response getAll() {
        return Response.ok(this.objetService.getAll()).build();
    }

    @GET
    @Path("most/type")
    public Response getObjetPerduLePlusParType(
        @DefaultValue("false") @QueryParam("populationAdjusted") boolean populationAdjusted,
        @QueryParam("cp") Long codePostal, 
        @QueryParam("dpt") String departement) {
        if(codePostal != null && departement == null)
            return Response.ok(this.objetService.getObjetPerduLePlusParTypeParCommune(codePostal)).build();
        if(codePostal == null && departement != null)
            return Response.ok(this.objetService.getObjetPerduLePlusParTypeParDepartement(populationAdjusted, departement)).build();
        return Response.ok(this.objetService.getObjetPerduLePlusParType(populationAdjusted)).build();
    }

    @GET
    @Path("count/restitue")
    public Response getNbObjetRestitue() {
        return Response.ok(this.objetService.getNbObjetRestitue()).build();
    }

    @GET
    @Path("count/non-restitue")
    public Response getNbObjetNonRestitue() {
        return Response.ok(this.objetService.getNbObjetNonRestitue()).build();
    }
}
