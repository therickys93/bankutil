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
		assertFalse(Iban.createIban(null).ibanChecksum());
	}
	
	@Test
	public void testFour() {
		assertFalse(Iban.createIban("").ibanChecksum());
	}
	
	@Test
	public void testFive() {
		assertFalse(Iban.createIban("IT34673893").ibanChecksum());
	}
	
	@Test
	public void testSix() {
		assertFalse(Iban.createIban("IT34K6789101112131415161718").ibanChecksum());
		assertTrue(Iban.createIban("IT60X0542811101000000123456").ibanChecksum());
		assertTrue(Iban.createIban("IT43K0310412701000000820420").ibanChecksum());
		assertTrue(Iban.createIban("IT40S0542811101000000123456").ibanChecksum());
	}
		
	@Test
	public void testSeven() {
		Iban iban = Iban.createIban("IT40S0542811101000000123456");
		assertTrue(iban.ibanChecksum());
		assertEquals("IT40S0542811101000000123456", iban.iban());
		assertEquals("Iban={iban=IT40S0542811101000000123456, correct=true}", iban.toString());
	}
	
	@Test
	public void testEight() {
		Iban iban = Iban.createIban("it40s0542811101000000123456");
		assertTrue(iban.ibanChecksum());
		assertEquals("IT40S0542811101000000123456", iban.iban());
		assertEquals("Iban={iban=IT40S0542811101000000123456, correct=true}", iban.toString());
	}
	
	@Test
	public void testNine() {
		Iban iban = Iban.createIban("IT43K0310412701000000820420");
		assertTrue(iban.ibanChecksum());
		assertEquals("43", iban.getCheckDigits());
	}
	
	@Test
	public void testTen() {
		Iban iban = Iban.createIban("IT40S0542811101000000123456");
		assertTrue(iban.ibanChecksum());
		assertEquals("IT40S0542811101000000123456", iban.iban());
		assertEquals("40", iban.getCheckDigits());
		assertTrue(iban.checkDigitsOK());
		assertTrue(iban.length());
	}
	
	@Test
	public void testEleven() {
		Iban iban = Iban.createIban("IT40S0542811101000000123456");
		assertEquals("IT40S0542811101000000123456", iban.iban());
		assertEquals("05428", iban.abi());
		assertEquals("11101", iban.cab());
		assertEquals("000000123456", iban.accountNumber());
	}
	
	@Test
	public void testTwelve() {
		Iban iban = Iban.createIban("IT40S0542811101000000123456");
		assertEquals(ibanOk(), iban.prettyToString());
	}
	
	@Test
	public void testThirteen() {
		Iban iban = Iban.createIban("IT34K6789101112131415161718");
		assertEquals(ibanNotOk(), iban.prettyToString());
	}
	
	private String ibanNotOk() {
		String response = "";
		response += "IBAN:\n";
		response += "n°: IT34K6789101112131415161718\n";
		response += "lunghezza: ✅\n";
		response += "n° controllo: ❌\n";
		response += "checksum: ❌\n";
		response += "abi: 67891\n";
		response += "cab: 01112\n";
		response += "n° conto: 131415161718";
		return response;
	}
	
	private String ibanOk() {
		String response = "";
		response += "IBAN:\n";
		response += "n°: IT40S0542811101000000123456\n";
		response += "lunghezza: ✅\n";
		response += "n° controllo: ✅\n";
		response += "checksum: ✅\n";
		response += "abi: 05428\n";
		response += "cab: 11101\n";
		response += "n° conto: 000000123456";
		return response;
	}
	
	@Test
	public void usage() {
		Iban iban = Iban.createIban("IT40S0542811101000000123456");
		assertEquals("IT40S0542811101000000123456", iban.iban());
		assertTrue(iban.length());
		assertTrue(iban.checkDigitsOK());
		assertTrue(iban.ibanChecksum());
	}
}
