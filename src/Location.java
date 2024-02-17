public enum Location {
    BRIDGEWATER("Bridgewater", "08807", "Somerset County"),
    EDISON("Edison", "08837", "Middlesex County"),
    FRANKLIN("Franklin", "08873", "Somerset County"),
    PISCATAWAY("Piscataway", "08854", "Middlesex County"),
    SOMERVILLE("Somerville", "08876", "Somerset County");

    private final String city;
    private final String zipcode;
    private final String county;

    Location(String city, String zipcode, String county) {
        this.city = city;
        this.zipcode = zipcode;
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCounty() {
        return county;
    }

    public String toSrting() {
        String result;
        result = city + ", " + zipcode + ", " + county;
        return result;
    }
}
