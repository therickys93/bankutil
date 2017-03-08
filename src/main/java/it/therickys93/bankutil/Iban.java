package it.therickys93.bankutil;

import java.math.BigDecimal;

public class Iban {

	private static final int START_INDEX_ACCOUNT_NUMBER = 15;
	private static final int START_INDEX_OF_CAB = 10;
	private static final int START_INDEX_OF_ABI = 5;
	private static final int SHIFT_VALUE_FOR_LETTERS = 55;
	public static final int ITALY_IBAN_LENGTH = 27;
	public static final int INDEX_CHECK_DIGIT_ONE = 2;
	public static final int INDEX_CHECK_DIGIT_TWO = 3;
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
		
	public static Iban createIban(String iban) {
		if(iban == null){
			return new Iban(iban, false, 0, false);
		}
		if(iban.length() != ITALY_IBAN_LENGTH) {
			return new Iban(iban, false, 0, false);
		}
		
		String newIban = Utils.replaceCharAtIndex(iban, '0', INDEX_CHECK_DIGIT_ONE);
		newIban = Utils.replaceCharAtIndex(newIban, '0', INDEX_CHECK_DIGIT_TWO);
		
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
			if(Utils.numberIsBetween(c, 'A', 'Z')) {
				int charNewValue = c - SHIFT_VALUE_FOR_LETTERS;
				newIban += "" + charNewValue;
			} else {
				newIban += c;
			}
		}
		BigDecimal ibanNumber = new BigDecimal(newIban);
		BigDecimal remainder = ibanNumber.remainder(BigDecimal.valueOf(97));
		return remainder.intValue();
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
		String digits = "" + this.iban.charAt(INDEX_CHECK_DIGIT_ONE) + this.iban.charAt(INDEX_CHECK_DIGIT_TWO);
		return ((""+checkDigits).equals(digits));
	}

	public boolean length() {
		return this.ibanLength;
	}

	public String abi() {
		return this.iban.substring(START_INDEX_OF_ABI, START_INDEX_OF_CAB);
	}

	public String cab() {
		return this.iban.substring(START_INDEX_OF_CAB, START_INDEX_ACCOUNT_NUMBER);
	}

	public String accountNumber() {
		return this.iban.substring(START_INDEX_ACCOUNT_NUMBER);
	}

}
