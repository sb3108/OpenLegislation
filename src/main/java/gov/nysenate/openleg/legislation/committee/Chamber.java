package gov.nysenate.openleg.legislation.committee;

/**
 * Enumeration of the different legislative chambers.
 */
public enum Chamber {
    SENATE  ('S'),
    ASSEMBLY('A');

    private final char abbreviation;

    Chamber(char abbreviation) {
        this.abbreviation = abbreviation;
    }

    public char getAbbreviation() {
        return abbreviation;
    }

    public String asSqlEnum() {
        return this.toString().toLowerCase();
    }

    /**
     * Returns the chamber that is opposite to this one.
     */
    public Chamber opposite() {
        return this == SENATE ? ASSEMBLY : SENATE;
    }

    public static Chamber getValue(String value) {
        if (value == null)
            throw new IllegalArgumentException("Supplied value cannot be null when mapping to Chamber.");
        return valueOf(value.trim().toUpperCase());

    }
}
