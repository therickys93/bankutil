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
		assertFalse(Iban.checkIban(null).correct());
	}
	
	@Test
	public void testFour() {
		assertFalse(Iban.checkIban("").correct());
	}
	
	@Test
	public void testFive() {
		assertFalse(Iban.checkIban("IT34673893").correct());
	}
	
	@Test
	public void testSix() {
		assertFalse(Iban.checkIban("IT34K6789101112131415161718").correct());
		assertTrue(Iban.checkIban("IT60X0542811101000000123456").correct());
		assertTrue(Iban.checkIban("IT43K0310412701000000820420").correct());
		assertTrue(Iban.checkIban("IT40S0542811101000000123456").correct());
	}
		
	@Test
	public void testSeven() {
		Iban iban = Iban.checkIban("IT40S0542811101000000123456");
		assertTrue(iban.correct());
		assertEquals("IT40S0542811101000000123456", iban.iban());
		assertEquals("Iban={iban=IT40S0542811101000000123456, correct=true}", iban.toString());
	}
	
	@Test
	public void testEight() {
		Iban iban = Iban.checkIban("it40s0542811101000000123456");
		assertTrue(iban.correct());
		assertEquals("IT40S0542811101000000123456", iban.iban());
		assertEquals("Iban={iban=IT40S0542811101000000123456, correct=true}", iban.toString());
	}
}
