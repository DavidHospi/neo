package org.acme.datasource.ressources;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.datasource.service.ImportServiceWIP;

@Path("import")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class ImportRessourceWIP {

    @Inject
    ImportServiceWIP importService;

    @GET
    @Path("")
    public Response importData() {
        return Response.ok(this.importService.importData()).build();
    }
    
}
