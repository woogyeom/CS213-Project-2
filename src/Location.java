public enum Location {
    BRIDGEWATER("BRIDGEWATER", "08807", "Somerset County"),
    EDISON("EDISON", "08837", "Middlesex County"),
    FRANKLIN("FRANKLIN", "08873", "Somerset County"),
    PISCATAWAY("PISCATAWAY", "08854", "Middlesex County"),
    SOMERVILLE("SOMERVILLE", "08876", "Somerset County");

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

    public  String getCounty() {
        return county;
    }

    public String toString() {
        return city + ", " + zipcode + ", " + county.toUpperCase();
    }
}
