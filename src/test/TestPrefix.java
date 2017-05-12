package test;

import static org.junit.Assert.*;

import org.junit.Test;

import program.scrabbler;

public class TestPrefix {
	String[] expectedValues={"zodiac" ,"zodiacal" ,"zodiacs" ,"zombi" ,"zombie" ,"zombies" ,"zombis" ,"zonal" ,"zone" ,"zoned" ,"zones" ,"zoning" ,"zonked" ,"zoo" ,"zoological" ,"zoologist" ,"zoologists" ,"zoology" ,"zoom" ,"zoomed" ,"zooming" ,"zooms" ,"zoos"};
	@Test
	public void test() {
		scrabbler sc= new scrabbler();
		scrabbler.prefix("zo");
		assertEquals(23, scrabbler.getWordcount());
		assertArrayEquals(expectedValues, scrabbler.getWords().toArray());
	}

}
