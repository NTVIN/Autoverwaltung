package ch.bzz.autoverwaltung.service;

import ch.bzz.autoverwaltung.data.DataHandler;
import ch.bzz.autoverwaltung.model.Autokonzern;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.UUID;

/**
 * provides services for the Autokonzern
 * <p>
 * Autoverwaltung
 *
 * @author Assvin
 */
@Path("autokonzern")
public class AutokonzernService {

    /**
     * produces a map of all automarken
     *
     * @return Response
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAutokonzern(
    ) {
        Map<String, Autokonzern> autokonzernMap = DataHandler.getAutokonzernMap();
        Response response = Response
                .status(200)
                .entity(autokonzernMap)
                .build();
        return response;
    }

    /**
     * reads a single autokonzern identified by the uuid
     *
     * @param konzernUUID the UUID in the URL
     * @return Response
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAutokonzern(
            @QueryParam("uuid") String konzernUUID
    ) {
        Autokonzern autokonzern = null;
        int httpStatus;

        try {
            UUID.fromString(konzernUUID);
            autokonzern = DataHandler.readAutokonzern(konzernUUID);
            if (autokonzern.getKonzern() != null) {
                httpStatus = 200;
            } else {
                httpStatus = 404;
            }
        } catch (IllegalArgumentException argEx) {
            httpStatus = 400;
        }

        Response response = Response
                .status(httpStatus)
                .entity(autokonzern)
                .build();
        return response;
    }
}

