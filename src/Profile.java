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
    @Override
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