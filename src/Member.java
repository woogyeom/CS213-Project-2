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

    public static class Profile {
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
                if (this.getDob().compareTo(profile.getDob()) == 0) {
                    return true;
                }
                return false;
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
