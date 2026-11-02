package ca.hccis.squash.controllers;

import ca.hccis.squash.bo.EventReportBO;
import ca.hccis.squash.dao.EventReportDAO;
import ca.hccis.squash.entity.ReportEvents;
import ca.hccis.squash.util.CisUtilityFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

/**
 * Controller for the Events by Month report.
 *
 * @since 2026-11-01
 */
@Controller
@RequestMapping("/report/events/month")
public class EventReportController {

    private static final Logger logger = LoggerFactory.getLogger(EventReportController.class);

    /** Send the user to the report input view. */
    @RequestMapping("")
    public String input(Model model) {
        logger.info("Events by month report - input view");
        ReportEvents report = new ReportEvents();
        report.setYear(LocalDate.now().getYear());
        report.setMonth(LocalDate.now().getMonthValue());
        model.addAttribute("reportInput", report);
        model.addAttribute("eventTypes", new EventReportDAO().selectEventTypes());
        return "report/reportEventsByMonth";
    }

    /** Run the report, write it to file, and show the results. */
    @RequestMapping("/submit")
    public String submit(Model model, @ModelAttribute("reportInput") ReportEvents report) {
        logger.info("Events by month report - running for {}-{}", report.getYear(), report.getMonth());
        model.addAttribute("eventTypes", new EventReportDAO().selectEventTypes());

        if (!EventReportBO.isValid(report.getYear(), report.getMonth())) {
            model.addAttribute("error", "Enter a year (2000-2100) and a month (1-12).");
            return "report/reportEventsByMonth";
        }

        new EventReportBO().run(report);
        report.setRan(true);

        String saved = CisUtilityFile.writeReportLines("EventsByMonth", EventReportBO.buildFileLines(report));
        if (saved == null) {
            model.addAttribute("error", "The report could not be saved to file.");
        }
        report.setSavedFileName(saved);

        model.addAttribute("reportInput", report);
        return "report/reportEventsByMonth";
    }
}
