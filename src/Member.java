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

    }

    public class Family extends Member {
        private static final double MONTHLY_FEE = 49.99;
        private static final int BILLING_INTERVAL = 3;
        private static final int MAX_CLASSES = Integer.MAX_VALUE;
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
    }

    public class Premium extends Member {
        private static final double MONTHLY_FEE = 59.99;
        private static final int BILLING_INTERVAL = 12;
        private static final int MAX_CLASSES = Integer.MAX_VALUE;
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
    }

    public class Profile implements Comparable<Profile> {
        private String fname;
        private String lname;
        private Date dob;

        public Profile(String fname, String lname, Date dob) {
            this.fname = fname;
            this.lname = lname;
            this.dob = dob;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getLname() {
            return lname;
        }

        public void setLname(String lname) {
            this.lname = lname;
        }

        public Date getDob() {
            return dob;
        }

        public void setDob(Date date) {
            this.dob = date;
        }

        @Override
        public String toString() {
            return fname + ":" + lname + ":" + dob;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Profile profile = (Profile) obj;

            if (this.getFname().equals(profile.getFname()) && this.getLname().equals(profile.getLname())) {
                return this.getDob().compareTo(profile.getDob()) == 0;
            }
            return false;
        }

        public int compareTo(Profile profile) {
            if (this.getLname().compareToIgnoreCase(profile.getLname()) == 0) {
                if (this.getFname().compareToIgnoreCase(profile.getFname()) == 0) {
                    if (this.getDob().compareTo(profile.getDob()) == 0) {
                        return 0;
                    }
                    return this.getDob().compareTo(profile.getDob());
                }
                return this.getFname().compareToIgnoreCase(profile.getFname());
            }
            return this.getLname().compareToIgnoreCase(profile.getLname());
        }
    }
}
