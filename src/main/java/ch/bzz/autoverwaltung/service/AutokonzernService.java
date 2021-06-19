package ch.bzz.autoverwaltung.service;

import ch.bzz.autoverwaltung.data.DataHandler;
import ch.bzz.autoverwaltung.model.Autokonzern;


import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
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

    /**
     * creates a new konzern without automodell
     *
     * @param autokonzern
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createJersey(
            @Valid @BeanParam Autokonzern autokonzern

    ) {
        int httpStatus = 200;
        autokonzern.setKonzernUUID(UUID.randomUUID().toString());
        DataHandler.saveAutokonzern(autokonzern);

        Response response = Response
                .status(httpStatus)
                .entity("")
                .build();
        return response;
    }
    /**
     * updates the autokonzern
     *
     * @param autokonzern
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateAutokonzern(
            @Valid @BeanParam Autokonzern autokonzern
    ) {
        int httpStatus = 200;

        if (DataHandler.updateAutokonzern(autokonzern)) {
            httpStatus = 200;
        } else {
            httpStatus = 404;
        }

        Response response = Response
                .status(httpStatus)
                .entity("")
                .build();
        return response;
    }

    /**
     * delet the autokonzern
     *
     * @param konzernUUID
     * @return Response
     */

    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteJersey(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String konzernUUID
    ) {
        int httpStatusJersey;

        int errorcode = DataHandler.deleteAutokonzern(konzernUUID);
        if (errorcode == 0) httpStatusJersey = 200;
        else if (errorcode == -1) httpStatusJersey = 409;
        else httpStatusJersey = 404;


        Response response = Response
                .status(httpStatusJersey)
                .entity("")
                .build();
        return response;
    }





}

