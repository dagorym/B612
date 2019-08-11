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
		String out = wl.printByFrequency(-1, -1, "NONE");
		String fragment = out.substring(0,46);
		assert(fragment.equals("text, 2\na, 2\nit, 2\nthe, 2\nsmall, 1\nproperly, 1"));

	}
}
