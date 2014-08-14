package maze;

public class Direction_8 implements IDirectionStrategy{

	public int currentX, currentY; // current Row, current Column point
	public int Side = 0; // east(2), south(3), west(4), north(5)
	// north-east(6), south-east(7), south-west(8), north-west(9)
	MyStack myStack = new MyStack(30);
	public static int stackPushCnt_8dir = 0; // how many times does the stack use push method
	
	public Direction_8(int startX, int startY) { // constructor of Direction_4
		this.currentX = startX;		// insert starting point when creating instance
		this.currentY = startY;
	}
	
	@Override
	public void escapeFromMaze(MazeMap givenMap) throws InterruptedException {
		
		while(true){
		
			if(validPoint(this.currentX, this.currentY) && givenMap.map[this.currentX][this.currentY] == 0) { // if current position is 0 (path, not wall)
				givenMap.map[this.currentX][this.currentY] = 2; // set current position(x,y) with 2 when you pass the current position
				showTheMap(givenMap); // display changes of the map
			}
			
			else if(this.currentX == MazeMap.destinationX && this.currentY == MazeMap.destinationY) { // if you arrive at the destination of maze, 
				
				System.out.println("Congratulation !!! You finally reached the destination.");
				System.out.println("Stack push count: " + stackPushCnt_8dir);
				break; // get out of while loop
			}
			
			else if(validPoint(this.currentX, this.currentY + 1) && givenMap.map[this.currentX][this.currentY + 1] == 0){ // check "east"(2)
				if(validPoint(this.currentX + 1, this.currentY + 1) && givenMap.map[this.currentX + 1][this.currentY + 1] == 0){ // check "south-east"(7)
					givenMap.map[this.currentX][this.currentY] = 2; // set current position(x,y) with 2 when you pass the current position
					this.currentY++;	
					this.currentX++;
					myStack.push(7);
					stackPushCnt_8dir++;
					showTheMap(givenMap); // display changes of the map
				}
				else if(validPoint(this.currentX - 1, this.currentY + 1) && givenMap.map[this.currentX - 1][this.currentY + 1] == 0){ // check "north-east"(6)
					givenMap.map[this.currentX][this.currentY] = 2; // set current position(x,y) with 2 when you pass the current position
					this.currentY++;	// position Y++;
					this.currentX--;
					myStack.push(6);
					stackPushCnt_8dir++;
					showTheMap(givenMap); // display changes of the map
				}
				else{
					givenMap.map[this.currentX][this.currentY] = 2; // set current position(x,y) with 2 when you pass the current position
					this.currentY++;	
					myStack.push(2);
					stackPushCnt_8dir++;
					showTheMap(givenMap); // display changes of the map
				}
			}
			else if(validPoint(this.currentX + 1, this.currentY) && givenMap.map[this.currentX + 1][this.currentY] == 0){ // check "south"(3)
				if(validPoint(this.currentX + 1, this.currentY + 1) && givenMap.map[this.currentX + 1][this.currentY + 1] == 0){ // check "south-east"(7)
					givenMap.map[this.currentX][this.currentY] = 2; // set current position(x,y) with 2 when you pass the current position
					this.currentY++;	
					this.currentX++;
					myStack.push(7);
					stackPushCnt_8dir++;
					showTheMap(givenMap); // display changes of the map
				}
				else if(validPoint(this.currentX + 1, this.currentY - 1) && givenMap.map[this.currentX + 1][this.currentY - 1] == 0){ // check "south-west"(8)
					givenMap.map[this.currentX][this.currentY] = 2; // set current position(x,y) with 2 when you pass the current position
					this.currentY--;	
					this.currentX++;
					myStack.push(8);
					stackPushCnt_8dir++;
					showTheMap(givenMap); // display changes of the map
				}
				else{
					givenMap.map[this.currentX][this.currentY] = 2; // set current position(x,y) with 2 when you pass the current position
					this.currentX++;	
					myStack.push(3);
					stackPushCnt_8dir++;
					showTheMap(givenMap); // display changes of the map
				}
			}
			else if(validPoint(this.currentX, this.currentY - 1) &&givenMap.map[this.currentX][this.currentY - 1] == 0){ // check "west"(4)
				if(validPoint(this.currentX + 1, this.currentY - 1) && givenMap.map[this.currentX + 1][this.currentY - 1] == 0){ // check "south-west"(8)
					givenMap.map[this.currentX][this.currentY] = 2; // set current position(x,y) with 2 when you pass the current position
					this.currentY--;	
					this.currentX++;
					myStack.push(8);
					stackPushCnt_8dir++;
					showTheMap(givenMap); // display changes of the map
				}
				else if(validPoint(this.currentX - 1, this.currentY - 1) && givenMap.map[this.currentX - 1][this.currentY - 1] == 0){ // check "north-west"(9)
					givenMap.map[this.currentX][this.currentY] = 2; // set current position(x,y) with 2 when you pass the current position
					this.currentY++;
					this.currentX++;
					myStack.push(9);
					stackPushCnt_8dir++;
					showTheMap(givenMap); // display changes of the map
				}
				else{
					givenMap.map[this.currentX][this.currentY] = 2; // set current position(x,y) with 2 when you pass the current position
					this.currentY--;	// 
					myStack.push(4);
					stackPushCnt_8dir++;
					showTheMap(givenMap); // display changes of the map
				}
			}
			else if(validPoint(this.currentX - 1, this.currentY) && givenMap.map[this.currentX - 1][this.currentY] == 0){ // check "north"(5)
				if(validPoint(this.currentX - 1, this.currentY - 1) && givenMap.map[this.currentX - 1][this.currentY - 1] == 0){ // check "north-west"(9)
					givenMap.map[this.currentX][this.currentY] = 2; // set current position(x,y) with 2 when you pass the current position
					this.currentY++;
					this.currentX++;
					myStack.push(9);
					stackPushCnt_8dir++;
					showTheMap(givenMap); // display changes of the map
				}
				else if(validPoint(this.currentX - 1, this.currentY + 1) && givenMap.map[this.currentX - 1][this.currentY + 1] == 0){ // check "north-east"(6)
					givenMap.map[this.currentX][this.currentY] = 2; // set current position(x,y) with 2 when you pass the current position
					this.currentY++;	// position Y++;
					this.currentX--;
					myStack.push(6);
					stackPushCnt_8dir++;
					showTheMap(givenMap); // display changes of the map
				}
				else{
					givenMap.map[this.currentX][this.currentY] = 2; // set current position(x,y) with 2 when you pass the current position
					this.currentX--;	// 
					myStack.push(5);
					stackPushCnt_8dir++;
					showTheMap(givenMap); // display changes of the map
				}
			}
			else {	// if all possible path is wall, then return to your past road by using the pop() method of stack
				this.goBack();
		}
	}
}

	@Override
	public void showTheMap(MazeMap givenMap) throws InterruptedException {
		// TODO Auto-generated method stub
		for(int i=0; i<8; i++){
			for(int j=0; j<10; j++){
				System.out.print(givenMap.map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		Thread.sleep(300);
	}

	@Override
	public boolean validPoint(int x, int y) { // check out the arrange of X,Y point
		if(x > maxX || y > maxY || x < 0 || y < 1)
			return false; // if point is not valid, then return false
		else
			return true; // if point is valid, then return true
	}

	@Override
	public void goBack() {
		Side = myStack.pop();// pop 하기
		switch(Side) {
			case 2: this.currentY--;	// move to west from east
					break;
			case 3: this.currentX--;	// move to north from south
					break;
			case 4: this.currentY++;	// move to east from west
					break;
			case 5: this.currentX++;	// move to south from north
					break;
			case 6: this.currentX++;	// 북서에서 남서로
					this.currentY--;
					break;
			case 7: this.currentX--;	// 남동에서 북서로
					this.currentY--;
					break;
			case 8: this.currentX--;	// 남서에서 북동으로
					this.currentY++;
					break;
			case 9: this.currentX++;	// 북서에서 남동으로
					this.currentY++;
					break;
			default: break;
		}
		
	}

}
