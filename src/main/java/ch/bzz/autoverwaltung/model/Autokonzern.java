package ch.bzz.autoverwaltung.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

public class Autokonzern {

    @FormParam("konzernUUID")
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String konzernUUID;

    @FormParam("konzern")
    @NotEmpty
    @Size(min = 2, max = 40)
    private String konzern;

    /**
     * Gets the konzernUUID
     *
     * @return value of konzernUUID
     */
    public String getKonzernUUID() {
        return konzernUUID;
    }

    /**
     * Sets the konzernUUID
     *
     * @param konzernUUID the value to set
     */

    public void setKonzernUUID(String konzernUUID) {
        this.konzernUUID = konzernUUID;
    }

    /**
     * Gets the konzern
     *
     * @return value of konzern
     */
    public String getKonzern() {
        return konzern;
    }

    /**
     * Sets the konzern
     *
     * @param autokonzern the value to set
     */

    public void setKonzern(Autokonzern autokonzern) {
        this.konzern = konzern;
    }
}