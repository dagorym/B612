/**
 * 
 */
package info.thomasstephens;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @date Created: Aug 10, 2019
 * @date Modified: Aug 10, 2019
 * @author Tom Stephens
 */
public class WordListGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		System.out.println("Welcome to the word list generator.");
		/*
		 * Read in the input file.  Given more time, this should be implemented
		 * as a Parameter class that can parse the file with keywords and store
		 * the parameters in the object.  For now, I'm just implementing it as a
		 * static file that gets read with the following format:
		 *  - line 1 - name of drop list file or 'NONE' if no drop list specified
		 *  - line 2 - ordering option - frequency for alphabetical
		 *  - line 3 - limits of frequency filtering, first value is lower limit
		 *             second value is upper limit, a value of -1 means no limit
		 *  - line 4 - word start filter - a * means no filter
		 *  - line 5 - name of input file with text to parse - if text is to be
		 *             supplied in the input file this is set to NONE
		 *  - rest of file - text string to process if line 5 contains NONE,
		 *             otherwise empty
		 */
//		System.out.println("Reading input file: " + args[0]);
		File file = new File(args[0]); 
		BufferedReader br;
		String dropListFile = null;
		String ordering = null;
		int frequencyLimits[] = new int[2];
		String wordFilter = null;
		String dataFile = null;
		List<String> dataText = new ArrayList<String>();
		try {
			br = new BufferedReader(new FileReader(file));
			// read orbit state vector
			dropListFile = br.readLine();
//			System.out.println(dropListFile);
			ordering = br.readLine();
//			System.out.println(ordering);
			String limits = br.readLine();
			String vals[] = limits.split(" ");
			frequencyLimits[0]=Integer.parseInt(vals[0]);
			frequencyLimits[1]=Integer.parseInt(vals[1]);
//			System.out.println(frequencyLimits[0] + " " + frequencyLimits[1]);
			wordFilter = br.readLine();
//			System.out.println(wordFilter);
			dataFile = br.readLine();
//			System.out.println(dataFile);
			if (dataFile.equals("NONE")) {
//				System.out.println("Reading in data to parse");
				String line = br.readLine();
				while (line != null) {
					dataText.add(line);
					line = br.readLine();
				}
//				for (String temp : dataText) {
//					System.out.println(temp);
//				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Read in the droplist if specified
		DropList dl = null;
		if (!(dropListFile.equals("NONE"))){
//			System.out.println("Reading in drop list");
			dl = new DropList(dropListFile);
//			System.out.println(dl.printList());
		}
		
		// create word list from input parameters
		WordList wl = new WordList(dataFile,dl);
		// print out word cloud data based on filters
		// @todo This should probably print out to a file
		System.out.println(wl.print(ordering,frequencyLimits[0],frequencyLimits[1],wordFilter));

	}

}
