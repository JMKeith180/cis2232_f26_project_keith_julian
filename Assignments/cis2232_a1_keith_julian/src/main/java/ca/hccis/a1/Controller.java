package ca.hccis.a1;

import com.google.gson.Gson;
import ca.hccis.a1.entity.DayPlanner;
import ca.hccis.a1.util.CisUtility;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

public class Controller {

    public static final int EXIT = 0;

    public static final String MENU = "1) Add" + System.lineSeparator()
            + "2) Edit" + System.lineSeparator()
            + "3) View" + System.lineSeparator()
            + EXIT + ") Exit"
            + System.lineSeparator();

    public static final String MESSAGE_ERROR = "Error";
    public static final String MESSAGE_EXIT = "Goodbye";
    public static final String MESSAGE_SUCCESS = "Success";

    private static HashMap<Integer, DayPlanner> eventMap = new HashMap();
    private static Gson gson = new Gson();


    public static final String PATH_NAME = "c:\\cis2232\\data_keith_julian.json";

    public static void main() {

        readAll();

        int menuOption;

        do {
            menuOption = CisUtility.getInputInt(MENU);

            switch (menuOption) {
                case EXIT:
                    System.out.println(MESSAGE_EXIT);
                    break; //Break out of the loop as we're finished.
                case 1:
                    add();
                    break;
                case 2:
                    edit();
                    break;
                case 3:
                    viewAll();
                    break;
                default:
                    System.out.println(MESSAGE_ERROR);
                    break;
            }
        } while (menuOption != EXIT);

    }

    public static void add() {
        DayPlanner newDayPlanner = new DayPlanner();
        IO.println("--- Add New Event ---");
        newDayPlanner.getInformation();

        if (eventMap.containsKey(newDayPlanner.getEventParentId())) {
            IO.println("That registration id already exist, would you like to overwrite it?");
            boolean rewrite = CisUtility.getInputBoolean("Yes or No (y/n)");
            if (rewrite) {
                eventMap.put(newDayPlanner.getEventParentId(), newDayPlanner);
            }
        } else {
            eventMap.put(newDayPlanner.getEventParentId(), newDayPlanner);
        }
        writeAll();
    }

    public static void edit() {
        int eventId = CisUtility.getInputInt("Event ID: ");
        if (!eventMap.containsKey(eventId)) {
            IO.println("Event ID can not be found, please try again");

        } else {
            DayPlanner editEvent = eventMap.get(eventId);
            editEvent.edit();

            writeAll();
        }
    }

    public static void writeAll() {
        Path path = Path.of("c:\\cis2232");


        try {
            Files.createDirectories(path);

            FileWriter writer = new FileWriter(PATH_NAME, false);
            for (DayPlanner current : eventMap.values()) {
                writer.append(gson.toJson(current));
                writer.append(System.lineSeparator());
            }
            writer.close();
            IO.println("Successfully written JSON string to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void viewAll(){
        readAll();
        if (eventMap.isEmpty()){
            IO.println("No events found.");
        }
        for (DayPlanner event : eventMap.values()){
            IO.println(event.toString());
        }
    }

    public static void readAll() {
        Path filePath = Path.of(PATH_NAME);
        if (!Files.exists(filePath)) {
            return; // nothing to load yet
        }
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                if (line.isBlank()) continue;
                DayPlanner eventFromJson = gson.fromJson(line, DayPlanner.class);
                eventMap.put(eventFromJson.getEventParentId(), eventFromJson);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

