public class Premium extends Member {
    private static final double MONTHLY_FEE = 59.99;
    private static final int BILLING_INTERVAL = 12;
    private static final int MAX_CLASSES = Integer.MAX_VALUE;
    private int guestPass;
    public Premium(Profile profile, Date expire, Location homeStudio) {
        super(profile, expire, homeStudio);
        this.guestPass = 3;
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
    public double bill() {
        return MONTHLY_FEE * BILLING_INTERVAL - MONTHLY_FEE;
    }
}