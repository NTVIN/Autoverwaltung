package ch.bzz.autoverwaltung.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

/**
 * a Automarke of a Automodell
 * <p>
 * Autoverwaltung
 *
 * @author Assvin Shanmuganathan
 */
public class Automarke {

    @FormParam("automarkeUUID")
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String automarkeUUID;

    @FormParam("automarke")
    @NotEmpty
    @Size(min = 2, max = 40)
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
    public String getAutomarke() {
        return automarke;
    }

    /**
     * Sets the automarke
     *
     * @param automarke the value to set
     */

    public void setAutomarke(String automarke) {
        this.automarke = automarke;
    }
}