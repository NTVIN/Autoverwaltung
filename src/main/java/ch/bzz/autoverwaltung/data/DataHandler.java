package ch.bzz.autoverwaltung.data;

import ch.bzz.autoverwaltung.model.Automarke;
import ch.bzz.autoverwaltung.model.Automodell;
import ch.bzz.autoverwaltung.model.Autokonzern;

import ch.bzz.autoverwaltung.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    private static Map<String, Autokonzern> autokonzernMap;

    /**
     * default constructor: defeat instantiation
     */
    private DataHandler() {
        autoMap = new HashMap<>();
        autoMarkeMap = new HashMap<>();
        autokonzernMap = new HashMap<>();
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
     * saves a auto
     *
     * @param automodell the book to be saved
     */
    public static void saveAutomodell(Automodell automodell) {
        getAutoMap().put(automodell.getAutoUUID(), automodell);
        writeJSON();
    }

    /**
     * updates the automodellmap
     */
    public static void updateAutomodell() {
        writeJSON();
    }

    /**
     * removes a player from the Automodellmap
     *
     * @param autoUUID the uuid of the automodell to be removed
     * @return success
     */
    public static boolean deleteAutomodell(String autoUUID) {
        if (getAutoMap().remove(autoUUID) != null) {
            writeJSON();
            return true;
        } else
            return false;
    }


// end of automodell


    /**
     * reads a single autokonzern identified by its uuid
     *
     * @param konzernUUID the identifier
     * @return automarke -object
     */
    public static Autokonzern readAutokonzern(String konzernUUID ) {
        Autokonzern autokonzern = new Autokonzern();
        if (getAutokonzernMap().containsKey(konzernUUID)) {
            autokonzern = getAutokonzernMap().get(konzernUUID);
        }
        return autokonzern ;
    }

    /**
     * saves a autokonzern
     *
     * @param autokonzern the konzern to be saved
     */
    public static void saveAutokonzern(Autokonzern autokonzern) {
       Automodell automodell = new Automodell();
       automodell.setAutoUUID(UUID.randomUUID().toString());
       automodell.setModellbezeichnung("");
       automodell.setAutokonzern(autokonzern);
       saveAutomodell(automodell);
    }

    /**
     * updates the autokonzernmap
     */
    public static boolean updateAutokonzern(Autokonzern autokonzern) {
        boolean found = false;
        for (Map.Entry<String, Automodell> entry : getAutoMap().entrySet()) {
            Automodell automodell = entry.getValue();
            if (automodell.getAutokonzern().getKonzernUUID().equals(autokonzern.getKonzernUUID())) {
                automodell.setAutokonzern(autokonzern);
                found = true;
            }
        }
        writeJSON();
        return found;
    }


    /**
     * removes a konzern from the konzernmap
     *
     * @param konzernUUID the uuid of the shoe to be removed
     * @return errorcode  0=ok, -1=referential integrity, 1=not found
     */
    public static int deleteAutokonzern(String konzernUUID) {
        int errorcode = 1;
        for (Map.Entry<String, Automodell> entry : getAutoMap().entrySet()) {
            Automodell automodell = entry.getValue();
            if (automodell.getAutokonzern().getKonzernUUID().equals(konzernUUID)) {
                if (automodell.getModellbezeichnung() == null || automodell.getModellbezeichnung().equals("")) {
                    deleteAutomodell(automodell.getAutoUUID());
                    errorcode = 0;
                } else {
                    return -1;
                }
            }
        }
        if (getAutoMap().containsKey(konzernUUID)) {
            getAutokonzernMap().remove(konzernUUID);
            errorcode = 0;
            writeJSON();
        }
        return errorcode;
    }


//end of Autokonzern



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
        Automodell automodell = new Automodell();
        automodell.setAutoUUID(UUID.randomUUID().toString());
        automodell.setModellbezeichnung("");
        automodell.setAutomarke(automarke);
        saveAutomodell(automodell);
    }

    /**
     * updates the AutomarkeMap
     */
    public static boolean updateAutomarke(Automarke automarke) {
        boolean found = false;
        for (Map.Entry<String, Automodell> entry : getAutoMap().entrySet()) {
            Automodell automodell = entry.getValue();
            if (automodell.getAutomarke().getAutomarkeUUID().equals(automarke.getAutomarkeUUID())) {
                automodell.setAutomarke(automarke);
                found = true;
            }
        }
        writeJSON();
        return found;
    }

    /**
     * removes a Automarke from the AutomarkeMap
     *
     * @param automarkeUUID the uuid of the marke to be removed
     * @return success
     */
    public static int deleteAutomarke(String automarkeUUID) {
        int errorcode = 1;
        for (Map.Entry<String, Automodell> entry : getAutoMap().entrySet()) {
            Automodell automodell = entry.getValue();
            if (automodell.getAutomarke().getAutomarkeUUID().equals(automarkeUUID)) {
                if (automodell.getModellbezeichnung() == null || automodell.getModellbezeichnung().equals("")) {
                    deleteAutomodell(automodell.getAutoUUID());
                    errorcode = 0;
                } else {
                    return -1;
                }
            }
        }
        if (getAutoMarkeMap().containsKey(automarkeUUID)) {
            getAutoMarkeMap().remove(automarkeUUID);
            errorcode = 0;
            writeJSON();
        }
        return errorcode;
    }


// end of automarke



    /**
     * gets the autoMap
     *
     * @return the bookMap
     */
    public static Map<String, Automodell> getAutoMap() {
        return autoMap;
    }
    /**
     * gets the autokonzernMap
     *
     * @return the autokonzernMap
     */
    public static Map<String, Autokonzern> getAutokonzernMap() {
        return autokonzernMap;
    }

    /**
     * gets the autoMarkeMap
     *
     * @return the autoMarkeMap
     */
    public static Map<String, Automarke> getAutoMarkeMap() {
        return autoMarkeMap;
    }

    public static void setAutoMarkeMap(Map<String, Automarke> autoMarkeMap) {
        DataHandler.autoMarkeMap = autoMarkeMap;
    }
    public static void setAutokonzernMap(Map<String, Autokonzern> autokonzernMap) {
        DataHandler.autokonzernMap = autokonzernMap;
    }

    /**
     * reads the autos and automarken
     */
    private static void readJSON() {
        try {
            String autoModellPath = Config.getProperty("autoJSON");
            byte[] jsonData = Files.readAllBytes(Paths.get(autoModellPath));
            ObjectMapper objectMapper = new ObjectMapper();
            Automodell[] automodelle = objectMapper.readValue(jsonData, Automodell[].class);
            for (Automodell automodell : automodelle) {
                String autoMarkeUUID = automodell.getAutomarke().getAutomarkeUUID();
                Automarke automarke;
                if (getAutoMarkeMap().containsKey(autoMarkeUUID)) {
                    automarke = getAutoMarkeMap().get(autoMarkeUUID);
                } else {
                    automarke = automodell.getAutomarke();
                    getAutoMarkeMap().put(autoMarkeUUID, automarke);
                }
                automodell.setAutomarke(automarke);

                String autokonzernUUID = automodell.getAutokonzern().getKonzernUUID();
                Autokonzern autokonzern;

                if (getAutokonzernMap().containsKey(autokonzernUUID)) {
                    autokonzern = getAutokonzernMap().get(autokonzernUUID);
                } else {
                    autokonzern = automodell.getAutokonzern();
                    getAutokonzernMap().put(autokonzernUUID, autokonzern);
                }
                automodell.setAutokonzern(autokonzern);
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

        String autoPath = ch.bzz.autoverwaltung.service.Config.getProperty("autoJSON");
        try {
            fileOutputStream = new FileOutputStream(autoPath);
            writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectMapper.writeValue(writer, getAutoMap().values());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
