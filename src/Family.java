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

    public double bill() {
        return MONTHLY_FEE * BILLING_INTERVAL;
    }
}