package it.therickys93.bankutil;

public class StringUtils {

	public static String replaceCharAtIndex(String string, char c, int index) {
		return string.substring(0, index) + c + string.substring(index + 1);
	}

	
	
}
