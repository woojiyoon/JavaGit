package grades;

public class GradesMain {

	public static void main(String[] args) {
		StudentInfo stuBox[] = new StudentInfo[5];
		// StudentInfo("�̸�","����",��������,��������);
		
		stuBox[0] = new StudentInfo("�̽±�","Mathematics",95,20);
		stuBox[1] = new StudentInfo("�����","English",90,100);
		stuBox[2] = new StudentInfo("�ֿ�","Mathematics",76,60);
		stuBox[3] = new StudentInfo("����","Mathematics",85,89);
		stuBox[4] = new StudentInfo("�̹�ȣ","English",90,95);
				
		// automatically student number is increasing. Let's see
		System.out.println("<�ڵ������� ������ �й� ����>");
		for(int i = 0; i < stuBox.length; i ++){
			System.out.println(stuBox[i].getStudentName() + ": " + stuBox[i].getStudentNumber());
		}
		
		System.out.println(); // for blank
		
		// Let's see the grade of Mathematics
		System.out.println("<�л����� ������ ���� ���� ��� ����>");
		for(int i = 0; i < stuBox.length; i++){
			stuBox[i].setMathGrade(stuBox[i].getMathScore());
			System.out.println(stuBox[i].getStudentName() + ": " + stuBox[i].getMathGrade());
		}
		
		System.out.println(); // for blank
		
		// Let's see the grade of English
		System.out.println("<�л����� ������ ���� ���� ��� ����>");
		for(int i = 0; i < stuBox.length; i++){
			stuBox[i].setEngGrade(stuBox[i].getEngScore());
			System.out.println(stuBox[i].getStudentName() + ": " + stuBox[i].getEngGrade());
		}
	}

}
