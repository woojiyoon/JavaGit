package homework5;

public class ScoreManagementTreeMapTest {

	public static void main(String[] args) {
		
		ScoreManagementTreeMap manager = new ScoreManagementTreeMap();
		
		Student student1 = new Student("이승기", "101");
		Student student2 = new Student("김수현", "102");
		Student student3 = new Student("주원", "103");
		Student student4 = new Student("이민호", "104");
		Student student5 = new Student("박찬진", "105");
		Student student6 = new Student("박찬호", "105");
		
		Score score1 = new Score("101",21,22);
		Score score2 = new Score("102",61,62);
		Score score3 = new Score("103",81,82);
		Score score4 = new Score("104",51,52);
		Score score5 = new Score("105",100,100);
		Score score6 = new Score("106",100,100);
		
		manager.addScore(student1, score1);
		manager.addScore(student2, score2);
		manager.addScore(student3, score3);
		manager.addScore(student4, score4);
		manager.addScore(student5, score5);
		manager.addScore(student6, score6);
		manager.displayAllScore();
	}

}
