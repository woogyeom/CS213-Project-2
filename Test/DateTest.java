import org.junit.Test;

import static org.junit.Assert.*;

public class DateTest {

    @Test
    public void testIsValid_InvalidMonth() {
        Date date = new Date(13, 15, 2023); // Month 13 is invalid
        assertFalse(date.isValid());
    }

    @Test
    public void testIsValid_NormalYearFebruary29() {
        Date date = new Date(2, 29, 2021); // 2021 is not a leap year
        assertFalse(date.isValid());
    }

    @Test
    public void testIsValid_InvalidDayForMonth() {
        Date date = new Date(4, 31, 2023); // April only has 30 days
        assertFalse(date.isValid());
    }

    @Test
    public void testIsValid_InvalidFebruaryDay() {
        Date date = new Date(2, 30, 2023); // February 30 is always invalid
        assertFalse(date.isValid());
    }

    @Test
    public void testIsValid_November40() {
        Date date = new Date(11, 40, 2022); // Day 40 is always invalid
        assertFalse(date.isValid());
    }

    @Test
    public void testIsValid_ValidLeapYearFebruary29() {
        Date date = new Date(2, 29, 2000); // 2000 is a leap year
        assertTrue(date.isValid());
    }

    @Test
    public void testIsValid_ValidDate() {
        Date date = new Date(12, 31, 2023); // December has 31 days
        assertTrue(date.isValid());
    }
}