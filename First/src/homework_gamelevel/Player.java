package homework_gamelevel;

public class Player {
	static int levelCount = 0; // 생성된 player 인스턴스의 수를 저장하기 위한 변수
	int levelNo;	// player 인스턴스 고유의 넘버
	{
		++levelCount;		// player 인스턴스가 생성될 때마다 levelCount 값을 1씩 증가시켜
		levelNo = levelCount; // levelNo에 저장한다.그래서 각 인스턴스마다 다른 값 가짐.
	}

	public void upgradeLevel(){ // 현재 GameLeve(1~3)을 출력하는 함수
		System.out.println("========level " + getGameLevel() + " start==========");
	}
	
	public int getGameLevel(){ // 현재 GameLevel(1~3)을 반환하는 함수
		switch(levelCount){
			case 1: return GameLevel_1.getInstance().levelOfGame;
			case 2: return GameLevel_2.getInstance().levelOfGame;
			case 3: return GameLevel_3.getInstance().levelOfGame;
			default: System.out.println("GameLevel의 범위가 1~3을 벗어났습니다.");
					return 0;
		}
	}
	
	public void attack(GameLevel obj){ // attack()함수로 각 레벨의 공격이 발생
		obj.play(); // 조상 클래스의 template method를 사용한다.
		System.out.println("========level " + getGameLevel() + " end============");
		System.out.println();
	}
}
