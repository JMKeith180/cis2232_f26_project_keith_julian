package ca.hccis.a1.entity;

import ca.hccis.a1.util.CisUtility;

import java.util.Scanner;

public class DayPlanner {

    private int id;
    private int eventParentId;
    private String eventType;
    private String eventSubType;
    private short year;
    private byte month;
    private byte day;
    private byte hour;
    private byte minute;
    private String eventName;

    public DayPlanner() {

    }

    public DayPlanner(int id, int eventParentId, String eventType, String eventSubType, short year, byte month, byte day, byte hour, byte minute, String eventName) {
    this.id = id;
    this.eventParentId = eventParentId;
    this.eventType = eventType;
    this.eventSubType = eventSubType;
    this.year = year;
    this.month = month;
    this.day = day;
    this.hour = hour;
    this.minute = minute;
    this.eventName = eventName;
    }

    public void getInformation() {
        Scanner scanner = new Scanner(System.in);

        IO.println("Event ID: ");
        eventParentId = scanner.nextInt();
        scanner.nextLine();

        IO.println("Name of the Event");
        eventName = scanner.nextLine();

        IO.println("Event Type: ");
        eventType = scanner.nextLine();

        // can be NULL
        IO.println("Event Sub Type");
        eventSubType = scanner.nextLine();

        IO.println("Year for the Event: ");
        year = scanner.nextShort();
        scanner.nextLine();

        IO.println("Month of the Event (as a number): ");
        month = scanner.nextByte();
        scanner.nextLine();

        IO.println("Day of the Event: ");
        day = scanner.nextByte();
        scanner.nextLine();

        String time = CisUtility.getInputString("Time of the event (15:40): ");
        String[] timeSplit = time.split(":");
        hour = Byte.parseByte(timeSplit[0]);
        minute = Byte.parseByte(timeSplit[1]);

    }

    public void edit(){
        String eName = CisUtility.getInputString("Event Name: ");
        String type = CisUtility.getInputString("Event Type: ");
        String sType = CisUtility.getInputString("Event Sub Type: ");
        short year = CisUtility.getInputShort("Year: ");
        byte month = CisUtility.getInputByte("Month: ");
        byte day = CisUtility.getInputByte("Day: ");
        String time = CisUtility.getInputString("Time: ");

        String[] timeSplit = time.split(":");

        setEventName(eName);
        setEventType(type);
        setEventSubType(sType);
        setYear(year);
        setMonth(month);
        setDay(day);
        setHour(Byte.parseByte(timeSplit[0]));
        setMinute(Byte.parseByte(timeSplit[1]));

    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public byte getMinute() {
        return minute;
    }

    public void setMinute(byte minute) {
        this.minute = minute;
    }

    public byte getHour() {
        return hour;
    }

    public void setHour(byte hour) {
        this.hour = hour;
    }

    public byte getDay() {
        return day;
    }

    public void setDay(byte day) {
        this.day = day;
    }

    public byte getMonth() {
        return month;
    }

    public void setMonth(byte month) {
        this.month = month;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public String getEventSubType() {
        return eventSubType;
    }

    public void setEventSubType(String eventSubType) {
        this.eventSubType = eventSubType;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getEventParentId() {
        return eventParentId;
    }

    public void setEventParentId(int eventParentId) {
        this.eventParentId = eventParentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Event ID: " + eventParentId +
                "\nEvent Name: " + eventName +
                "\nEvent Type: " + eventType +
                "\nEvent Sub Type: " + eventSubType +
                "\nDate of planned event: " + year + ", " + month + ", " + day + " at " + hour + ":" +  minute +
                "\n";
    }
}
