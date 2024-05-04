import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 근처 빵집에 파이프를 설치하여 훔치려고한다.
 * 빵집이 있는 곳은 R*C의 격자로 표현될 수 있다. 첫째 열은 근처 빵집의 가스관이고, 마지막 열은 원웅이의 빵집이다.
 * 빵집을 연결한는 파이프를 설치하려고할 때 건물이 있는 경우는 설치할 수 없다.
 * 모든 파이프라인은 첫째 열에서 시작해야하고 마지막 열에서 끝나야하고 각 칸은 오른쪽 오른쪽위 대각선 오른쪽 아래 대각선으로
 * 연결할 수 있으며 되도록 많이 연결하려고한다. 연결 할 수 있는 파이프 라인의 최대수를 구하시오 
 * 빈칸은 . 건물은 X로 주어진다. 
 * ------------------------------------------------------------------------
 * 백트래킹 => 이렇게 풀면 안됨 
 * 1. 우선적으로 위, 오른쪽, 아래 순서로 간다. 
 * 2. 실패했을 경우 이전으로 돌아간다. 
 * 3. 끝에 도달하면 1을 더하고 지금까지 온 모든 영역을 true로 칠한다.
 * -------------------------------------------------------------------------   
 * @author jaeseon.park
 */
public class Main{
	static BufferedReader br;
	static StringTokenizer st;
	
	//맵의 열과 행 
	static int rowSize, colSize;
	static char[][]maps;
	//최대 횟수
	static int maxOfWay;
	static boolean endSeq;
	//델타배열 (우상, 우 ,우하)
	static int[] deltaRow = {-1, 0, 1};
	static int[] deltaCol = {1, 1, 1};
	
	private static void searchPipePath(int row, int col) {
		// 마지막 행에 도달했다면 지금까지 살펴온 모든 flag를 true로 바꾼다.
		if (col == colSize-1) {
			//해당 구역을 막는다 .
			maps[row][col] ='x';
			//최대 횟수 증가
			maxOfWay++;
			//도달여부를 true로 변경
			endSeq = true;
			return;
		}

		for (int dirIdx = 0; dirIdx < 3 ; dirIdx++) {
			//만약 끝에 도달했다면 멈춘다.
			if (endSeq) {
				return;
			}
			//윗쪽부터 탐색을 수행한다. 
			int tmpRow = row + deltaRow[dirIdx];
			int tmpCol = col + deltaCol[dirIdx];
			if (tmpRow >= 0 && tmpRow < rowSize && maps[tmpRow][tmpCol] == '.') {
				//해당 구역을 막고 탐색수행
				maps[row][col] = 'x';
				searchPipePath(tmpRow, tmpCol);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		// 행과 열 크기 할당.
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		//최대값 초기화
		maxOfWay = 0;
		// 배열 초기화
		maps = new char[rowSize][colSize];
		for (int row = 0 ; row < rowSize; row++) {
			char[] line = br.readLine().trim().toCharArray();
			for (int col = 0 ; col < colSize; col++) {
				maps[row][col] = line[col];
			}
		}
		//각열마다 반복 
		for (int idx = 0; idx < rowSize; idx++) {
			endSeq = false;
			searchPipePath(idx, 0);
		}
		//최대의 수를 출력한다. 
		System.out.println(maxOfWay);
	}
}
