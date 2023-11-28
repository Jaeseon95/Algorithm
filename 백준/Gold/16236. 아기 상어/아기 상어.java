import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. 상어는 자기 자신 보다 큰 크기의 물고기가 있는 구역은 지나갈 수 없다.
 * 2. 상어는 몸집 크기만큼 먹이를 먹으면 크기가 1증가한다.
 * 3. 상어는 먹을 수 있는 가장 가까운 물고기를 먹으러 가는데 같은 거리라면 우선순위는 가장 위에있을 수록 같은 위치라면 왼쪽에 있을 수록 우선적으로 먹는다.
 * 4. 더이상 먹을 수 있는 물고기가 없다면 엄마의 도움을 요청한다.
 * 5. 도움을 요청할 때 까지 걸린 시간을 구하시오 이때 1칸을 이동하는데 필요한 시간은 1초이다. 
 * --------------------------------------------------------------------
 * 상어의 위치는 9, 빈칸은 0, 물고기의 크기는 1~6으로 주어진다.  
 * 1. bfs로 같은 거리를 전부 탐색 => 가장 위에 있는 녀석으로 이동하고 그 이동칸을 0으로 바꾼다. 
 * 2. 이동한 칸까지 걸린 시간을 더해준다. 
 * @author jaeseon.park
 */
public class Main{
	// 입출력을 위한 클래스 호출 
	static BufferedReader br;
	static StringTokenizer st;
	// 상어의 크기, 먹은 개수를 측정하기 위한 변수 선언
	static int sharkSize, feedStack;
	// 상어가 엄마를 부르는데 걸린시간을 확인하기 위한 변수 선언 
	static int usingTime;
	//bfs를 위한 큐 선언
	static Queue<Node> Qu = new LinkedList<>();
	// 후보 Node를 담기 위한 arrayList 선언
	static List<Node> candidate = new ArrayList<>();
	// 공간을 담기위한 2차원 배열과 방문 확인을 위한 flag배열 생성 
	static int[][] maps; 
	static boolean[][] visited; 
	static int mapSize;
	// 탐색하고 있는 Distance를 확인하기 위한 변수
	static int checkDistance;
	// 델타 배열 상 하 좌 우 
	static int[] deltaRow = {-1,0,0, 1};
	static int[] deltaCol= {0,-1,1, 0};

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입출력을 위한 객체 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		// 2차원 배열의 크기를 받아주고 배열 초기화 
		mapSize = Integer.parseInt(br.readLine().trim());
		maps = new int[mapSize][mapSize];
		visited = new boolean[mapSize][mapSize];
		// 값을 입력 받으면서 만약 아기상어의 위치라면 해당 영역을 큐에 삽입 하고 값을 0으로 바꾼다. 
		for (int row = 0; row < mapSize; row++) {
			// 주어진 입력 값을 받아준다. 
			st = new StringTokenizer(br.readLine().trim());
			for (int col= 0; col< mapSize; col++) {
				// 해당 영역의 값을 받고 9인경우 큐에 삽입
				int value = Integer.parseInt(st.nextToken());
				maps[row][col] = value;
				// 아기 상어의 위치인 경우 
				if (value == 9) {
					// 0으로 변경 
					maps[row][col] = 0;
					// 해당 위치를 큐에 삽입
					Qu.offer(new Node(row, col, 0));
					// 해당 위치 방문처리
					visited[row][col] = true;
				}
			}
		}
		// 거리 확인을 위한 변수 초기화 
		checkDistance = 0;
		usingTime = 0;
		//상어 크기와 먹이 스택 초기화
		sharkSize = 2;
		feedStack = 2;
		// 상어의 위치를 받았으니 bfs 수행 
		// 큐가 빌때까지 반복 
		while (!Qu.isEmpty()||!candidate.isEmpty()) {
			// 만약 후보가 비어있지 않고 다음 큐가 비어있거나 거리가 다른경우 
			if (!candidate.isEmpty() && (Qu.isEmpty()||checkDistance != Qu.peek().getDistance())) {
				// 좌상단이 최우선이 되도록 정렬 
				Collections.sort(candidate);
				//해당 노드를 선택하고 
				Node targetNode = candidate.get(0);
				//해당 거리를 소모된 시간에 더해준다.
				usingTime += targetNode.getDistance();
				//큐와 배열 초기화 
				Qu.clear();
				candidate.clear();
				//방문 배열 초기화 
				//해당영역의 값 0으로한다.
				visited = new boolean[mapSize][mapSize];
				visited[targetNode.getRow()][targetNode.getCol()] = true;
				maps[targetNode.getRow()][targetNode.getCol()] = 0;
				//Q에 현재 노드 추가 .
				targetNode.setDistance(0);
				Qu.offer(targetNode);
				//먹이 스택 감소 후 상어 사이즈 증가 
				feedStack--;
				if (feedStack == 0) {
					sharkSize++;
					feedStack =sharkSize;
				}
			}
			// 큐에서 뽑아 현재 노드를 받아준다.
			Node currentNode = Qu.poll();
			// 현재 row, col, distance를 받는다. 
			int curRow = currentNode.getRow();
			int curCol = currentNode.getCol();
			int curDistance = currentNode.getDistance();
			//사용하는 거리를 바꿔준다.
			checkDistance = curDistance;
			// 현재 위치를 기준으로 4방향 탐색 수행 
			for (int idx = 0; idx < 4; idx++) {
				//임시 우치와 임시 거리를 설정 
				int tmpRow = curRow + deltaRow[idx];
				int tmpCol = curCol + deltaCol[idx];
				int tmpDistance = curDistance + 1;
				//만약 해당 위치가 영역안이면서 상어의 크기보다 작거나 같은 경우 이동한다. 
				if(tmpCol >=0 && tmpRow >= 0 && tmpCol< mapSize && tmpRow < mapSize && maps[tmpRow][tmpCol] <= sharkSize && !visited[tmpRow][tmpCol]) {
					
					//만약 해당 구역이 0보다 크고 상어 크기보다 작은 경우 후보에 추가한ㄷ.
					if (maps[tmpRow][tmpCol] > 0 &&maps[tmpRow][tmpCol] < sharkSize) {
						candidate.add(new Node(tmpRow, tmpCol, tmpDistance));
						visited[tmpRow][tmpCol] = true;
					}else {
						// 그렇지 않은 경우 해당 위치를 큐에 추가.
						Qu.offer(new Node(tmpRow, tmpCol, tmpDistance));
						visited[tmpRow][tmpCol] = true;
					}
				}
			}
		}
		System.out.println(usingTime);
	}
}

class Node implements Comparable<Node>{
	private int row;
	private int col;
	private int distance;
	
	public Node(int row, int col, int distance) {
		setRow(row);
		setCol(col);
		setDistance(distance);
	}

	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public int compareTo(Node o) {
		// 만약 같은 열이라면 행을 오름차순 정렬 
		if (this.row == o.getRow()) {
			return this.col - o.getCol();
		}
		// 다른 경우라면 열을 기준으로 오름차순 정렬 
		return this.row - o.getRow();
	}
}