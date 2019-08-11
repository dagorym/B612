package info.thomasstephens;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DropListTest {
	
	/**
	 * Test the constructor by just verifying the size.  Other tests will
	 * check the contents
	 * 
	 * @date Created: Aug 10, 2019
	 * @date Modified: Aug 10, 2019
	 * @author Tom Stephens
	 */
	@Test
	void testConstructor() {
		DropList dl = new DropList("droplist.txt");
		assert (dl.size() == 7);
	}
	
	/**
	 * Test the list by verifying the correct output from the printList() method.
	 * This test is probably very brittle and may fail on a different machine since
	 * the underlying data structure is a hashSet and the has might get computed
	 * differently
	 * 
	 * @date Created: Aug 10, 2019
	 * @date Modified: Aug 10, 2019
	 * @author Tom Stephens
	 */
	@Test
	void testPrintList() {
		DropList dl = new DropList("droplist.txt");
		String out = dl.printList();
		assert (out.equals("but, or, in, and, of, for, is"));
	}

}
