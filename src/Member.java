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

    public class Basic extends Member {
        private static final double MONTHLY_FEE = 39.99;
        private static final int BILLING_INTERVAL = 1;
        private static final int MAX_CLASSES = 4;
        private int numClases;
        public Basic(Profile profile, Date expire, Location homeStudio) {
            super(profile, expire, homeStudio);
        }

        public double getMonthlyFee() {
            return MONTHLY_FEE;
        }
        public int getBillingInterval() {
            return BILLING_INTERVAL;
        }
        public int getMaxClasses() {
            return MAX_CLASSES;
        }
        public int getNumClases() {
            return numClases;
        }
        public void setNumClases(int n) {
            numClases = n;
        }
    }

    public class Family extends Member {
        private static final double MONTHLY_FEE = 49.99;
        private static final int BILLING_INTERVAL = 3;
        private static final int MAX_CLASSES = Integer.MAX_VALUE;
        private boolean guest = true;
        public Family(Profile profile, Date expire, Location homeStudio) {
            super(profile, expire, homeStudio);
        }

        public double getMonthlyFee() {
            return MONTHLY_FEE;
        }
        public int getBillingInterval() {
            return BILLING_INTERVAL;
        }
        public int getMaxClasses() {
            return MAX_CLASSES;
        }
        public boolean getGuest() {
            return guest;
        }
        public void setGuest(boolean bool) {
            guest = bool;
        }
    }

    public class Premium extends Member {
        private static final double MONTHLY_FEE = 59.99;
        private static final int BILLING_INTERVAL = 12;
        private static final int MAX_CLASSES = Integer.MAX_VALUE;
        private int guestPass;
        public Premium(Profile profile, Date expire, Location homeStudio, int guestPass) {
            super(profile, expire, homeStudio);
        }

        public double getMonthlyFee() {
            return MONTHLY_FEE;
        }
        public int getBillingInterval() {
            return BILLING_INTERVAL;
        }
        public int getMaxClasses() {
            return MAX_CLASSES;
        }
        public int getGuestPass() {
            return guestPass;
        }
        public void setGuestPass(int n) {
            guestPass = n;
        }
    }
}
