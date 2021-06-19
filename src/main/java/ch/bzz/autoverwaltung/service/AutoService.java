package ch.bzz.autoverwaltung.service;

import ch.bzz.autoverwaltung.data.DataHandler;
import ch.bzz.autoverwaltung.model.Automodell;
import ch.bzz.autoverwaltung.model.Automarke;
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
            if (automodell.getModellbezeichnung() != null) {
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

    /**
     * creates a new player
     *
     * @param automarkeUUID automarke of automodell
     * @param konzernUUID   konzern of automodell
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createAuto(
            @Valid @BeanParam Automodell automodell,
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("automarkeUUID") String automarkeUUID,
            @FormParam("konzernUUID") String konzernUUID
    ) {
        int httpStatus = 200;
        automodell.setAutoUUID(UUID.randomUUID().toString());
        Automarke automarke = DataHandler.readAutomarke(automarkeUUID);
        Autokonzern autokonzern = DataHandler.readAutokonzern(konzernUUID);

        if (automarke != null) {
            automodell.setAutomarke(automarke);
            DataHandler.saveAutomodell(automodell);
            httpStatus = 200;
        } else {
            httpStatus = 404;
        }

        if (autokonzern != null) {
            automodell.setAutokonzern(autokonzern);
            DataHandler.saveAutomodell(automodell);
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
     * updates an existing player
     *
     * @param automarkeUUID automarke of automodell
     * @param konzernUUID   automarke of automodell
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateAutomodell(
            @Valid @BeanParam Automodell automodell,
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")

            @FormParam("automarkeUUID") String automarkeUUID,
            @FormParam("konzernUUID") String konzernUUID
    ) {
        int httpStatus = 200;
        Automodell oldAutomodell = DataHandler.readAuto(automodell.getAutoUUID());

        if (oldAutomodell.getModellbezeichnung() != null) {
            httpStatus = 200;
            oldAutomodell.setModellbezeichnung(automodell.getModellbezeichnung());
            oldAutomodell.setPreis(automodell.getPreis());
            oldAutomodell.setAutoUUID(automodell.getAutoUUID());
            oldAutomodell.setGewicht(automodell.getGewicht());
            oldAutomodell.setLeistung(automodell.getLeistung());
            oldAutomodell.setVerbrauch(oldAutomodell.getVerbrauch());

            Automarke automarke = DataHandler.readAutomarke(automarkeUUID);
            oldAutomodell.setAutomarke(automarke);

            Autokonzern autokonzern = DataHandler.readAutokonzern(konzernUUID);
            oldAutomodell.setAutokonzern(autokonzern);

            DataHandler.updateAutomodell();
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
     * deletes an existing player identified by its uuid
     *
     * @param autoUUID the unique key for the player
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteAutomodell(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String autoUUID
    ) {
        int httpStatus;

        try {
            UUID.fromString(autoUUID);

            if (DataHandler.deleteAutomodell(autoUUID)) {
                httpStatus = 200;
            } else {
                httpStatus = 404;
            }
        } catch (IllegalArgumentException argEx) {
            httpStatus = 400;
        }

        Response response = Response
                .status(httpStatus)
                .entity("")
                .build();
        return response;
    }

    /**
     * sets the attribute values of the automodell object
     *
     * @param automodell
     * @param modellbezeichnung
     * @param preis
     * @param gewicht
     * @param leistung
     * @param verbrauch
     * @param autoUUID
     * @param konzernUUID
     * @param
     */

    private void setValues(
            Automodell automodell,
            String modellbezeichnung,
            int preis,
            String autoUUID,
            String gewicht,
            int leistung,
            String verbrauch,
            String automarkeUUID,
            String konzernUUID) {
        automodell.setModellbezeichnung(modellbezeichnung);
        automodell.setPreis(preis);
        automodell.setAutoUUID(autoUUID);
        automodell.setGewicht(gewicht);
        automodell.setLeistung(leistung);
        automodell.setVerbrauch(verbrauch);

        Automarke automarke = DataHandler.getAutoMarkeMap().get(automarkeUUID);
        automodell.setAutomarke(automarke);

        Autokonzern autokonzern = DataHandler.getAutokonzernMap().get(konzernUUID);
        automodell.setAutokonzern(autokonzern);
    }


}
