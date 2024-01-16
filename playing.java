package adventure_test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;
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

	// reading input and switch to change character data according
	public static int[] movement(char[][] map, String path, int[] character,int[] goal) {
		
		// !character.equals(goal) give true even when should be false
		// eclipse bug ? treat && as a || , cannot if(character[0] != goal[0]) && (character[1] != goal[1])
		// create if in if to pass the encountered issued
		// deleted additional if, created two new boolean to compare
		
		boolean x = (character[0] == goal[0]);
		boolean y = (character[1] == goal[1]);
		if (x == false || y == false) {
				char line[] = map[character[1]];
				
		        String[] current_line = String.copyValueOf(line).split("");
			    String[] next_line = String.copyValueOf(map[character[1] +1 ]).split("");
		    	//filling string with # in case of first line , avoiding OOB and declaring the variable
		    	String[] previous_line = String.copyValueOf(line).split("");
		    	int i = 0;
		    	previous_line[i] = previous_line[i].replace(' ', '#');
			    // check for out of boundaries on map[]
			    if (character[1] > 0) {
			    	previous_line = String.copyValueOf(map[character[1] -1 ]).split("");
			    }	    
			    
			    byte[] ascii = {};
			    switch (path) {
			    case "S" :
			    	ascii = next_line[character[0]].getBytes(StandardCharsets.US_ASCII);
			    		if (ascii[0] != 35) {
			    			character[1] = character[1] + 1;
			    		}
			    	break;
		
			    case "N" :
			    	ascii = previous_line[character[0]].getBytes(StandardCharsets.US_ASCII);
			    		if (ascii[0] != 35) {	    			
			    			character[1] = character[1] -1;
			    		}
			    	break;
			    case "E":
			    	ascii = current_line[character[0]].getBytes(StandardCharsets.US_ASCII);
			    		if (ascii[0] != 35) {
			    			character[0] = character[0] +1;
			    		}
			    	break;
			    case "O":
			    	ascii = current_line[character[0]].getBytes(StandardCharsets.US_ASCII);
			    		if (ascii[0] != 35) {
			    			character[0] = character[0] - 1;
			    		}
			    	break;
			}
	    } 
		return character;
	}
	
	// creating character
	public static int[] character(int x, int y) {
		int[] character = {x,y};
		return character;
	}
	
	public static void test(String path,int[] result,int[] character) {
		char[][] map = create_map_array(); // using funct to create map[][]
		
		// initializing the variable needed
		
		String move_needed = "";
		
		for (int i = 0; i < path.length(); i++) {
			
			move_needed = read_string(path,i);
			map[character[1]][character[0]] = 'x'; // putting the path as the character move
			character = movement(map,move_needed,character, result);	
		}
		System.out.println(Arrays.toString(character) + " char");
		map[character[1]][character[0]] = 'p'; // placing a "p" where the character end up
	    for (int i = 0; i < 19 ; i++) {
	    	char line[] = map[i];
	        String current_line = String.copyValueOf(line);
	    	System.out.println(current_line); // putting the map in the console
	    }
	    System.out.println("position attendue : " + result[0] + " / " + result[1] );
	    System.out.println("position obtenue : " + character[0] + " / " + character[1]);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// test 1
		int[] goal_1 = {9,2};
		int[] character = character(3,0); 
		test("SSSSEEEEEENN",goal_1,character);
		
		//test 2
		int[] goal_2 = {1,9};
		character = character(6,7);
		test("OONOOOSSO", goal_2,character);
	}

}
