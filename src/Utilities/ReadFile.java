package Utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Flight;

public class ReadFile {

	// method to store all flights from CSV flights
	public static List<Flight> readCSVFiles() {

		Flight flight;
		List<Flight> flights = new ArrayList<>();
		SimpleDateFormat sdfo = new SimpleDateFormat("dd-MM-yyyy");
		Scanner sc;

		File folder = new File(Constants.CSV_FILES_PATH);
		File[] listOfFiles = folder.listFiles();
		String fileName;

		for (File file : listOfFiles) {
			fileName = file.getName();
			if (file.isFile() && fileName.substring(fileName.lastIndexOf('.') + 1).equals("csv")) {
				try {
					sc = new Scanner(file);
					sc.nextLine();
					while (sc.hasNext()) {
						String data = sc.nextLine();
						flight = new Flight();
						String[] flightData = data.split("\\|");
						flight.setFlightNumber(flightData[0]);
						flight.setDepartureLocation(flightData[1].toLowerCase());
						flight.setArrivalLocation(flightData[2].toLowerCase());
						flight.setValidityOfFlight(sdfo.parse(flightData[3]));
						flight.setFlightTime(flightData[4]);
						flight.setFlightDuration(Double.parseDouble(flightData[5]));
						flight.setFare(Integer.parseInt(flightData[6]));
						if (flightData[7].equals("Y")) {
							flight.setSeatAvailability(true);
						} else {
							flight.setSeatAvailability(false);
						}
						flight.setFlightClass(flightData[8]);
						flights.add(flight);
					}
				} catch (Exception e) {
					System.out.println("Some error occured while reading file.");
				}
			}
		}
		return flights;
	}

}
