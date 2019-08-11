package info.thomasstephens;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class WordListTest {

	@Test
	void testFileConstructor() {
		DropList dl = new DropList("droplist.txt");
		WordList wl = new WordList("testtext.dat",dl);
		assert (wl.size() == 26);
		assert (wl.count("text") == 2);
		assert (wl.count("lines") == 1);
		assert (wl.count("parser") == 1);
	}

	@Test
	void testTextConstructor() {
		DropList dl = new DropList("droplist.txt");
		List<String> dataText = new ArrayList<String>();
		dataText.add("This is a test of the text parser");
		dataText.add("to see if it pulls in multiple lines");
		dataText.add("of text, extracts the words,");
		dataText.add("and properly creates a small");
		dataText.add("word cloud from them.  It should ignore");
		dataText.add("punctuation and whitespace.");
	    WordList wl = new WordList(dataText,dl);
		assert (wl.size() == 26);
		assert (wl.count("text") == 2);
		assert (wl.count("lines") == 1);
		assert (wl.count("parser") == 1);
	}

	@Test
	void testPrintByFrequencyNoFilters() {
		DropList dl = new DropList("droplist.txt");
		WordList wl = new WordList("testtext.dat",dl);
		String out = wl.printByFrequency(-1, -1, "*");
		String fragment = out.substring(0,46);
		assert(fragment.equals("text, 2\na, 2\nit, 2\nthe, 2\nsmall, 1\nproperly, 1"));
	}
	
	@Test
	void testPrintByFrequencyWithLowLimit() {
		DropList dl = new DropList("droplist.txt");
		WordList wl = new WordList("testtext.dat",dl);
		String out = wl.printByFrequency(2, -1, "*");
		assert(out.equals("text, 2\na, 2\nit, 2\nthe, 2\n"));
	}
	
	@Test
	void testPrintByFrequencyWithHighLimit() {
		DropList dl = new DropList("droplist.txt");
		WordList wl = new WordList("testtext.dat",dl);
		String out = wl.printByFrequency(-1, 1, "*");
		String fragment = out.substring(0,36);
		assert(fragment.equals("small, 1\nproperly, 1\ncloud, 1\nsee, 1"));
	}
	
	@Test
	void testPrintByFrequencyWithWordFilter1() {
		DropList dl = new DropList("droplist.txt");
		WordList wl = new WordList("testtext.dat",dl);
		String out = wl.printByFrequency(-1, -1, "t");
		assert(out.equals("the, 2\ntext, 2\ntest, 1\nthis, 1\nthem, 1\nto, 1\n"));
	}
	
	@Test
	void testPrintByFrequencyWithWordFilter2() {
		DropList dl = new DropList("droplist.txt");
		WordList wl = new WordList("testtext.dat",dl);
		String out = wl.printByFrequency(-1, -1, "th");
		assert(out.equals("the, 2\nthis, 1\nthem, 1\n"));
	}
	
	@Test
	void testPrintByFrequencyWithWordFilter3() {
		DropList dl = new DropList("droplist.txt");
		WordList wl = new WordList("testtext.dat",dl);
		String out = wl.printByFrequency(-1, -1, "the");
		assert(out.equals("the, 2\nthem, 1\n"));
	}

	@Test
	void testPrintAlphabeticallyNoFilters() {
		DropList dl = new DropList("droplist.txt");
		WordList wl = new WordList("testtext.dat",dl);
		String out = wl.printAlphabetically(-1, -1, "*");
		String fragment = out.substring(0,46);
		assert(fragment.equals("a, 2\ncloud, 1\ncreates, 1\nextracts, 1\nfrom, 1\ni"));
	}
	
	@Test
	void testPrintAlphabeticallyWithLowLimit() {
		DropList dl = new DropList("droplist.txt");
		WordList wl = new WordList("testtext.dat",dl);
		String out = wl.printAlphabetically(2, -1, "*");
		assert(out.equals("a, 2\nit, 2\ntext, 2\nthe, 2\n"));
	}
	
		@Test
	void testPrintAlphabeticallyWithHighLimit() {
		DropList dl = new DropList("droplist.txt");
		WordList wl = new WordList("testtext.dat",dl);
		String out = wl.printAlphabetically(-1, 1, "*");
		String fragment = out.substring(0,46);
		assert(fragment.equals("cloud, 1\ncreates, 1\nextracts, 1\nfrom, 1\nif, 1\n"));
	}
	
	@Test
	void testPrintAlphabeticallyWithWordFilter1() {
		DropList dl = new DropList("droplist.txt");
		WordList wl = new WordList("testtext.dat",dl);
		String out = wl.printAlphabetically(-1, -1, "t");
		assert(out.equals("test, 1\ntext, 2\nthe, 2\nthem, 1\nthis, 1\nto, 1\n"));
	}
	
	@Test
	void testPrintAlphabeticallyWithWordFilter2() {
		DropList dl = new DropList("droplist.txt");
		WordList wl = new WordList("testtext.dat",dl);
		String out = wl.printAlphabetically(-1, -1, "th");
		assert(out.equals("the, 2\nthem, 1\nthis, 1\n"));
	}
	
	@Test
	void testPrintAlphabeticallyWithWordFilter3() {
		DropList dl = new DropList("droplist.txt");
		WordList wl = new WordList("testtext.dat",dl);
		String out = wl.printAlphabetically(-1, -1, "the");
		assert(out.equals("the, 2\nthem, 1\n"));
	}


}
