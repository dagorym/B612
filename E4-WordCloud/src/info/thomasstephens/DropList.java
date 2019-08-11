/**
 * 
 */
package info.thomasstephens;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @brief Class to contain a list of words to ignore
 * 
 * This is just a delegator class that wraps the HashSet class
 * for string objects
 * 
 * 
 * @date Created: Aug 10, 2019
 * @date Modified: Aug 10, 2019
 * @author Tom Stephens
 *
 */
public class DropList {
	private HashSet<String> m_list;
	
	/**
	 * @brief Constructor for DropList class
	 * 
	 * The constructor takes a file as input and reads all the words
	 * in the file and adds them to the drop list.  It is assumed that
	 * there is one word per line in the file.
	 * 
	 * @param file The file containing the list of words to add to the drop list
	 * 
	 * @date Created: Aug 10, 2019
	 * @date Modified: Aug 10, 2019
	 * @author Tom Stephens
	 */
	DropList(String file){
		m_list = new HashSet<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			while (line != null) {
				m_list.add(line);
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * @brief Returns the contents of the drop list as a comma separated list
	 * 
	 * This iterates over the drop list and returns the list of word contained
	 * as a string with each word separated by a comma and a space.
	 * 
	 * @return string containing all the words in the drop list
	 * 
	 * @date Created: Aug 10, 2019
	 * @date Modified: Aug 10, 2019
	 * @author Tom Stephens
	 */
	String printList(){
		String data = "";
		Iterator<String> itr = m_list.iterator();
		int count = 0;
		while (itr.hasNext()) {
			data+= itr.next();
			count++;
			if (count != m_list.size()) {
				data += ", ";
			}
		}
		return data;
	}
	
	/// return the size of the drop list
	int size() {
		return m_list.size();
	}
	
	/// check if the list contains the specified word
	boolean contains(String s) {
		return m_list.contains(s);
	}
	
}
