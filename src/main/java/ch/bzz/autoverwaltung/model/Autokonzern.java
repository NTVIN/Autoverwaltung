package ch.bzz.autoverwaltung.model;

public class Autokonzern {
    private String konzernUUID;
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