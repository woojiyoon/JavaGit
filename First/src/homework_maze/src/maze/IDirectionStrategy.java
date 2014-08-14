package maze;

public interface IDirectionStrategy {
	public static final int maxX = 7, maxY = 8; // arrange of X,Y point
	
	public abstract void escapeFromMaze(MazeMap givenMap) throws InterruptedException; // escape from the maze
	public abstract void showTheMap(MazeMap givenMap) throws InterruptedException;  // display the map
	public abstract boolean validPoint(int x, int y); // check out the arrange of X,Y position
	public abstract void goBack(); // go back by using pop method of stack
}
