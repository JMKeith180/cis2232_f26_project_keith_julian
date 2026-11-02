package ca.hccis.squash.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds the inputs and results of the Events by Month report.
 *
 * @since 2026-11-01
 */
public class ReportEvents {
    private int year;
    private int month;
    private String eventType = "";
    private List<EventReportRow> rows = new ArrayList<>();
    private List<Integer> chartLabels = new ArrayList<>();
    private List<Long> chartValues = new ArrayList<>();
    private String savedFileName;
    private boolean ran;

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public int getMonth() { return month; }
    public void setMonth(int month) { this.month = month; }
    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    public List<EventReportRow> getRows() { return rows; }
    public void setRows(List<EventReportRow> rows) { this.rows = rows; }
    public List<Integer> getChartLabels() { return chartLabels; }
    public void setChartLabels(List<Integer> chartLabels) { this.chartLabels = chartLabels; }
    public List<Long> getChartValues() { return chartValues; }
    public void setChartValues(List<Long> chartValues) { this.chartValues = chartValues; }
    public String getSavedFileName() { return savedFileName; }
    public void setSavedFileName(String savedFileName) { this.savedFileName = savedFileName; }
    public boolean isRan() { return ran; }
    public void setRan(boolean ran) { this.ran = ran; }
    public int getTotal() { return rows == null ? 0 : rows.size(); }
}
