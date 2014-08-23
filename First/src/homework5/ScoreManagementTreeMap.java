package homework5;

import java.util.*;

public class ScoreManagementTreeMap {
	
	private TreeMap<Student, Score> studentMap;

	public ScoreManagementTreeMap() {
		this.studentMap = new TreeMap<Student, Score>(); // key:Score(class), value:Student(class)
	}
	
	public void addScore(Student student, Score score) {
		student.setScore(score);
		studentMap.put(student, score);
	}
	
	public void displayAllScore() {
		Iterator<Student> ir = studentMap.keySet().iterator();
		while(ir.hasNext()) {
			Student student = ir.next();
			Score score = studentMap.get(student);
			System.out.println("�л���ȣ:" + student.getstudentNumber() + ", "
					         + "�л��̸�:" + student.getStudentName() + ", "
					         + "����:" + score.getKorScore() + ", "
					         + "����:" + score.getMathScore() + ", "
					         + "����:" + score.getTotalScore());
		}
	}
}
