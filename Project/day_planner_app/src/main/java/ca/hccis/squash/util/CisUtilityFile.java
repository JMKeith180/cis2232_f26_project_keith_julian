package ca.hccis.squash.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CisUtilityFile {

    public static final String FOLDER_NAME = "c:\\CIS2232\\";

    public static void writeReportToFile(String fileName, ArrayList theObjects) {

        String fullName = FOLDER_NAME + fileName + CisUtility.getCurrentDate("_yyyyMMddhhmmss") + ".txt";
        Path path = Paths.get(FOLDER_NAME);
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            System.out.println("BJM-CisUtilityFile-Error creating directories");
        }

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fullName, true));

            for (Object current : theObjects) {
                writer.write(current.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("CisUtilityFile exception with file: " + fullName);
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                System.out.println("CisUtilityFile exception with file: " + fullName);
            }
        }

    }
    /**
     * Write report lines to c:\cis2232\ using the name pattern ReportName + yyyyMMddHHmm + .txt
     * (e.g. EventsByMonth202611011537.txt).
     *
     * @return the full file name written, or null if the file could not be written
     */
    public static String writeReportLines(String reportName, java.util.List<String> lines) {
        String fullName = FOLDER_NAME + reportName + CisUtility.getCurrentDate("yyyyMMddHHmm") + ".txt";
        try {
            Files.createDirectories(Paths.get(FOLDER_NAME));
            Files.write(Paths.get(fullName), lines);
            return fullName;
        } catch (IOException e) {
            System.out.println("CisUtilityFile exception with file: " + fullName + " - " + e.getMessage());
            return null;
        }
    }
}