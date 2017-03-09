package it.therickys93.bankutil;

/**
 * This class operates with the Credit Card numbers ( only Visa, MasterCard and American Express works )
 * @author therickys93
 *
 */
public class CreditCard {

	private static final String NOT_SUPPORTED_YET = "Non Ancora Supportata";
	private static final String AMERICAN_EXPRESS = "American Express";
	private static final String VISA = "Visa";
	private static final String MASTER_CARD = "MasterCard";
	private static final String INVALID = "Carta Non Valida";
	private static final int AMERICAN_EXPRESS_LENGTH = 15;
	private static final int MASTER_CARD_LENGTH = 16;
	private static final int VISA_LENGTH_16 = 16;
	private static final int VISA_LENGTH_13 = 13;
	private String number;
	private boolean valid;
	private String type;
	
	CreditCard(String number, boolean valid, String type){
		this.number = number;
		this.valid = valid;
		this.type = type;
	}
	
	/**
	 * This function creates a CreditCard instance for the number given as parameter
	 * @param number the number of the credit card given
	 * @return a new CreditCard object
	 */
	public static CreditCard createCard(String number) {
		if(number == null || number.isEmpty()) {
			return new CreditCard("", false, INVALID);
		}
		
		int length = number.length();
		if((length != VISA_LENGTH_13) && (length != AMERICAN_EXPRESS_LENGTH) && (length != MASTER_CARD_LENGTH)){
			return new CreditCard(number, false, INVALID);
		}
		
		boolean check = check(number);
		if(check) {
			if(isMasterCard(number)){
				return new CreditCard(number, true, MASTER_CARD);
			} else if(isVisa16(number)) {
				return new CreditCard(number, true, VISA);
			} else if(isVisa13(number)){
				return new CreditCard(number, true, VISA);
			} else if(isAmericanExpress(number)) {
				return new CreditCard(number, true, AMERICAN_EXPRESS);
			}
			return new CreditCard(number, true, NOT_SUPPORTED_YET);
		}
		return new CreditCard(number, false, INVALID);
	}
	
	private static boolean isNumber(String number){
		try {
			Long.parseLong(number);
			return true;
		} catch(NumberFormatException e){
			return false;
		}
	}
	
	/**
	 * This functions use the Luhm algorithm to verify if the number is valid
	 * @param number the number of the credit card
	 * @return true if ok, false otherwise
	 */
	public static boolean check(String number){
		if(isNumber(number)) {
			int sum = 0;
			boolean alternate = false;
			for(int index = number.length() - 1; index >= 0; index--){
				int n = Integer.parseInt(number.substring(index, index + 1));
				if(alternate){
					n *= 2;
					if(n > 9){
						n = (n % 10) + 1;
					}
				}
				alternate = !alternate;
				sum += n;
			}
			
			return (sum % 10) == 0;
		} else {
			return false;
		}
	}

	/**
	 * This function returns the credit card number given
	 * @return the credit card number
	 */
	public String number() {
		return this.number;
	}

	/**
	 * This function returns if the credit card is valid or not
	 * @return true if valid, false if not
	 */
	public boolean ok() {
		return this.valid;
	}

	/**
	 * This function returns the credit card type: Visa, MasterCard or American Express
	 * @return the credit card type
	 */
	public String type() {
		return this.type;
	}
	
	/**
	 * This function returns the string representation of the CreditCard
	 * @return the string representation of a CreditCard
	 */
	@Override
	public String toString() {
		String response = "";
		response += "CreditCard={number=" + this.number + ", correct=" + this.valid + ", type=" + this.type + "}";
		return response;
	}

	/**
	 * This function returns a pretty version of the toString ( written in Italian )
	 * @return the pretty string representation of the response
	 */
	public String prettyToString() {
		String response = "";
		response += "Carta di Credito:\n";
		response += "n°: " + this.number + "\n";
		response += "checksum: " + Utils.getEmoji(this.valid) + "\n";
		response += "tipo: " + this.type;
		return response;
	}

	/**
	 * This function returns if the credit card could be a MasterCard
	 * @param number the number of the credit card
	 * @return true if ok, false if not
	 */
	public static boolean isMasterCard(String number) {
		if(isNumber(number)) {
			int n = Integer.parseInt(number.substring(0, 2));
			boolean master = Utils.numberIsBetween(n, 51, 55);
			return (number.length() == MASTER_CARD_LENGTH) && master;
		} else {
			return false;
		}
	}

	/**
	 * This function returns if the credit card could be an American Express
	 * @param number the number of the credit card
	 * @return true if ok, false if not
	 */
	public static boolean isAmericanExpress(String number) {
		if(isNumber(number)) {
			int n = Integer.parseInt(number.substring(0, 2));
			return (number.length() == AMERICAN_EXPRESS_LENGTH) && (n == 34 || n == 37);
		} else {
			return false;
		}
	}
	
	/**
	 * This function returns if the credit card could be a Visa 13 digits
	 * @param number the number of the credit card
	 * @return true if ok, false if not
	 */
	public static boolean isVisa13(String number) {
		return isVisa(number, VISA_LENGTH_13);
	}

	/**
	 * This function returns if the credit card could be a Visa 16 digits
	 * @param number the number of the credit card
	 * @return true if ok, false if not
	 */
	public static boolean isVisa16(String number) {
		return isVisa(number, VISA_LENGTH_16);
	}
	
	private static boolean isVisa(String number, int length){
		if(isNumber(number)){
			int n = Integer.parseInt(number.substring(0, 1));
			return (number.length() == length) && (n == 4);
		} else {
			return false;
		}
	}

}
