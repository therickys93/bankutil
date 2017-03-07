package it.therickys93.bankutil;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilsTest {

	@Test
	public void testOne() {
		assertEquals("hallo", StringUtils.replaceCharAtIndex("hello", 'a', 1));
		assertEquals("amore", StringUtils.replaceCharAtIndex("amare", 'o', 2));
	}
	
}
