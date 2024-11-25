import java.util.*;
import java.io.*;

public class Main {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static final int[] D_ROW = {-1, 0, 1, 0};
	static final int[] D_COL = {0, 1, 0, -1};
	
	static int testCase;
	static int rowSize, colSize, numPos;
	static int[][] map;
	static boolean[][] visited;
	
	public static boolean isValid(int row, int col) {
		return !(row < 0 || col <0 || row >= rowSize || col >= colSize);
	}
	
	public static void dfs(int row, int col) {
		visited[row][col] = true;
		int tmpRow, tmpCol;
		for (int idx = 0; idx < 4; idx++) {
			tmpRow = row + D_ROW[idx];
			tmpCol = col + D_COL[idx];
			if (isValid(tmpRow, tmpCol)) {
				if (map[tmpRow][tmpCol] == 1 && !visited[tmpRow][tmpCol]) {
					dfs(tmpRow, tmpCol);
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		
		testCase = Integer.parseInt(st.nextToken()); 
		
		int row, col;
		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine().trim());

			colSize = Integer.parseInt(st.nextToken());
			rowSize = Integer.parseInt(st.nextToken());
			numPos = Integer.parseInt(st.nextToken());

			map = new int[rowSize][colSize];
			visited = new boolean[rowSize][colSize];
			for (int idx = 0; idx < numPos; idx++) {
				st = new StringTokenizer(br.readLine().trim());
				col = Integer.parseInt(st.nextToken());
				row = Integer.parseInt(st.nextToken());
				map[row][col] = 1;
			}
			
			int numWorms = 0;
			for (int rIdx = 0; rIdx < rowSize; rIdx++) {
				for (int cIdx = 0; cIdx < colSize; cIdx++) {
					if (map[rIdx][cIdx] == 1 && !visited[rIdx][cIdx]) {
						numWorms++;
						dfs(rIdx, cIdx);
					}
				}
			}
			sb.append(numWorms).append("\n");
		}
		System.out.print(sb);
	}
}
