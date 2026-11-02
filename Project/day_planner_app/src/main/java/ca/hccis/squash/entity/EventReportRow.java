package ca.hccis.squash.entity;

import java.time.LocalDate;

/**
 * One line of the Events by Month report.
 *
 * @since 2026-11-01
 */
public record EventReportRow(String eventType, String eventSubtype, String eventName,
                             LocalDate startDate, LocalDate endDate,
                             int startTime, int endTime, String parentName) {

    /** @return start time as hh:mm (stored as hhmm, e.g. 930 -> 09:30) */
    public String getStartTimeText() {
        return formatTime(startTime);
    }

    /** @return end time as hh:mm */
    public String getEndTimeText() {
        return formatTime(endTime);
    }

    public static String formatTime(int hhmm) {
        return String.format("%02d:%02d", hhmm / 100, hhmm % 100);
    }

    /** Used for the report file output. */
    @Override
    public String toString() {
        return startDate + " to " + endDate + " | " + getStartTimeText() + "-" + getEndTimeText()
                + " | " + eventType + " | " + (eventSubtype == null ? "" : eventSubtype)
                + " | " + eventName + (parentName == null ? "" : " (part of " + parentName + ")");
    }
}
