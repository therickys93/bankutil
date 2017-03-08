package it.therickys93.bankutil;

import static org.junit.Assert.*;

import org.junit.Test;

public class CreditCardCheckTest {

	@Test
	public void testOne() {
		assertFalse(CreditCard.check("1234567890123"));
	}
	
	@Test
	public void testTwo() {
		assertTrue(CreditCard.check("378282246310005"));
	}
	
	@Test
	public void testThree() {
		assertFalse(CreditCard.isMasterCard("1234567890123456"));
		assertFalse(CreditCard.isMasterCard("123456"));
		assertTrue(CreditCard.isMasterCard("5112345678901234"));
	}
	
	@Test
	public void testFour() {
		assertFalse(CreditCard.isAmericanExpress("123456"));
		assertFalse(CreditCard.isAmericanExpress("341234"));
		assertTrue(CreditCard.isAmericanExpress("341234567890123"));
		assertTrue(CreditCard.isAmericanExpress("371234567890123"));
		assertFalse(CreditCard.isAmericanExpress("451234567890123"));
	}
	
	@Test
	public void testFive() {
		assertFalse(CreditCard.isVisa13("1234567890"));
		assertFalse(CreditCard.isVisa13("1234567890123"));
		assertTrue(CreditCard.isVisa13("4123456789012"));
	}
	
	@Test
	public void testSix() {
		assertFalse(CreditCard.isVisa16("1234567890"));
		assertFalse(CreditCard.isVisa16("1234567890123456"));
		assertTrue(CreditCard.isVisa16("4123456789012345"));
	}
	
	@Test
	public void testSeven() {
		assertFalse(CreditCard.check("ciao"));
		assertFalse(CreditCard.isAmericanExpress("ciao"));
		assertFalse(CreditCard.isMasterCard("ciao"));
		assertFalse(CreditCard.isVisa13("ciao"));
		assertFalse(CreditCard.isVisa16("ciao"));
	}
	
}
