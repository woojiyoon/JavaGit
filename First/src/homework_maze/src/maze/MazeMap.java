package maze;

public class MazeMap {
	
	public static int destinationX = 7, destinationY = 8;
	public static int startX = 0, startY = 1;
	
	public int[][] map = { {1,0,1,1,1,0,1,1,1,1},
						   {1,0,0,0,1,0,0,0,0,1},
						   {1,1,1,0,0,0,1,0,1,1},
						   {1,1,1,0,1,1,1,0,1,1},
						   {1,1,0,0,1,0,0,0,0,1},
						   {1,0,1,1,1,0,1,1,1,1},
						   {1,1,0,1,1,0,0,0,0,1},
						   {1,0,1,1,0,1,1,1,0,1} };
	
	public MazeMap(){}
}
