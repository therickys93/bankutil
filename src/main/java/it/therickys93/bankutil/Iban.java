package it.therickys93.bankutil;

import java.math.BigDecimal;

public class Iban {

	private String iban;
	private boolean correct;
	
	Iban(String iban, boolean correct){
		this.iban = iban;
		this.correct = correct;
	}
	
	public static final int ITALY_IBAN_LENGTH = 27;

	public static Iban checkIban(String iban) {
		if(iban == null){
			return new Iban(iban, false);
		}
		if(iban.length() != 27) {
			return new Iban(iban, false);
		}
		
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
		boolean right = remainder.intValue() == 1;
		return new Iban(iban, right);
	}
	
	private static boolean isBetween(char c) {
		return (c >= 'A' && c <= 'Z');
	}

	public boolean correct() {
		return this.correct;
	}
	
	@Override
	public String toString() {
		return "Iban={iban="+this.iban+", correct="+this.correct+"}";
	}

	public String iban() {
		return this.iban;
	}

}
