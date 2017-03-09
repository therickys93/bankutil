package it.therickys93.bankutil;

import com.vdurmont.emoji.EmojiParser;

/**
 * This class is used as an Utility for the all the package
 * @author therickys93
 *
 */
public class Utils {
	
	/**
	 * The string of the emoji to parse. This is for true
	 */
	public final static String EMOJI_OK = ":white_check_mark:";
	
	/**
	 * The string of the emoji to parse. This is for false
	 */
	public final static String EMOJI_NOT_OK = ":x:";
	
	/**
	 * This simple function replaces in the string the character c at index
	 * @param string the old string to transform
	 * @param c the char that is replaced
	 * @param index the index of the old string
	 * @return the new string 
	 */
	public static String replaceCharAtIndex(String string, char c, int index) {
		return string.substring(0, index) + c + string.substring(index + 1);
	}

	/**
	 * This function returns the max number between the two given as parameters
	 * @param i the first number
	 * @param j the second number
	 * @return the max between the two numbers
	 */
	public static int max(int i, int j) {
		return (i > j) ? i : j;
	}

	/**
	 * This function returns the min number between the two given as parameters
	 * @param i the first number
	 * @param j the second number
	 * @return the min between the two numbers
	 */
	public static int min(int i, int j) {
		return (i < j) ? i : j;
	}

	/**
	 * This function returns true if the number is between the min and the max, false otherwise
	 * @param number the number to test
	 * @param min the lower bound number
	 * @param max the upper bound number
	 * @return true if ok, false if not
	 */
	public static boolean numberIsBetween(int number, int min, int max) {
		return max(number, min) == min(number, max);
	}

	/**
	 * This function returns the correct Emoji of the boolean b
	 * @param b the parameter to test
	 * @return the correct emoji
	 */
	public static String getEmoji(boolean b) {
		return (b) ? EmojiParser.parseToUnicode(EMOJI_OK) : EmojiParser.parseToUnicode(EMOJI_NOT_OK);
	}

	
	
}
