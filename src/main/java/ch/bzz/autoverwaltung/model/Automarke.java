package ch.bzz.autoverwaltung.model;

/**
 * a Automarke of a Automodell
 * <p>
 * Autoverwaltung
 *
 * @author Assvin Shanmuganathan (Ghwalin)
 */
public class Automarke {
    private String automarkeUUID;
    private String automarke;

    /**
     * Gets the automarkeUUID
     *
     * @return value of automarkeUUID
     */
    public String getAutomarkeUUID() {
        return automarkeUUID;
    }

    /**
     * Sets the automarkeUUID
     *
     * @param automarkeUUID the value to set
     */

    public void setAutomarkeUUID(String automarkeUUID) {
        this.automarkeUUID = automarkeUUID;
    }

    /**
     * Gets the automarke
     *
     * @return value of automarke
     */
    public String getPublisher() {
        return automarke;
    }

    /**
     * Sets the automarke
     *
     * @param automarke the value to set
     */

    public void setPublisher(String automarke) {
        this.automarke = automarke;
    }
}