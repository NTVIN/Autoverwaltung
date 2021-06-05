package ch.bzz.autoverwaltung.data;

import ch.bzz.autoverwaltung.model.Automarke;
import ch.bzz.autoverwaltung.model.Automodell;

import ch.bzz.autoverwaltung.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * data handler for reading and writing the csv files
 * <p>
 *
 * @author Assvin Shanmuganathan
 */

public class DataHandler {
    private static final DataHandler instance = new DataHandler();
    private static Map<String, Automodell> autoMap;
    private static Map<String, Automarke> autoMarkeMap;

    /**
     * default constructor: defeat instantiation
     */
    private DataHandler() {
        autoMap = new HashMap<>();
        autoMarkeMap = new HashMap<>();
        readJSON();
    }

    /**
     * reads a single book identified by its uuid
     *
     * @param autoUUID the identifier
     * @return Automodell-object
     */
    public static Automodell readAuto(String autoUUID) {
        Automodell automodell = new Automodell();
        if (getAutoMap().containsKey(autoUUID)) {
            automodell = getAutoMap().get(autoUUID);
        }
        return automodell;
    }

    /**
     * saves a autp
     *
     * @param automodell the book to be saved
     */
    public static void saveAutomodell(Automodell automodell) {
        getAutoMap().put(automodell.getAutoUUID(), automodell);
        writeJSON();
    }

    /**
     * reads a single automarke identified by its uuid
     *
     * @param automarkeUUID the identifier
     * @return automarke -object
     */
    public static Automarke readAutomarke(String automarkeUUID) {
        Automarke automarke = new Automarke();
        if (getAutoMarkeMap().containsKey(automarkeUUID)) {
            automarke = getAutoMarkeMap().get(automarkeUUID);
        }
        return automarke;
    }

    /**
     * saves a automarke
     *
     * @param automarke the publisher to be saved
     */
    public static void saveAutomarke(Automarke automarke) {
        getAutoMarkeMap().put(automarke.getAutomarkeUUID(), automarke);
        writeJSON();
    }

    /**
     * gets the autoMap
     *
     * @return the bookMap
     */
    public static Map<String, Automodell> getAutoMap() {
        return autoMap;
    }

    /**
     * gets the autoMarkeMap
     *
     * @return the autoMarkeMap
     */
    public static Map<String, Automarke> getAutoMarkeMap() {
        return autoMarkeMap;
    }

    public static void setPublisherMap(Map<String, Automarke> autoMarkeMap) {
        DataHandler.autoMarkeMap = autoMarkeMap;
    }

    /**
     * reads the autos and automarken
     */
    private static void readJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(Config.getProperty("bookJSON")));
            ObjectMapper objectMapper = new ObjectMapper();
            Automodell[] automodelle = objectMapper.readValue(jsonData, Automodell[].class);
            for (Automodell automodell : automodelle) {
                String automarkeUUID = automodell.getAutomarke().getAutomarkeUUID();
                Automarke automarke;
                if (getAutoMarkeMap().containsKey(automarkeUUID)) {
                    automarke = getAutoMarkeMap().get(automarkeUUID);
                } else {
                    automarke = automodell.getAutomarke();
                    getAutoMarkeMap().put(automarkeUUID, automarke);
                }
                automodell.setAutomarke(automarke);
                getAutoMap().put(automodell.getAutoUUID(), automodell);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * write the books and automarken
     */
    private static void writeJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        Writer writer;
        FileOutputStream fileOutputStream = null;

        String autoPath = Config.getProperty("bookJSON");
        try {
            fileOutputStream = new FileOutputStream(autoPath);
            writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectMapper.writeValue(writer, getAutoMap().values());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
