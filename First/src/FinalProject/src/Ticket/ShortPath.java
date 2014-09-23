package ticket;

/********************************************************************
  
  	시작점(start)에서 도착점(destination)까지의 최소경로 값을 리턴해 주는 클래스
  	최소경로를 구하는 방법은 Dijkstra 알고리즘을 이용함.
  
 *******************************************************************/

public class ShortPath {
	public final int INT_MAX = 2147483647;	// 최대 정수
	public final int TRUE = 1;
	public final int FALSE = 0;
	public final int MAX_VERTICES = 7;		// 정점의 수(도시의 수)
	
	int[] distance = new int[MAX_VERTICES];	// 시작 정점으로부터의 최단 경로 거리를 저장하는 배열
	int[] found = new int[MAX_VERTICES];	// 방문한 정점 표시
	
	public ShortPath() { }
	
	public int selectMinPosition(int[] distance, int n, int[] found) { // n = MAX_VERTICES
		int i, min, minPosition;
		min = INT_MAX;
		minPosition = -1;
		for(i = 0; i < n; i++) {
			if(distance[i] < min && found[i] == 0) {	// 방문한 정점이 아니라면
				min = distance[i];						// 최소경로 갱신
				minPosition = i;
				}
		}
		return minPosition;
	}
	
	public int shortestPath(int start, int destination, int n) {
		int i;
		int u,w;	// 정점 u -> 정점 w
		
		for(i = 0; i < n; i++) {	// 초기화 , n = MAX_VERTICES
			distance[i] = Define.weight[start][i];
			found[i] = FALSE;	// 방문한 정점 없음, 0(=FALSE)
		}
		
		found[start] = TRUE;	// 시작 정점 방문 표시, 1(=TRUE)
		distance[start] = 0;	// 정점 start -> start 값은 항상 0
		
		for(i = 0; i < n-2; i++) {
			u = selectMinPosition(distance, n, found);
			found[u] = TRUE;	// 정점 방문 표시, 1=(TRUE)
			for(w = 0; w < n; w++) { // w : 0 ~ MAX_VERTICES
				if(found[w] == 0) // 방문한 곳이 아니라면
					if(distance[u] + Define.weight[u][w] < distance[w])
						distance[w] = distance[u] + Define.weight[u][w];
			}
		}
		
		return distance[destination];
	}
}
