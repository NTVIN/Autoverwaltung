package ch.bzz.autoverwaltung.service;

import ch.bzz.autoverwaltung.data.DataHandler;
import ch.bzz.autoverwaltung.model.Automarke;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.UUID;

/**
 * provides services for the Automarke
 * <p>
 * Autoverwaltung
 *
 * @author Assvin
 */
@Path("automarke")
public class AutomarkeService {

    /**
     * produces a map of all automarken
     *
     * @return Response
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listPublishers(
    ) {
        Map<String, Automarke> autoMarkeMap = DataHandler.getAutoMarkeMap();
        Response response = Response
                .status(200)
                .entity(autoMarkeMap)
                .build();
        return response;
    }

    /**
     * reads a single automarke identified by the uuid
     *
     * @param automarkeUUID the UUID in the URL
     * @return Response
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAutoMarke(
            @QueryParam("uuid") String automarkeUUID
    ) {
        Automarke automarke = null;
        int httpStatus;

        try {
            UUID.fromString(automarkeUUID);
            automarke = DataHandler.readAutomarke(automarkeUUID);
            if (automarke.getPublisher() != null) {
                httpStatus = 200;
            } else {
                httpStatus = 404;
            }
        } catch (IllegalArgumentException argEx) {
            httpStatus = 400;
        }

        Response response = Response
                .status(httpStatus)
                .entity(automarke)
                .build();
        return response;
    }
}
