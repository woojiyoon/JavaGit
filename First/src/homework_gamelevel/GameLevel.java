package homework_gamelevel;

public abstract class GameLevel{ // 추상클래스
	public abstract void simpleAttack(); 	// GameLevel_1,2,3 possible method
	public abstract void turnAttack();		// GameLevel_2,3 possible method
	public abstract void flyingAttack();	// GameLevel_3 possible method
	
	public final void play(){ // template method
		simpleAttack();
		turnAttack();
		flyingAttack();
	}
}

class GameLevel_1 extends GameLevel{
	static int levelOfGame = 1;
	// singleton
	private static GameLevel_1 GL1 = new GameLevel_1();
	private GameLevel_1(){
		//System.out.println("GameLevel_1 인스턴스가 생성되었습니다.");
	}
	
	public static GameLevel_1 getInstance(){
		if(GL1 == null)
			GL1 = new GameLevel_1();
		return GL1;
	}
	
	public void simpleAttack(){
		System.out.println("simple attack:메롱~~~");
	}
	public void turnAttack(){
		System.out.println("turn attack:못하지롱");
	}
	public void flyingAttack(){
		System.out.println("flying attack:이것도 못하지롱");
	}
}

class GameLevel_2 extends GameLevel{
	static int levelOfGame = 2;
	// singleton
	private static GameLevel_2 GL2 = new GameLevel_2();
	private GameLevel_2(){
		//System.out.println("GameLevel_2 인스턴스가 생성되었습니다."	);
	}
	
	public static GameLevel_2 getInstance(){
		if(GL2 == null)
			GL2 = new GameLevel_2();
		return GL2;
	}
	public void simpleAttack(){
		System.out.println("simple attack:메롱~메롱~");
	}
	public void turnAttack(){
		System.out.println("turn attack:돌려차기 쓍~~~");
	}
	public void flyingAttack(){
		System.out.println("flying attack:이건 못하지롱");
	}
}

class GameLevel_3 extends GameLevel{
	static int levelOfGame = 3;
	// singleton
	private static GameLevel_3 GL3 = new GameLevel_3();
	private GameLevel_3(){
		//System.out.println("GameLevel_3 인스턴스가 생성되었습니다.");
	}
	
	public static GameLevel_3 getInstance(){
		if(GL3 == null)
			GL3 = new GameLevel_3();
		return GL3;
	}
	
	public void simpleAttack(){
		System.out.println("simple attack:메롱~메롱~메롱");
	}
	public void turnAttack(){
		System.out.println("turn attack:뒤돌려차기 앞돌려차기");
	}
	public void flyingAttack(){
		System.out.println("flying attack:날라차기 휙~~~");
	}
}