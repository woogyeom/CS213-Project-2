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
        return 0.0;
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
        String expireStatus = expire.isExpired() ? "Membership expired " + expire.toString() : "Membership expires " + expire.toString();
        String memberShip = null;
        switch (this) {
            case Basic basic -> memberShip = "(Basic) number of classes attended: " + basic.getNumClases();
            case Family family -> {
                if (family.expired()) {
                    memberShip = "(Family) guest-pass remaining: not eligible";
                } else if (family.getGuest()) {
                    memberShip = "(Family) guest-pass remaining: 1";
                } else {
                    memberShip = "(Family) guest-pass remaining: 0";
                }
            }
            case Premium premium -> {
                if (!premium.expired()) {
                    memberShip = "(Premium) guest-pass remaining: " + premium.getGuestPass();
                } else {
                    memberShip = "(Premium) guest-pass remaining: not eligible";
                }
            }
            default -> {}
        }
        return profile.toString() + ", " + expireStatus + ", Home Studio: " + homeStudio.toString() + ", " + memberShip;
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

    public boolean expired() {
        return expire.isExpired();
    }
}
