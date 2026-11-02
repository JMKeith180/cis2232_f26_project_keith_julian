package ca.hccis.squash.dao;

import ca.hccis.squash.entity.EventReportRow;
import ca.hccis.squash.util.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * DAO (JDBC) for the Events by Month report.
 *
 * @since 2026-11-01
 */
public class EventReportDAO {

    // "yyyy-M-d" accepts both 2026-11-03 and 2026-11-3
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-M-d");

    /**
     * Events whose start date falls in the given month, optionally of one type.
     *
     * @param eventType null or blank for all types
     * @return events sorted by start date then start time (empty list if none or on error)
     */
    public ArrayList<EventReportRow> selectByMonth(int year, int month, String eventType) {
        ArrayList<EventReportRow> rows = new ArrayList<>();
        boolean filterByType = eventType != null && !eventType.isBlank();

        String sql = "SELECT e.eventType, e.eventSubtype, e.eventName, e.eventStartDate, e.eventEndDate, "
                + "e.eventStartTime, e.eventEndTime, p.eventName AS parentName "
                + "FROM events e LEFT JOIN events p ON e.eventParentId = p.eventId "
                + "WHERE e.eventStartDate LIKE ? "
                + (filterByType ? "AND e.eventType = ? " : "")
                + "ORDER BY e.eventStartDate, e.eventStartTime";

        try (Connection conn = DatabaseUtility.getConnection("");
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, String.format("%04d-%02d-%%", year, month));
            if (filterByType) {
                ps.setString(2, eventType);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    rows.add(new EventReportRow(
                            rs.getString("eventType"),
                            rs.getString("eventSubtype"),
                            rs.getString("eventName"),
                            LocalDate.parse(rs.getString("eventStartDate"), DATE_FMT),
                            LocalDate.parse(rs.getString("eventEndDate"), DATE_FMT),
                            rs.getInt("eventStartTime"),
                            rs.getInt("eventEndTime"),
                            rs.getString("parentName")));
                }
            }
        } catch (SQLException e) {
            System.out.println("EventReportDAO - selectByMonth error: " + e.getMessage());
        }
        return rows;
    }

    /** @return the distinct event types, for the input form dropdown */
    public ArrayList<String> selectEventTypes() {
        ArrayList<String> types = new ArrayList<>();
        try (Connection conn = DatabaseUtility.getConnection("");
             PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT eventType FROM events ORDER BY eventType");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                types.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("EventReportDAO - selectEventTypes error: " + e.getMessage());
        }
        return types;
    }
}