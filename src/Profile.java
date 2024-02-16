public class Profile {
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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Profile profile = (Profile) obj;
        return fname.equals(profile.getFname()) && lname.equals(profile.getLname()) && dob.equals(profile.getDob());
    }

    @Override
    public String toString() {
        String result;
        result = fname + " " + lname + ":" + dob.toString();
        return result;
    }

    public int compareTo(Profile profile) {
        int fnameComp = fname.compareTo(profile.getFname());
        if (fnameComp != 0) {
            return fnameComp;
        }

        int lnameComp = lname.compareTo(profile.getLname());
        if (lnameComp != 0) {
            return lnameComp;
        }

        return dob.compareTo(profile.dob);
    }
}
