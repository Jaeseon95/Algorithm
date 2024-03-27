import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 미로 속에 있다. 미로는 직사각형 모양이고 여행길을 떠나기 위해 미로를 탈출하려고 한다. 
 * 빈칸 : ., 벽: # (이동불가), 
 * 열쇠 : 언제나 이동 할 수 있다. 이곳에 처음 들어가면 열쇠를 집는다. ('a', 'b', 'c', 'd,' 'e', 'f') 
 * 문: 'A', 'B','C','D','E','F' 
 * 민식의 현재 위치 '0', 출구 달이 차오르기 때문에 민식이가 가야하는 곳 '1'
 * =========================================================================
 * 민식의 열쇠 보유 상황을 가질 자료를 비트로 표현 111111 
 * a:0, b:1, c:2, d:3, e:4, f:5 
 * 초기 보유상황 0 해당 문자열 일 때 해당 비트 플립 
 * 열쇠를 보유하지 않은 상황 0 , 모든 열쇠를 보유한 상황  63
 * 최단 거리? => bfs 
 * 
 * visit에 필요한 경우를 key의 개수만큼 배열 생성 해당 키를 들고 방문한 이력이 있으면 종료 
 * @author jaeseon.park
 */
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static final int NUM_STATE = 1<<6;

	static int rowSize, colSize;

	static char[][] map;
	static boolean[][][] visited;
	static int[] startPoint = new int[2];
	
	// 델타 배열 
	static final int[] D_ROW = { -1, 1, 0, 0};
	static final int[] D_COL = { 0, 0, -1, 1};
	
	// 최소 이동횟수 기록할 변수 선언 
	static int minStep;
	// bfs를 위한 큐
	
	//방문 가능한지 확인을 위한 함수
	private static boolean isValidPos(int row, int col) {
		if (row < 0 || col < 0 || row >= rowSize || col >= colSize) return false;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());

		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		map = new char[rowSize][colSize];

		visited = new boolean[NUM_STATE][rowSize][colSize];

		minStep = Integer.MAX_VALUE;
		int keyState = 0;
		// 맵에 값을 할당하고 시작점 결정 
		for (int row = 0; row < rowSize; row++) {
			String info  = br.readLine().trim();
			for (int col =0; col <colSize; col++) {
				char val = info.charAt(col);
				if(val == '0') {
					startPoint[0] = row;
					startPoint[1] = col;
					val = '.';
				}
				map[row][col] = val;
			}
		}
		
		minStep = Integer.MAX_VALUE;

		Queue<Info> queue = new ArrayDeque<>();
		Info info = new Info(startPoint[0], startPoint[1], keyState, 0);
		// 방문 처리 
		visited[keyState][startPoint[0]][startPoint[1]] = true;
		queue.offer(info);
		while(!queue.isEmpty()) {
			info = queue.poll();
			int row = info.row;
			int col = info.col;
			keyState = info.keyState;
			int step = info.step;

			// 4방 탐색 수행 
			for (int dirIdx =0 ; dirIdx < D_ROW.length; dirIdx++) {
				int tmpRow = row + D_ROW[dirIdx];
				int tmpCol = col + D_COL[dirIdx];

				// 유효한 위치라면 
				if (isValidPos(tmpRow, tmpCol)) {
					// 해당 위치를 방문한 적이 있다면?
					if (visited[keyState][tmpRow][tmpCol]) continue;
					// 없는 경우 해당 위치의 값에 따라 다르게 동작 
					char val = map[tmpRow][tmpCol];
					if (val == '#') { // 벽인 경우 이동하지 않고 넘어간다. 
						continue;
					}else if (val >= 'a' && val <= 'f') { //열쇠인 경우 해당 keystate를 변경해서 넘겨준다.
						int keyNumber = 1 << (val - 'a');
						int tmpKeyState = keyState | keyNumber;
						info = new Info(tmpRow, tmpCol, tmpKeyState, step + 1);
						visited[tmpKeyState][tmpRow][tmpCol] = true;
						queue.offer(info);
					}else if (val >= 'A' && val <= 'F') {
						int doorNumber = 1 << (val - 'A');
						if ((keyState & doorNumber) != 0) {
							info = new Info(tmpRow, tmpCol, keyState, step + 1);
							visited[keyState][tmpRow][tmpCol] = true;
							queue.offer(info);
						}
					}else if (val == '.') {// 평지인 경우 
						info = new Info(tmpRow, tmpCol, keyState, step + 1);
						visited[keyState][tmpRow][tmpCol] = true;
						queue.offer(info);
					}else if  (val == '1') {// 도착지에 도착한 경우 
						minStep = (minStep > step + 1)? step + 1: minStep;
                        queue.clear();
					}
				}
			}
		}
		System.out.println((minStep == Integer.MAX_VALUE)? -1 : minStep);
	}
	
	static class Info{
		int row, col;
		int keyState, step;

		public Info(int row, int col, int keyState, int step) {
			super();
			this.row = row;
			this.col = col;
			this.keyState = keyState;
			this.step = step;
		}
	}

}
