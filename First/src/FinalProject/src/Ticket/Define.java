package ticket;

public class Define {
	
	/**************************************************************************
	   
		������ ����ġ ���� ����� �迭 ���·� ��Ÿ����
		(���⼭�� ����ġ�� �� �������� �Ÿ�����)

 	*************************************************************************/
	public static final int TotalNumberOfCity = 7;	// ������ �� ����, ������ �� ����
	public static final int INF = 1000;				// ���Ѵ�(���� ���� ������ ���� ���, �� 1������ ���� �ȵǴ� ����)
	public static int[][] weight = new int[][] {	// ����ġ ���� ���
		{0,   16,  22,  20,  INF, 29,  INF},
		{16,  0,   28,  INF, 31,  INF, INF},
		{22,  28,  0,   INF, 32,  23,  INF},
		{20,  INF, INF, 0,   INF, 35,  25},
		{INF, 31,  32,  INF, 0,   15,  18},
		{29,  INF, 23,  35,  15,  0,   12},
		{INF, INF, INF, 25,  18,  12,  0}
	};
	
	
	

	
	/**************************************************************************
	  
	 							������ ���� �̸� ���
	 
	 *************************************************************************/
	public static final int Seoul = 0;
	public static final int Chuncheon = 1;
	public static final int Wonju = 2;
	public static final int Asan = 3;
	public static final int Gyeongju = 4;
	public static final int Deajeon = 5;
	public static final int Gwangju = 6;
	
	
}
