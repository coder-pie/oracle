package test;

import static org.junit.Assert.*;

import org.junit.Test;

import program.scrabbler;

public class TestPermute {

	@Test
	public void test() {
		scrabbler sc= new scrabbler();
		scrabbler.permute("dab");
		assertEquals(4, scrabbler.getWordcount());
		
	}

}
