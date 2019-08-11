/**
 * 
 */
package info.thomasstephens;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
