/**
 * 
 */
package info.thomasstephens;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Object to store all the words and their values for the
 * word cloud.  This class uses a map as the underlying data
 * structure to hold the data with the word as the key and the
 * count as the value.  It requires a DropList object to be
 * passed in to ignore words.
 * 
 * @date Created: Aug 10, 2019
 * @date Modified: Aug 10, 2019
 * @author Tom Stephens
 */
public class WordList {
	
	private Map<String,Integer> m_words;
	
	/**
	 * Default constructor creates an empty list
	 * 
	 * @date Created: Aug 10, 2019
	 * @date Modified: Aug 10, 2019
	 * @author Tom Stephens
	 */
	WordList(){
		m_words = new HashMap();
	}
	
	/**
	 * @brief Process a single line of text
	 * 
	 * This method take a single line of text, removes all punctuation,
	 * converts everything to lower case, splits the line into individual
	 * words, and adds all words not in the drop list to the full word cloud
	 * list.
	 * 
	 * @param line The string of words to process
	 * @param dl The drop list to check against
	 * 
	 * @date Created: Aug 10, 2019
	 * @date Modified: Aug 10, 2019
	 * @author Tom Stephens
	 */
	void processLine(String line,DropList dl) {
		// convert text to lower case, remove punctuation, and break the line individual words
		String[] words = line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
		for (int i = 0; i < words.length; i++) {
			// check against drop list
			if (!dl.contains(words[i])){  // only add if not in drop list
				String k = words[i];
				if (m_words.containsKey(k)) { // add one to word count if already there
					m_words.replace(k, m_words.get(k)+1);
				} else {  // or create a new entry
					m_words.put(k, 1);
				}
			}
		}
	}

	/**
	 * @brief Constructor creates list by parsing passed in file name
	 * 
	 * This constructor takes the name of the data file and the DropList object
	 * The file is read one word at a time and if the word is not in the drop list
	 * it is added to the word count
	 * 
	 * @param file The input file containing the list of words to process
	 * @param dl The DropList object containing the words to ignore
	 * 
	 * @date Created: Aug 10, 2019
	 * @date Modified: Aug 10, 2019
	 * @author Tom Stephens
	 */
	WordList(String file, DropList dl){
		m_words = new HashMap();
		
		// Open the file
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			// Read a line of text
			String line = br.readLine();
			while (line != null) {
				processLine(line,dl);
				// read in the next line
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
	 * @brief Constructor creates list by parsing the passed List<String>
	 * 
	 * This constructor takes the list of strings and the DropList object
	 * The list is processed one string at a time, extracting the words in each
	 * string and if the word is not in the drop list
	 * it is added to the word count
	 * 
	 * @param data The input List<String> containing the words to process
	 * @param dl The DropList object containing the words to ignore
	 * 
	 * @date Created: Aug 10, 2019
	 * @date Modified: Aug 10, 2019
	 * @author Tom Stephens
	 */
	WordList(List<String> data, DropList dl){
		m_words = new HashMap();

		// iterate over the list
		Iterator<String> itr = data.iterator();
		while (itr.hasNext()) {
			String line = itr.next();
			processLine(line,dl);
		}
	}
	
	String print(String order, int low, int high, String filter){
		String out = "";
		filter=filter.toLowerCase();
		if (order.equals("frequency")) {
			out = printByFrequency(low,high,filter);
		}
		return out;
	}
	
	String printByFrequency(int low, int high, String filter) {
		String out = "";
		TreeSet<Integer> vList = new TreeSet<>();
		vList.addAll(m_words.values());
		Iterator<Integer> itr = vList.descendingIterator();
		Set<String> keys = null;
		if (filter.equals("NONE")) {
			keys = m_words.keySet();
		} else {
			//We'll do the word filtering by simply removing the keys we need to check against
			Set<String> keyList = m_words.keySet();
			keys = new HashSet<String>();
			Iterator<String> kItr = keyList.iterator();
			while (kItr.hasNext()) {
				String key = kItr.next();
				if (key.length()>=filter.length() && key.substring(0,filter.length()).equals(filter)) {
					keys.add(key);
				}
			}
			
		}
		while (itr.hasNext()) {
			Integer value = itr.next();
			if (high != -1 && value > high) continue;  // skip if we're above the upper frequency limit
			if (value < low) break;      // if we drop below the lower limit we're done
			Iterator<String> kItr = keys.iterator();
			List<String> keysToRemove = new ArrayList<String>();
			while (kItr.hasNext()) {
				String key = kItr.next();
				if (value == m_words.get(key)) {
					out += key+", "+ value + "\n";
					keysToRemove.add(key);
				}
			}
			Iterator<String> lItr = keysToRemove.iterator();
			while (lItr.hasNext()) {
				keys.remove(lItr.next());
			}
			if (keys.size() == 0) break;  // we don't have any keys left to check so we're done
		}

		return out;
	}
	
	
	
	// return the size of the word cloud
	int size() {
		return m_words.size();
	}
	
	// return the count of the specified word
	int count(String s) {
		if (m_words.containsKey(s)) {
			return m_words.get(s);
		} else {
			return 0;
		}
	}
}
