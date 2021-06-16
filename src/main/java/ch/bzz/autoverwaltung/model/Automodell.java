package ch.bzz.autoverwaltung.model;

import java.math.BigDecimal;

/**
 * ein Automodel einer Marke
 * <p>
 * Autoverwaltung
 *
 * @author Assvin Shanmuganathan
 */
public class Automodell {
    private String autoUUID;
    private String modellbezeichnung;
    private int preis;
    private String gewicht;
    private int leistung;
    private String verbrauch;

    private Automarke automarke;
    private Autokonzern autokonzern;


    /**
     * Gets the autoUUID
     *
     * @return value of autoUUID
     */
    public String getAutoUUID() {
        return autoUUID;
    }

    /**
     * Sets the autoUUID
     *
     * @param autoUUID the value to set
     */

    public void setAutoUUID(String autoUUID) {
        this.autoUUID = autoUUID;
    }

    /**
     * Gets the modellbezeichnung
     *
     * @return value of modellbezeichnung
     */
    public String getModellbezeichnung() {
        return modellbezeichnung;
    }

    /**
     * Sets the modellbezeichnung
     *
     * @param modellbezeichnung the value to set
     */

    public void setModellbezeichnung(String modellbezeichnung) {
        this.modellbezeichnung = modellbezeichnung;
    }

    /**
     * Gets the preis
     *
     * @return value of preis
     */
    public int getPreis() {
        return preis;
    }

    /**
     * Sets the preis
     *
     * @param preis the value to set
     */

    public void setPreis(int preis) {
        this.preis = preis;
    }


    public String getGewicht() {
        return gewicht;
    }

    /**
     * Sets the gewicht
     *
     * @param gewicht the value to gewicht
     */

    public void setGewicht(String gewicht) {
        this.gewicht = gewicht;
    }

    /**
     * Gets the leistung
     *
     * @return value of leistung
     */
    public int getLeistung() {
        return leistung;
    }

    /**
     * Sets the leistung
     *
     * @param leistung the value to set
     */

    public void setLeistung(int leistung) {
        this.leistung = leistung;
    }

    /**
     * Gets the verbrauch
     *
     * @return value of verbrauch
     */
    public String getVerbrauch() {
        return verbrauch;
    }

    /**
     * Sets the verbrauch
     *
     * @param verbrauch the value to set
     */

    public void setVerbrauch(String verbrauch) {
        this.verbrauch = verbrauch;
    }

    /**
     * Gets the Automarke
     *
     * @return value of Automarke
     */
    public Automarke getAutomarke() {
        return automarke;
    }

    /**
     * Sets the automarke
     *
     * @param automarke the value to set
     */

    public void setAutomarke(Automarke automarke) {
        this.automarke = automarke;
    }

    /**
     * Gets the Autokonzern
     *
     * @return value of Autokonzern
     */
    public Autokonzern getAutokonzern() {
        return autokonzern;
    }

    /**
     * Sets the autokonzern
     *
     * @param autokonzern the value to set
     */

    public void setAutokonzern(Autokonzern autokonzern) {
        this.autokonzern = autokonzern;
    }
}