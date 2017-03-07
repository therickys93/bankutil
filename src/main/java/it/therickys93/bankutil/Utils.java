package it.therickys93.bankutil;

public class Utils {

	public static String replaceCharAtIndex(String string, char c, int index) {
		return string.substring(0, index) + c + string.substring(index + 1);
	}

	public static int max(int i, int j) {
		return (i > j) ? i : j;
	}

	public static int min(int i, int j) {
		return (i < j) ? i : j;
	}

	public static boolean numberIsBetween(int number, int min, int max) {
		return max(number, min) == min(number, max);
	}

	
	
}
