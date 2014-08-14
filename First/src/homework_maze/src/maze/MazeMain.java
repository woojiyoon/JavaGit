package maze;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MazeMain {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		MazeMap mazeMap = new MazeMap(); // create an instance of map class
				
		Properties props = new Properties();
		FileInputStream in = new FileInputStream("direction.txt"); // direction.txt file needs in the java project folder.
		props.load(in);
		String dbtype = props.getProperty("search_direction"); // check the word "search_direction" in direction.txt file
		
		IDirectionStrategy direction = new Direction_4(mazeMap.startX, mazeMap.startY); // when 4 direction algorithm
		
		if(dbtype.equals("8")){
			System.out.println("8 direction will start.");
			direction = new Direction_8(mazeMap.startX, mazeMap.startY); // when 8 direction algorithm
		}
		else
			System.out.println("4 direction will start.");
		
		direction.escapeFromMaze(mazeMap);
		
		
		/*
		// Test of stack method
		MyStack newStack = new MyStack(20); // make new stack
		newStack.push(20);	// push elements into stack
		newStack.push(40);
		newStack.push(60);
		newStack.push(80);
		
		while(!newStack.isEmpty()) {	// until stack is empty
			int value = newStack.pop();
			System.out.print(value); 	// display element of stack
			System.out.print(" ");
		} // end of while
		
		System.out.println("");
		*/
	}

}
