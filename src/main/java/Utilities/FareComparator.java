package Utilities;

import java.util.Comparator;

import models.Flight;

public class FareComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		{
			Flight s1 = (Flight) o1;
			Flight s2 = (Flight) o2;

			if (s1.getFare() == s2.getFare())
				return 0;
			else if (s1.getFare() > s2.getFare())
				return 1;
			else
				return -1;
		}
	}
}
