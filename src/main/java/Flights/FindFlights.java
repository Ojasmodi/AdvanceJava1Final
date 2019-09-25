package main;

import java.util.*;

import Utilities.FareComparator;
import Utilities.FlightTimeComparator;
import Utilities.InputChecker;
import Utilities.Constants;
import models.Flight;

public class FindFlights {

	// method to list all flights based on user input
	public static void findListOfFlights(Flight flight, List<Flight> flights) {
		List<Flight> resultedFlights = new ArrayList<>();
		String inputValue;
		String sortByPreference;
		Iterator iterator = flights.iterator();
		while (iterator.hasNext()) {
			Flight tempFlight = (Flight) iterator.next();
			if ((flight.getArrivalLocation()).equals(tempFlight.getArrivalLocation())
					&& (flight.getDepartureLocation()).equals(tempFlight.getDepartureLocation())
					&& (tempFlight.getValidityOfFlight().compareTo(flight.getValidityOfFlight()) <= 0)
					&& tempFlight.isSeatAvailability()) {
				if (flight.getFlightClass().equals("B"))
					tempFlight.setFare(tempFlight.getFare() * Constants.BUSINESS_CLASS_MULTIPLIER);
				resultedFlights.add(tempFlight);
			}
		}
		if (resultedFlights.size() == 0) {
			System.out.println("No flights available for the given criteria.");
		} else {
			System.out.println("Enter output preference number.-\n 1.By Fare\n 2.By both Fare and flight duration.");
			while (true) {
				inputValue = InputChecker.getInputForItemValue();
				if (inputValue.equals("1")) {
					sortByPreference = "Fare";
					break;
				} else if (inputValue.equals("2")) {
					sortByPreference = "FareAndFlightDuration";
					break;
				} else
					System.err.println("Invalid preference. Please enter your choice again.");
			}
			Collections.sort(resultedFlights, new FareComparator());
			if (sortByPreference.equals("FareAndFlightDuration"))
				Collections.sort(resultedFlights, new FlightTimeComparator());
			System.out.println("Flight number|Fare(Rs)|Time(24:00)|Duration(Hrs)");
			for (Flight f : resultedFlights)
				System.out.println(f.getFlightNumber() + "|" + f.getFare() + "|" + f.getFlightTime() + "|"
						+ f.getFlightDuration());
		}
	}

}
