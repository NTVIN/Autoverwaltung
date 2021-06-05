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
    private String preis;
    private Automarke automarke;
    private String gewicht;
    private String leistung;
    private String verbrauch;


    public Automodell() {
        setAutoUUID(null);
        setModellbezeichnung(null);
        setPreis(null);
        setGewicht(null);
        setLeistung(null);
        setVerbrauch(null);
    }

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
    public String getPreis() {
        return preis;
    }

    /**
     * Sets the preis
     *
     * @param preis the value to set
     */

    public void setPreis(String preis) {
        this.preis = preis;
    }

    /**
     * Gets the automarke
     *
     * @return value of automarke
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
     * Gets the gewicht
     *
     * @return value of gewicht
     */
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
    public String getLeistung() {
        return leistung;
    }

    /**
     * Sets the leistung
     *
     * @param leistung the value to set
     */

    public void setLeistung(String leistung) {
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
}