package it.therickys93.bankutil;

import java.math.BigDecimal;

public class Iban {

	private String iban;
	private boolean correct;
	private String checkDigits;
	private boolean ibanLength;
	
	Iban(String iban, boolean correct, int checkDigits, boolean ibanLength){
		this.iban = iban;
		this.correct = correct;
		if(checkDigits < 10){
			this.checkDigits = "0"+checkDigits;
		} else {
			this.checkDigits = "" + checkDigits;
		}
		this.ibanLength = ibanLength;
	}
	
	public static final int ITALY_IBAN_LENGTH = 27;

	public static Iban createIban(String iban) {
		if(iban == null){
			return new Iban(iban, false, 0, false);
		}
		if(iban.length() != 27) {
			return new Iban(iban, false, 0, false);
		}
		
		String newIban = StringUtils.replaceCharAtIndex(iban, '0', 2);
		newIban = StringUtils.replaceCharAtIndex(newIban, '0', 3);
		
		int ibanChecksum = checksum(iban);
		boolean right = ibanChecksum == 1;
		int checkDigits = 98 - checksum(newIban.toString());
		return new Iban(iban, right, checkDigits, true);
	}
	
	private static int checksum(String iban) {
		String tempiban = iban.toUpperCase();
		tempiban = tempiban.substring(4).concat(tempiban.substring(0, 4));
		String newIban = "";
		for(int index = 0; index < iban.length(); index++) {
			char c = tempiban.charAt(index);
			if(isBetween(c)) {
				int charNewValue = c - 55;
				newIban += "" + charNewValue;
			} else {
				newIban += c;
			}
		}
		BigDecimal ibanNumber = new BigDecimal(newIban);
		BigDecimal remainder = ibanNumber.remainder(BigDecimal.valueOf(97));
		return remainder.intValue();
	}
	
	private static boolean isBetween(char c) {
		return (c >= 'A' && c <= 'Z');
	}

	public boolean ibanChecksum() {
		return this.correct;
	}
	
	@Override
	public String toString() {
		return "Iban={iban="+this.iban.toUpperCase()+", correct="+this.correct+"}";
	}

	public String iban() {
		return this.iban.toUpperCase();
	}

	public String getCheckDigits() {
		return this.checkDigits;
	}

	public boolean checkDigitsOK() {
		String digits = "" + this.iban.charAt(2) + this.iban.charAt(3);
		return ((""+checkDigits).equals(digits));
	}

	public boolean length() {
		return this.ibanLength;
	}

}
