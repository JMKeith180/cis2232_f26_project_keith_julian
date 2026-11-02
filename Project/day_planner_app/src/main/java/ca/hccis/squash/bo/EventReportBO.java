package ca.hccis.squash.bo;

import ca.hccis.squash.dao.EventReportDAO;
import ca.hccis.squash.entity.EventReportRow;
import ca.hccis.squash.entity.ReportEvents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Business logic for the Events by Month report.
 *
 * @since 2026-11-01
 */
public class EventReportBO {

    /** @return true when year is 2000-2100 and month is 1-12 */
    public static boolean isValid(int year, int month) {
        return year >= 2000 && year <= 2100 && month >= 1 && month <= 12;
    }

    /** Runs the report: fills rows and the per-day chart data on the report object. */
    public void run(ReportEvents report) {
        EventReportDAO dao = new EventReportDAO();
        report.setRows(dao.selectByMonth(report.getYear(), report.getMonth(), report.getEventType()));

        Map<Integer, Long> perDay = countPerDay(report.getRows());
        report.setChartLabels(new ArrayList<>(perDay.keySet()));
        report.setChartValues(new ArrayList<>(perDay.values()));
    }

    /** @return number of events starting on each day of month (sorted by day) */
    public static Map<Integer, Long> countPerDay(List<EventReportRow> rows) {
        Map<Integer, Long> perDay = new TreeMap<>();
        for (EventReportRow row : rows) {
            perDay.merge(row.startDate().getDayOfMonth(), 1L, Long::sum);
        }
        return perDay;
    }

    /** @return the lines written to the report file */
    public static ArrayList<String> buildFileLines(ReportEvents report) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("Report: Events by Month");
        lines.add("Criteria: " + report.getYear() + "-" + String.format("%02d", report.getMonth())
                + ", type = " + (report.getEventType() == null || report.getEventType().isBlank()
                ? "All" : report.getEventType()));
        lines.add("------------------------------------------------");
        for (EventReportRow row : report.getRows()) {
            lines.add(row.toString());
        }
        lines.add("------------------------------------------------");
        lines.add("Total events: " + report.getTotal());
        return lines;
    }
}
