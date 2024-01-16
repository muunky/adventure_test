package adventure_test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

// date/time started : 16/01 . 8h51
public class playing {
	
	
	// reading and storing map in map[][] variable
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
	
	// spliting string and returning the needed char
	public static String read_string(String path, int i) {
		String[] path_arr = path.split("");;
		return path_arr[i];
	}
	
	// reding input and switch to change character data according
	public static int[] movement(String path, int[] character) {
		switch (path) {
		case "N":
		    	character[1] = character[1] -1;
		    break;
		case "E":
				character[0] = character[0] + 1;
		    	break;
		case "O":
				character[0] = character[0] - 1;
		    break;
		case "S":
				character[1] = character[1] + 1;
		    break;
		}
		return character;
	}
	
	// creating character
	public static int[] character(int x, int y) {
		int[] character = {x,y};
		return character;
	}
	
	public static void test(String path,int[] result) {
		char[][] map = create_map_array(); // using funct to create map[][]
		
		// initializing the variable needed
		int[] character = character(3,0);
		String move_needed = "";
		
		for (int i = 0; i < path.length(); i++) {
			
			move_needed = read_string(path,i);
			character = movement(move_needed,character);
			map[character[0]][character[1]] = 'x'; // putting the path as the character move
			
					
		}
		map[character[0]][character[1]] = 'p'; // placing a "p" where the character end up
	    for (int i = 0; i < 19 ; i++) {
	    	System.out.println(Arrays.toString(map[i])); // putting the map in the console
	    }
	    System.out.println("position attendue : 9 / 2");
	    System.out.println("position obtenue : " + character[0] + " / " + character[1]);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// test 1
		int[] goal = {9,2};
		test("SSSSEEEEEENN",goal);
	}

}
