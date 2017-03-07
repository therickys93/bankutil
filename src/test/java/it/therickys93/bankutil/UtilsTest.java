package it.therickys93.bankutil;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilsTest {

	@Test
	public void testOne() {
		assertEquals("hallo", Utils.replaceCharAtIndex("hello", 'a', 1));
		assertEquals("amore", Utils.replaceCharAtIndex("amare", 'o', 2));
	}
	
	@Test
	public void testTwo() {
		assertEquals(10, Utils.max(1, 10));
		assertEquals(10, Utils.max(10, 1));
	}
	
	@Test
	public void testThree() {
		assertEquals(1, Utils.min(1, 10));
		assertEquals(1, Utils.min(10, 1));
	}
	
	@Test
	public void testFour() {
		assertTrue(Utils.numberIsBetween(2, 1, 3));
		assertTrue(Utils.numberIsBetween(1, 1, 3));
		assertTrue(Utils.numberIsBetween(3, 1, 3));
		assertFalse(Utils.numberIsBetween(10, 1, 3));
	}
	
	@Test
	public void testImproveCodeCoverage() {
		Utils utils = new Utils();
		utils.toString();
	}
	
}
