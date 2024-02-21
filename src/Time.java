public enum Time {
    MORNING (9, 30),
    AFTERNOON (14, 0),
    EVENING (18, 30);

    private final int hour;
    private final int minute;

    Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String toString() {
        String strHour = String.valueOf(hour);
        String strMinute = String.format("%02d", minute);

        return strHour + ":" + strMinute;
    }
}
