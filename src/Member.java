public class Member implements Comparable<Member> {
    private Profile profile;
    private Date expire;
    private Location homeStudio;

    public double bill() {

    }

    /**
     * Compares this object with the specified object for order.
     */
    @Override
    public int compareTo(Member o) {
        return 0;
    }
}
