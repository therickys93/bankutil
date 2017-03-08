package it.therickys93.bankutil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class CreditCardTest {

	@Test
	public void testOne() {
		CreditCard card = CreditCard.createCard("5555555555554444");
		assertEquals("5555555555554444", card.number());
	}
	
	@Test
	public void testTwo() {
		CreditCard card = CreditCard.createCard("5555555555554444");
		assertTrue(card.ok());
	}
	
	@Test
	public void testThree() {
		CreditCard card = CreditCard.createCard("5555555555554444");
		assertTrue(card.ok());
		assertEquals("MasterCard", card.type());
	}

	@Test
	public void testFour() {
		CreditCard card = CreditCard.createCard("4111111111111111");
		assertTrue(card.ok());
		assertEquals("Visa", card.type());
	}
	
	@Test
	public void testFive() {
		CreditCard card = CreditCard.createCard("378282246310005");
		assertTrue(card.ok());
		assertEquals("American Express", card.type());
	}
	
	@Test
	public void testSix() {
		CreditCard card = CreditCard.createCard("");
		assertEquals("", card.number());
		assertFalse(card.ok());
		assertEquals("Carta Non Valida", card.type());
	}
	
	@Test
	public void testSeven() {
		CreditCard card = CreditCard.createCard(null);
		assertEquals("", card.number());
		assertFalse(card.ok());
		assertEquals("Carta Non Valida", card.type());
	}
	
	@Test
	public void testEight() {
		CreditCard card = CreditCard.createCard("12345567890");
		assertFalse(card.ok());
		assertEquals("Carta Non Valida", card.type());
	}
	
	@Test
	public void testNine() {
		CreditCard card = CreditCard.createCard("1234567890123");
		assertFalse(card.ok());
		assertEquals("Carta Non Valida", card.type());
	}
	
	@Test
	public void testTen() {
		CreditCard card = CreditCard.createCard("5610591081018250");
		assertTrue(card.ok());
		assertEquals("Non Ancora Supportata", card.type());
	}
	
	@Test
	public void testEleven() {
		CreditCard card = CreditCard.createCard("4716635803152396");
		assertTrue(card.ok());
		assertEquals("Visa", card.type());
	}
	
	@Test
	public void testTwelve() {
		CreditCard card = CreditCard.createCard("4111111111111111");
		assertEquals("CreditCard={number=4111111111111111, correct=true, type=Visa}", card.toString());
	}
	
	@Test
	public void testThirteen() {
		CreditCard card = CreditCard.createCard("4111111111111111");
		assertEquals(cardOk(), card.prettyToString());
	}
	
	@Test
	public void testFourteen() {
		CreditCard card = CreditCard.createCard("1234567890123");
		assertEquals(cardNotOk(), card.prettyToString());
	}
	
	private String cardNotOk() {
		String response = "";
		response += "Carta di Credito:\n";
		response += "n°: 1234567890123\n";
		response += "checksum: ❌\n";
		response += "tipo: Carta Non Valida";
		return response;
	}
	
	private String cardOk() {
		String response = "";
		response += "Carta di Credito:\n";
		response += "n°: 4111111111111111\n";
		response += "checksum: ✅\n";
		response += "tipo: Visa";
		return response;
	}
	
	@Test
	public void testFinal() {
		Map<String, CreditCard> cards = getCards();
		for (Map.Entry<String, CreditCard> entry : cards.entrySet())
		{
		    assertEquals(entry.getKey(), entry.getValue().type());
		}
	}
	
	private Map<String, CreditCard> getCards() {
		Map<String, CreditCard> cards = new HashMap<String, CreditCard>();
		cards.put("American Express", CreditCard.createCard("378282246310005"));
		cards.put("American Express", CreditCard.createCard("371449635398431"));
		cards.put("American Express", CreditCard.createCard("378734493671000"));
		cards.put("MasterCard", CreditCard.createCard("5555555555554444"));
		cards.put("MasterCard", CreditCard.createCard("5105105105105100"));
		cards.put("Visa", CreditCard.createCard("4111111111111111"));
		cards.put("Visa", CreditCard.createCard("4012888888881881"));
		cards.put("Visa", CreditCard.createCard("4222222222222"));
		return cards;
	}
}
