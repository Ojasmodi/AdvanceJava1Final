package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import Utilities.InputChecker;
import Utilities.ReadFile;
import models.Flight;

public class Main {

	static List<Flight> existingFlights;

	public static void main(String[] args) {

		Timer timer = setTimerForUpdatingCSVFiles();
		try {
			Thread.sleep(500);
			Flight flight = getInputFromUser(existingFlights);
			FindFlights.findListOfFlights(flight, existingFlights);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			timer.cancel();
		}
	}

	private static Timer setTimerForUpdatingCSVFiles() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				existingFlights = ReadFile.readCSVFiles();
			}
		};
		Timer timer = new Timer();
		long delay = 0;
		long intevalPeriod = 1 * 10000;
		// schedules the task to be run in an interval
		timer.scheduleAtFixedRate(task, delay, intevalPeriod);
		return timer;
	}

	// method for getting user Input
	private static Flight getInputFromUser(List<Flight> flights) {
		List<String> arrivalLocations = new ArrayList<>();
		List<String> departureLocations = new ArrayList<>();
		Iterator iterator = flights.iterator();
		String inputValue;
		Flight flight = new Flight();
		SimpleDateFormat sdfo = new SimpleDateFormat("dd-MM-yyyy");
		while (iterator.hasNext()) {
			Flight tempFlight = (Flight) iterator.next();
			arrivalLocations.add(tempFlight.getArrivalLocation());
			departureLocations.add(tempFlight.getDepartureLocation());
		}
		System.out.println("Enter flight details...");
		System.out.print("Enter departure location.");
		System.err.println("(Should be 3 characters long)");
		while (true) {
			inputValue = InputChecker.getInputForItemValue().toLowerCase();
			if (!departureLocations.contains(inputValue)) {
				System.err.println("Entered departure Location is missing in the database. Please enter again.");
			} else {
				flight.setDepartureLocation(inputValue.toLowerCase());
				break;
			}
		}
		System.out.print("Enter destination");
		System.err.println("(Should be 3 characters long)");
		while (true) {
			inputValue = InputChecker.getInputForItemValue().toLowerCase();
			if (!arrivalLocations.contains(inputValue)) {
				System.err.println("Entered destination is missing in the database. Please enter again.");
			} else {
				flight.setArrivalLocation(inputValue.toLowerCase());
				break;
			}
		}
		System.out.println("Enter journey date(dd-mm-yyyy).");
		while (true) {
			inputValue = InputChecker.getInputForItemValue();
			try {
				Date tempDate = sdfo.parse(inputValue);
				Date currentDate = new Date();
				if (currentDate.compareTo(tempDate) <= 0) {
					flight.setValidityOfFlight(tempDate);
					break;
				} else {
					System.err.println("Do not enter a past date.Please enter again in dd-MM-yyyy format.");
				}

			} catch (Exception e) {
				System.err.println("Enter a valid journey date in dd-mm-yyyy format");
			}
		}
		System.out.println("Choose flight class.\n1.Enter E/e for Economy class.\n2.Enter B/b for Bussiness class.");
		while (true) {
			inputValue = InputChecker.getInputForItemValue();
			if (inputValue.equalsIgnoreCase("E") || inputValue.equalsIgnoreCase("B")) {
				flight.setFlightClass(inputValue.toUpperCase());
				break;
			} else
				System.err.println("Invalid preference. Please enter your choice again.");
		}
		return flight;
	}

}
