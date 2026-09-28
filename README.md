# Day Planner App #

Sample cis2232 project

## Development Team ##

Business Client:  Chandler	<br/>
Lead Developer:  Julian	<br/>
Project Manager / Quality Control:  Alex F	<br/>

## Description ##

This web application is a calendar and day planner. Each event has an eventType, an optional eventSubtype and an eventName. An event with only an eventType set is a parent event and only appears on the calendar web page. An event with both an eventType and an eventSubtype set is a child event and appears on the day planner page of its parent event.

On the calendar page the user can select a month and every calendar event for that month is displayed. Clicking a parent event opens its day planner page, which shows all events whose eventParentId is set to that calendar event. Day planner events are shown in a list with their name and their hour and minute timestamp.

## Color ##

Main Color:  Light Blue

## Required Fields ##

eventId	int	ID for the event on the calendar	<br/>
eventParentId	int	ID of the parent event for events with a subtype	<br/>
eventType	String	Type of event	<br/>
eventSubtype	String	Subtype of event	<br/>
eventYear	short	Year of the event	<br/>
eventMonth	byte	Month of the event	<br/>
eventDay	byte	Day of the month of the event	<br/>
eventHour	byte	Hour of the event	<br/>
eventMinute	byte	Minute of the event	<br/>
eventName	String	Name of event	<br/>

## Calculation ##

Event distance:  Determine the number of days an event is away, or the hours and minutes if the event is less than one day away. The result is displayed when the user hovers over the event.

Event sorting:  Sort all events for a given month or day planner page.

Event count:  Get the number of events for a given month or day planner page.
