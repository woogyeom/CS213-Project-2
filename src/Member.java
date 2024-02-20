public class Member implements Comparable<Member> {
    private Profile profile;
    private Date expire;
    private Location homeStudio;
    private final double EXTRA_CLASS_FEE = 10.0;

    public Member(Profile profile, Date expire, Location homeStudio) {
        this.profile = profile;
        this.expire = expire;
        this.homeStudio = homeStudio;
    }

    public double bill() {
        switch (this) {
            case Basic membership -> {
                int attended = membership.getNumClases();
                double monthlyFee = membership.getMonthlyFee();
                if (attended > membership.getMaxClasses()) {
                    monthlyFee += EXTRA_CLASS_FEE * (attended - membership.getMaxClasses());
                }
                return monthlyFee;
            }
            case Family membership -> {
                return membership.getMonthlyFee();
            }
            case Premium membership -> {
                return membership.getMonthlyFee();
            }
            default -> {
                return 0.0;
            }
        }
    }

    public Profile getProfile() {
        return profile;
    }

    public Date getExpire() {
        return  expire;
    }

    public Location getHomeStudio() {
        return homeStudio;
    }

    @Override
    public String toString() {
        return profile.toString() + expire.toString() + ", Location: " + homeStudio.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Member comparingMember = (Member) obj;
        return profile.equals(comparingMember.profile);
    } // Writeup said we could uniquely identify a member by profile, so I'm not sure if we need to check other fields

    /**
     * Compares this object with the specified object for order.
     */
    public int compareTo(Member member) {
        return this.profile.compareTo(member.profile);
    } //Again writeup said we could uniquely identify a member by profile, so I'm not sure if we need to check other fields to compare
}
