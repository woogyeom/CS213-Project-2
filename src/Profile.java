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
    
}
