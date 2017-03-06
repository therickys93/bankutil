package it.therickys93.bankutil;

import static org.junit.Assert.*;
import org.junit.Test;

public class IbanTest {

	@Test
	public void testOne(){
		assertTrue(true);
	}
	
	@Test
	public void testTwo() {
		assertEquals(27, Iban.ITALY_IBAN_LENGTH);
	}
	
	@Test
	public void testThree() {
		assertFalse(Iban.checkIban(null));
	}
	
	@Test
	public void testFour() {
		assertFalse(Iban.checkIban(""));
	}
	
	@Test
	public void testFive() {
		assertFalse(Iban.checkIban("IT34673893"));
	}
	
	@Test
	public void testSix() {
		assertFalse(Iban.checkIban("IT43K0310412701000000820420"));
		assertTrue(Iban.checkIban("IT40S0542811101000000123456"));
	}
	
	@Test
	public void testSeven() {
		Iban iban = new Iban();
		iban.toString();
	}
	
	// IT60X0542811101000000123456
	// IT43K0310412701000000820420
	// IT40S0542811101000000123456
	
}
