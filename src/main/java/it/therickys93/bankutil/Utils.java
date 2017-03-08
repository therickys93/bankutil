package it.therickys93.bankutil;

import com.vdurmont.emoji.EmojiParser;

public class Utils {
	
	public final static String EMOJI_OK = ":white_check_mark:";
	public final static String EMOJI_NOT_OK = ":x:";

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

	public static String getEmoji(boolean b) {
		return (b) ? EmojiParser.parseToUnicode(EMOJI_OK) : EmojiParser.parseToUnicode(EMOJI_NOT_OK);
	}

	
	
}
