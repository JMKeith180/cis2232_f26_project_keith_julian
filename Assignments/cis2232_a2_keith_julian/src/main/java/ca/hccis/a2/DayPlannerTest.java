package ca.hccis.a2;

import ca.hccis.a2.entity.DayPlanner;
import ca.hccis.a2.bo.DayPlannerBO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

public class DayPlannerTest {

    private static final double DELTA = 0.0001;
    private DayPlannerBO dayPlannerBO;

    @BeforeEach
    public void setUp() {
        Clock fixed = Clock.fixed(Instant.parse("2026-10-02T12:00:00Z"), ZoneOffset.UTC);
        dayPlannerBO = new DayPlannerBO(fixed);
    }

    private DayPlanner buildEvent(int year, int month, int day, int hour, int minute) {

        DayPlanner dayPlanner = new DayPlanner();

        dayPlanner.setYear((short) year);
        dayPlanner.setMonth((byte) month);
        dayPlanner.setDay((byte) day);
        dayPlanner.setHour((byte) hour);
        dayPlanner.setMinute((byte) minute);

        return dayPlanner;
    }

    //My Test
    @Test
    public void returnEventIsADaysAway() {
        double days = dayPlannerBO.calculate(buildEvent(2026, 10, 7, 12, 0));
        assertEquals(5.0, days, DELTA);
    }

    @Test
    public void returnFractionWhenEventIsLessThanOneDayAway() {
        double days = dayPlannerBO.calculate(buildEvent(2026, 10, 2, 18, 30));
        assertTrue(days < 1.0);
        assertEquals(6.5 / 24, days, DELTA);
    }

    @Test
    public void returnNegativeWhenEventIsInThePast() {
        double days = dayPlannerBO.calculate(buildEvent(2026, 10, 1, 12, 0));
        assertTrue(days < 0);
        assertEquals(-1.0, days, DELTA);
    }

    //AI's tests
    @Test
    public void eventAtExactlyNowIsZeroDaysAway() {
        assertEquals(0.0, dayPlannerBO.calculate(buildEvent(2026, 10, 2, 12, 0)), DELTA);
    }

    @Test
    public void eventExactlyOneDayAwayIsOne() {
        assertEquals(1.0, dayPlannerBO.calculate(buildEvent(2026, 10, 3, 12, 0)), DELTA);
    }

    @Test
    public void eventOneAndAHalfDaysAwayIsOnePointFive() {
        assertEquals(1.5, dayPlannerBO.calculate(buildEvent(2026, 10, 4, 0, 0)), DELTA);
    }

    @Test
    public void eventThirtyMinutesAwayIsUnderOneDay() {
        double days = dayPlannerBO.calculate(buildEvent(2026, 10, 2, 12, 30));
        assertTrue(days > 0 && days < 1.0);
        assertEquals(30.0 / (24 * 60), days, DELTA);
    }

    @Test
    public void eventOneMinuteAwayIsSmallPositiveValue() {
        double days = dayPlannerBO.calculate(buildEvent(2026, 10, 2, 12, 1));
        assertTrue(days > 0);
        assertEquals(1.0 / (24 * 60), days, DELTA);
    }

    @Test
    public void eventLaterTodayUsesHoursAndMinutes() {
        assertEquals(11.0 / 24 + 59.0 / (24 * 60),
                dayPlannerBO.calculate(buildEvent(2026, 10, 2, 23, 59)), DELTA);
    }

    @Test
    public void eventAcrossMonthBoundaryIsCalculatedCorrectly() {
        assertEquals(30.0, dayPlannerBO.calculate(buildEvent(2026, 11, 1, 12, 0)), DELTA);
    }

    @Test
    public void eventAcrossYearBoundaryIsCalculatedCorrectly() {
        assertEquals(91.0, dayPlannerBO.calculate(buildEvent(2027, 1, 1, 12, 0)), DELTA);
    }

    @Test
    public void leapDayIsCountedForLeapYear() {
        // 2026-10-02 to 2028-03-01 = 516 days (2028-02-29 exists)
        assertEquals(516.0, dayPlannerBO.calculate(buildEvent(2028, 3, 1, 12, 0)), DELTA);
    }

    @Test
    public void pastEventIsNegative() {
        assertTrue(dayPlannerBO.calculate(buildEvent(2026, 9, 30, 12, 0)) < 0);
    }

    @Test
    public void pastEventLessThanOneDayAgoIsNegativeFraction() {
        double days = dayPlannerBO.calculate(buildEvent(2026, 10, 2, 6, 0));
        assertEquals(-0.25, days, DELTA);
    }

    @Test
    public void nullEventThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> dayPlannerBO.calculate(null));
    }

    @Test
    public void invalidDateThrowsDateTimeException() {
        assertThrows(DateTimeException.class, () -> dayPlannerBO.calculate(buildEvent(2026, 2, 30, 12, 0)));
    }

    @Test
    public void invalidHourThrowsDateTimeException() {
        assertThrows(DateTimeException.class, () -> dayPlannerBO.calculate(buildEvent(2026, 10, 5, 24, 0)));
    }

    @Test
    public void defaultConstructorMeasuresFromRealClock() {
        DayPlanner farFuture = buildEvent(2099, 1, 1, 0, 0);
        assertTrue(new DayPlannerBO().calculate(farFuture) > 365);
    }
}