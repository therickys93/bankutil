package it.therickys93.bankutil;

import java.math.BigDecimal;

public class Iban {

	public static final int ITALY_IBAN_LENGTH = 27;

	public static boolean checkIban(String iban) {
		if(iban == null){
			return false;
		}
		if(iban.length() != 27) {
			return false;
		}
		
		iban = iban.toUpperCase();
		iban = iban.substring(4).concat(iban.substring(0, 4));
		String newIban = "";
		for(int index = 0; index < iban.length(); index++) {
			char c = iban.charAt(index);
			if(isBetween(c)) {
				int charNewValue = c - 55;
				newIban += "" + charNewValue;
			} else {
				newIban += c;
			}
		}
		BigDecimal ibanNumber = new BigDecimal(newIban);
		BigDecimal remainder = ibanNumber.remainder(BigDecimal.valueOf(97));
		return remainder.intValue() == 1;
	}
	
	private static boolean isBetween(char c) {
		return (c >= 'A' && c <= 'Z');
	}

}
