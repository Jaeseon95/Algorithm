import java.util.*;
import java.io.*;

public class Main {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	
	// 상, 우, 하, 좌
	static final int[] D_ROW= {-1, 0, 1, 0};
	static final int[] D_COL= {0, 1, 0, -1};
	
	static final int BLANK = -1;
	
	static int rowSize, colSize;
	static int[][] map;
	
	static int minDay;
	
	static class Pos{
		int row;
		int col;
		int day;

		public Pos(int row, int col, int day) {
			this.row = row;
			this.col = col;
			this.day = day;
		}
	}
	
	public static boolean checkTomato() {
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (map[row][col] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		

		colSize = Integer.parseInt(st.nextToken());
		rowSize = Integer.parseInt(st.nextToken());
		
		map = new int[rowSize][colSize];
		
		minDay = 0;

		//bfs
		Queue<Pos> queue = new ArrayDeque<>();

		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < colSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if (map[row][col] == 1) {
					queue.offer(new Pos(row, col, 0));
				}
			}
		}
		
		if (checkTomato()) {
			System.out.println(minDay);
			return;
		}
		
		int curRow, curCol;
		int tmpRow, tmpCol;
		while(!queue.isEmpty()) {
			Pos current = queue.poll();
			curRow = current.row;
			curCol = current.col;
			minDay = current.day;
			for (int pIdx = 0; pIdx < 4; pIdx++) {
				tmpRow = curRow + D_ROW[pIdx];
				tmpCol = curCol + D_COL[pIdx];
				if (tmpRow < 0 || tmpCol < 0 || tmpRow >= rowSize || tmpCol >= colSize) {
					continue;
				}
				if(map[tmpRow][tmpCol] == 0){
					queue.offer(new Pos(tmpRow, tmpCol, minDay + 1));
					map[tmpRow][tmpCol] = 1;
				}
			}
		}

		if (!checkTomato()) {
			System.out.println(-1);
			return;
		}

		System.out.println(minDay);
	}
}
