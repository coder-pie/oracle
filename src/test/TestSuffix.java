package test;

import static org.junit.Assert.*;

import org.junit.Test;

import program.scrabbler;

public class TestSuffix {

	@Test
	public void test() {
		scrabbler sc= new scrabbler();
		scrabbler.suffix("zo");
		assertEquals(6, scrabbler.getWordcount());
		
	}

}
