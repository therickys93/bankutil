package it.therickys93.bankutil;

import java.math.BigDecimal;

/**
 * This class operates with an iban code
 * @author therickys93
 *
 */
public class Iban {

	private static final int START_INDEX_ACCOUNT_NUMBER = 15;
	private static final int START_INDEX_OF_CAB = 10;
	private static final int START_INDEX_OF_ABI = 5;
	private static final int SHIFT_VALUE_FOR_LETTERS = 55;
	/**
	 * The length of the Italian IBAN
	 */
	public static final int ITALY_IBAN_LENGTH = 27;
	private static final int INDEX_CHECK_DIGIT_ONE = 2;
	private static final int INDEX_CHECK_DIGIT_TWO = 3;
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
	
	/**
	 * This class will create an Iban Object from the string
	 * @param iban the iban as a string
	 * @return the new Iban object
	 */
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
	
	/**
	 * This function returns if true if the iban checksum is correct, false otherwise
	 * @return the result of the checksum
	 */
	public boolean ibanChecksum() {
		return this.correct;
	}
	
	/**
	 * This function returns the string representation of the Iban
	 * @return the string representation of an Iban
	 */
	@Override
	public String toString() {
		return "Iban={iban="+this.iban.toUpperCase()+", correct="+this.correct+"}";
	}

	/**
	 * This function returns the Iban string given in the createIban function
	 * @return the iban given
	 */
	public String iban() {
		return this.iban.toUpperCase();
	}

	/**
	 * This function returns the Iban check Digits
	 * @return the Iban Check Digits
	 */
	public String getCheckDigits() {
		return this.checkDigits;
	}

	/**
	 * This function returns true if the iban check digits calculated are equals to the check digits given before
	 * @return true if ok, false if not
	 */
	public boolean checkDigitsOK() {
		String digits = "" + this.iban.charAt(INDEX_CHECK_DIGIT_ONE) + this.iban.charAt(INDEX_CHECK_DIGIT_TWO);
		return ((""+checkDigits).equals(digits));
	}

	/**
	 * This function returns the Iban length
	 * @return the Iban length
	 */
	public boolean length() {
		return this.ibanLength;
	}

	/**
	 * This function returns the ABI of the Iban
	 * @return the ABI of the Iban
	 */
	public String abi() {
		return this.iban.substring(START_INDEX_OF_ABI, START_INDEX_OF_CAB);
	}
	
	/**
	 * This function returns the CAB of the Iban
	 * @return the CAB of the Iban
	 */
	public String cab() {
		return this.iban.substring(START_INDEX_OF_CAB, START_INDEX_ACCOUNT_NUMBER);
	}

	/**
	 * This function returns the number of the account
	 * @return the number of the account
	 */
	public String accountNumber() {
		return this.iban.substring(START_INDEX_ACCOUNT_NUMBER);
	}

	/**
	 * This function returns a pretty version of the toString ( written in Italian )
	 * @return the pretty string representation of the response
	 */
	public String prettyToString() {
		String response = "";
		response += "IBAN:\n";
		response += "n°: " + this.iban() + "\n";
		response += "lunghezza: " + Utils.getEmoji(this.length()) + "\n";
		response += "n° controllo: " + Utils.getEmoji(this.checkDigitsOK()) + "\n";
		response += "checksum: " + Utils.getEmoji(this.ibanChecksum()) + "\n";
		response += "abi: " + this.abi() + "\n";
		response += "cab: " + this.cab() + "\n";
		response += "n° conto: " + this.accountNumber();
		return response;
	}

}
