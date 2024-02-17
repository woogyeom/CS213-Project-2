public class Member implements Comparable<Member> {
    private Profile profile;
    private Date expire;
    private Location homeStudio;

    public Member(Profile profile, Date expire, Location homeStudio) {
        this.profile = profile;
        this.expire = expire;
        this.homeStudio = homeStudio;
    }

    public double bill() {

    }

    @Override
    public String toString() {
        return profile.toString() + expire.toString() + ", Location: " + homeStudio.toString();
    }

    @Override
    public boolean equals(Object obj) {
        Profile comparingProfile = (Profile) obj;
        return profile.equals(comparingProfile);
    } // Writeup said we could uniquely identify a member by profile, so I'm not sure if we need to check other fields

    /**
     * Compares this object with the specified object for order.
     */
    @Override
    public int compareTo(Member member) {
        return this.profile.compareTo(member.profile);
    } //Again writeup said we could uniquely identify a member by profile, so I'm not sure if we need to check other fields to compare

    public class Basic extends Member {
        private int numClasses;
        public Basic(Profile profile, Date expire, Location homeStudio, int numClasses) {
            super(profile,  expire, homeStudio);
            this.numClasses = numClasses;
        }

    }

    public class Family extends Member {
        private boolean guest;
        public Family(Profile profile, Date expire, Location homeStudio, boolean guest) {
            super(profile,  expire, homeStudio);
            this.guest = guest;
        }
    }

    public class Premium extends Member {
        private int guestPass;
        public Premium(Profile profile, Date expire, Location homeStudio, int guestPass) {
            super(profile,  expire, homeStudio);
            this.guestPass = guestPass;
        }
    }
}
