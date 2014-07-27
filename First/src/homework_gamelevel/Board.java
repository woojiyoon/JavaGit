package homework_gamelevel;

public class Board {

	public static void main(String[] args) {
		Player p1 = new Player();	// Board에서 Player를 생성하고
		p1.upgradeLevel();	// upgradeLevel함수로 GameLevel을 선택한다.
		p1.attack(GameLevel_1.getInstance()); // attack함수를 호출하면 각 레벨에서 공격이 이루어진다.
		
		Player p2 = new Player();
		p2.upgradeLevel();
		p2.attack(GameLevel_2.getInstance());
		
		Player p3 = new Player();
		p3.upgradeLevel();
		p3.attack(GameLevel_3.getInstance());
	}

}
