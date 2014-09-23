package ticket;

/********************************************************************
  
  	������(start)���� ������(destination)������ �ּҰ�� ���� ������ �ִ� Ŭ����
  	�ּҰ�θ� ���ϴ� ����� Dijkstra �˰����� �̿���.
  
 *******************************************************************/

public class ShortPath {
	public final int INT_MAX = 2147483647;	// �ִ� ����
	public final int TRUE = 1;
	public final int FALSE = 0;
	public final int MAX_VERTICES = 7;		// ������ ��(������ ��)
	
	int[] distance = new int[MAX_VERTICES];	// ���� �������κ����� �ִ� ��� �Ÿ��� �����ϴ� �迭
	int[] found = new int[MAX_VERTICES];	// �湮�� ���� ǥ��
	
	public ShortPath() { }
	
	public int selectMinPosition(int[] distance, int n, int[] found) { // n = MAX_VERTICES
		int i, min, minPosition;
		min = INT_MAX;
		minPosition = -1;
		for(i = 0; i < n; i++) {
			if(distance[i] < min && found[i] == 0) {	// �湮�� ������ �ƴ϶��
				min = distance[i];						// �ּҰ�� ����
				minPosition = i;
				}
		}
		return minPosition;
	}
	
	public int shortestPath(int start, int destination, int n) {
		int i;
		int u,w;	// ���� u -> ���� w
		
		for(i = 0; i < n; i++) {	// �ʱ�ȭ , n = MAX_VERTICES
			distance[i] = Define.weight[start][i];
			found[i] = FALSE;	// �湮�� ���� ����, 0(=FALSE)
		}
		
		found[start] = TRUE;	// ���� ���� �湮 ǥ��, 1(=TRUE)
		distance[start] = 0;	// ���� start -> start ���� �׻� 0
		
		for(i = 0; i < n-2; i++) {
			u = selectMinPosition(distance, n, found);
			found[u] = TRUE;	// ���� �湮 ǥ��, 1=(TRUE)
			for(w = 0; w < n; w++) { // w : 0 ~ MAX_VERTICES
				if(found[w] == 0) // �湮�� ���� �ƴ϶��
					if(distance[u] + Define.weight[u][w] < distance[w])
						distance[w] = distance[u] + Define.weight[u][w];
			}
		}
		
		return distance[destination];
	}
}
