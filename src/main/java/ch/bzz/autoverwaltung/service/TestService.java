package ch.bzz.autoverwaltung.service;

import ch.bzz.autoverwaltung.data.DataHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * service for testing
 * <p>
 * Bookshelf
 *
 * @author Assvin Shanmuganathan
 */
@Path("test")
public class TestService {

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response test() {

        return Response
                .status(200)
                .entity("Erfolgreich")
                .build();
    }

    @GET
    @Path("restore")
    @Produces(MediaType.TEXT_PLAIN)
    public Response restore() {

        DataHandler.restoreData();
        return Response
                .status(200)
                .entity("Daten wiederhergestellt")
                .build();
    }
}