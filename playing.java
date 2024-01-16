package adventure_test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.logging.Level;
import java.util.logging.Logger;

// date/time started : 16/01 . 8h51
public class playing {
	public static char[][] create_map_array() {
		int numberOfLines = 0;    
	    try {
	        LineNumberReader lineNumberReader = new LineNumberReader(new FileReader("map.txt"));  // read the file 
	        lineNumberReader.skip(Long.MAX_VALUE); // jump to end of file 
	        numberOfLines = lineNumberReader.getLineNumber(); // return line number at end of file
	        lineNumberReader.close();
	    } catch (IOException ex) {
	        Logger.getLogger(Character.class.getName()).log(Level.SEVERE, null, ex);
	    }

	    // create array
	    char[][] map = new char[numberOfLines][];   // create a 2D char[][] with as many char[] as there is lines
	    
	    // read the file line by line and put it in the array
	    try (BufferedReader bufferedReader = new BufferedReader(new FileReader("map.txt"))) {
	        int i = 0;
	        String line = bufferedReader.readLine();   // read the first line
	        
	        while (line != null) {
	            map[i++] = line.toCharArray();   // convert the read line to an array and put it in your char[][]
	            
	            line = bufferedReader.readLine(); // read the next line
	        }
	    } catch (IOException ex) {
	        Logger.getLogger(Character.class.getName()).log(Level.SEVERE, null, ex);
	    }
		return map;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
