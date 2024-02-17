public class Member implements Comparable<Member> {
    private Profile profile;
    private Date expire;
    private Location homeStudio;

    public double bill() {

    }

    public Profile getProfile() {
        return profile;
    }

    public Date getExpire() {
        return expire;
    }

    public Location getHomeStudio() {
        return homeStudio;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Member member = (Member) obj;

        return profile.equals(member.getProfile()) && expire.equals(member.getExpire()) && homeStudio == member.getHomeStudio();
    }

    @Override
    public String toString() {
        String result;
        result = profile.toString() + ", Membership expire " + expire.toString() + ", Location: " + homeStudio.toString();
        return result;
    }

    /**
     * Compares this object with the specified object for order.
     */
    @Override
    public int compareTo(Member member) {
        int profileComp = this.profile.compareTo(member.getProfile());
        if (profileComp != 0) {
            return profileComp;
        }

        int expireComp = this.expire.compareTo(member.getExpire());
        if (expireComp != 0) {
            return expireComp;
        }

        return this.homeStudio.compareTo(member.getHomeStudio());
    }
}
