import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 스도쿠의 빈칸이 주어졌을 때 완성하는 프로그램을 작성하시오 
 * 남아 있는 숫자를 대입해 본다 이 때  해당 값이 스도쿠의 조건을 파괴하지 않는지 확인한다. 
 * 
 * @author jaeseon.park 
 *
 */
public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	
	static final int SIZE = 9;
	static int[][] board = new int[SIZE][SIZE];
	// 0 ~ 9번의 가로, 세로, 사각형 조건을 담을 행렬들 
	// row, col일 때 가로 row로 조회 세로 col로 조회 
	// idx = 3 * (row/3) +  (col/3)
	static int[][] horizon = new int[SIZE][SIZE + 1];
	static int[][] vertical = new int[SIZE][SIZE + 1];
	static int[][] square = new int[SIZE][SIZE + 1];
	
	static List<Blank> blankList = new ArrayList<>();
	static boolean isEnd = false;
	
	private static void dfs(int startIdx) {
		// 모든 빈칸을 메꿨으면 종료 
		if (startIdx == blankList.size() || isEnd) {
			isEnd = true;
			return;
		}
		
		Blank curBlank = blankList.get(startIdx);
		int row = curBlank.row;
		int col = curBlank.col;
		// 모든 값을 빈칸에 넣어본다. 
		for (int val = 1; val <= SIZE; val++) {

			int idx = 3 * (row/3) + (col/3);
			// 이미 값이 존재한다면 
			if (!addList(row, val, horizon)) continue;
			if (!addList(col, val, vertical)) {
				removeList(row,  val, horizon);
				continue;
			}

			if (!addList(idx, val, square)) {
				removeList(row,  val, horizon);
				removeList(col, val, vertical);
				continue;
			}

			
			board[row][col] = val;
			dfs(startIdx + 1);
			if (isEnd) {
				return;
			}
			board[row][col] = 0;
			removeList(row, val, horizon);
			removeList(col, val, vertical);
			removeList(idx, val, square);
		}
	}
	
	static class Blank{
		int row, col;
		public Blank(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	
	private static boolean addList(int idx, int val, int[][] list) {
		if (list[idx][val] != 0) {
			return false;
		}else {
			list[idx][val]++; 
			return true;
		}
	}
	
	private static void removeList(int idx,  int val, int[][] list) {
			list[idx][val] = 0; 
	}

	

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		for (int row = 0; row < SIZE; row++) {
			String numbers = br.readLine().trim();
			for (int col = 0; col < SIZE; col++) {
				int val = numbers.charAt(col) - '0';
				board[row][col] = val;
				if (val != 0) {
					addList(col,  val, vertical);
					addList(row, val, horizon);
					int idx = 3 * (row/ 3) + (col/3);
					addList(idx, val, square);
				}else {
					Blank blank = new Blank(row, col);
					blankList.add(blank);
				}
			}
		}
		
		dfs(0);
		for (int row = 0; row <SIZE; row++) {
			for (int col = 0; col <SIZE; col++) {
				sb.append(board[row][col]);
			}
            sb.append("\n");
		}
		System.out.println(sb);
	}

}
