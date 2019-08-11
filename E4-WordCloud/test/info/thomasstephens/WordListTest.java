package info.thomasstephens;

import static org.junit.jupiter.api.Assertions.*;

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

}
