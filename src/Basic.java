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

    @Override
    public double bill() {
        double monthlyFee = MONTHLY_FEE;
        if (numClases > MAX_CLASSES) {
            monthlyFee += 10.0 * (numClases - MAX_CLASSES);
        }
        return monthlyFee;
    }
}