public class FitnessClass {
    private Offer classInfo;
    private Instructor instructor;
    private Location studio;
    private Time time;
    private MemberList members;
    private MemberList guests;

    public FitnessClass(Offer classInfo, Instructor instructor, Location studio, Time time) {
        this.classInfo = classInfo;
        this.instructor = instructor;
        this.studio = studio;
        this.time = time;
        this.members = null;
        this.guests = null;
    }

    public Offer getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(Offer classInfo) {
        this.classInfo = classInfo;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Location getStudio() {
        return studio;
    }

    public void setStudio(Location studio) {
        this.studio = studio;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public MemberList getMembers() {
        return members;
    }

    public void setMembers(MemberList members) {
        this.members = members;
    }

    public MemberList getGuests() {
        return guests;
    }

    public void setGuests(MemberList guests) {
        this.guests = guests;
    }
}
