package ch.bzz.autoverwaltung.service;

import ch.bzz.autoverwaltung.data.DataHandler;
import ch.bzz.autoverwaltung.model.Automarke;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
    public Response listAutomarke(
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
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String automarkeUUID
    ) {
        Automarke automarke = null;
        int httpStatus;

        try {
            UUID.fromString(automarkeUUID);
            automarke = DataHandler.readAutomarke(automarkeUUID);
            if (automarke.getAutomarke() != null) {
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
    /**
     * creates a new Automarke without automodell
     * @param automarke
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createAutomarke(
            @Valid @BeanParam Automarke automarke
    ) {
        int httpStatus = 200;
        automarke.setAutomarkeUUID(UUID.randomUUID().toString());
        DataHandler.saveAutomarke(automarke);

        Response response = Response
                .status(httpStatus)
                .entity("")
                .build();
        return response;
    }
    /**
     * updates the shoes
     * @param automarke
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateAutomarke(
            @Valid @BeanParam Automarke automarke
    ) {
        int httpStatus = 200;

        ;
        if (DataHandler.updateAutomarke(automarke)) {
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
     * deletes automarke
     *
     * @param automarkeUUID the uuid of the automarke to be deleted
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteAutomarke(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String automarkeUUID
    ) {
        int httpStatusSchuh;

        int errorcode = DataHandler.deleteAutomarke(automarkeUUID);
        if (errorcode == 0) httpStatusSchuh = 200;
        else if (errorcode == -1) httpStatusSchuh = 409;
        else httpStatusSchuh = 404;


        Response response = Response
                .status(httpStatusSchuh)
                .entity("")
                .build();
        return response;
    }






}
