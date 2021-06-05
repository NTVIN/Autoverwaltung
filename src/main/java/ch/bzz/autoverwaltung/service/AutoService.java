package ch.bzz.autoverwaltung.service;

import ch.bzz.autoverwaltung.data.DataHandler;
import ch.bzz.autoverwaltung.model.Automodell;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.UUID;

/**
 * provides services for the Autoverwaltung
 * <p>
 * Autoverwaltung
 *
 * @author Assvin Shanmuganathan
 */
@Path("auto")
public class AutoService {

    /**
     * produces a map of all autos
     *
     * @return Response
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAutos(
    ) {
        Map<String, Automodell> autoMap = DataHandler.getAutoMap();
        Response response = Response
                .status(200)
                .entity(autoMap)
                .build();
        return response;

    }

    /**
     * reads a single auto identified by the autoId
     *
     * @param autoUUID the autoUUID in the URL
     * @return Response
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAuto(
            @QueryParam("uuid") String autoUUID
    ) {
        Automodell automodell = null;
        int httpStatus;

        try {
            UUID autokey = UUID.fromString(autoUUID);
            automodell = DataHandler.readAuto(autoUUID);
            if (automodell.getAutomarke() != null) {
                httpStatus = 200;
            } else {
                httpStatus = 404;
            }
        } catch (IllegalArgumentException argEx) {
            httpStatus = 400;
        }

        Response response = Response
                .status(httpStatus)
                .entity(automodell)
                .build();
        return response;
    }
}
