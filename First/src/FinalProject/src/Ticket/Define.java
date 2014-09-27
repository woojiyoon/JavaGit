package ticket;

public class Define {
	
	/**************************************************************************
	   
		지도를 가중치 인접 행렬의 배열 형태로 나타내기
		(여기서의 가중치는 두 정점간의 거리값임)

 	*************************************************************************/
	public static final int TotalNumberOfCity = 7;	// 도시의 총 갯수, 정점의 총 갯수
	public static final int INF = 1000;				// 무한대(정점 간에 연결이 없는 경우, 즉 1번만에 연결 안되는 상태)
	public static int[][] weight = new int[][] {	// 가중치 인접 행렬
		{0,   16,  22,  20,  INF, 29,  INF},
		{16,  0,   28,  INF, 31,  INF, INF},
		{22,  28,  0,   INF, 32,  23,  INF},
		{20,  INF, INF, 0,   INF, 35,  25},
		{INF, 31,  32,  INF, 0,   15,  18},
		{29,  INF, 23,  35,  15,  0,   12},
		{INF, INF, INF, 25,  18,  12,  0}
	};
	
	
	

	
	/**************************************************************************
	  
	 							열차역 도시 이름 목록
	 
	 *************************************************************************/
	public static final int Seoul = 0;
	public static final int Chuncheon = 1;
	public static final int Wonju = 2;
	public static final int Asan = 3;
	public static final int Gyeongju = 4;
	public static final int Deajeon = 5;
	public static final int Gwangju = 6;
	
	
}
