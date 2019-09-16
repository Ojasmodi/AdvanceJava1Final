package Utilities;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class InputChecker {

	// to check whether the entered input is blank or not
	public static String getInputForItemValue() {
		Scanner sc = new Scanner(System.in);
		String inputString = sc.nextLine();
		while (true) {
			if (StringUtils.isBlank(inputString))
				System.err.print("Please enter the required input.");
			else
				break;
			inputString = sc.nextLine();
		}
		return inputString;
	}
}
