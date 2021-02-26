package org.acme.datasource.ressources;

import org.acme.datasource.entity.Commune;
import org.acme.datasource.service.CommuneService;
import org.acme.datasource.service.GareService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("commune")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class CommuneRessource {

    @Inject
    CommuneService communeService;

    @GET
    @Path("most")
    public List<Commune> communes() {
        return this.communeService.getCommuneAvecLePlusDeGare();
    }

}
