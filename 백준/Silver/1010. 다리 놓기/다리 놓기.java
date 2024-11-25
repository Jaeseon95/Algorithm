import java.util.*;
import java.io.*;

/**
 * 서쪽 N개 동쪽 M개 
 * 1
 * 11
 * 121
 * 1331
 * 14641
 */

public class Main {
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int testCase;
	static final int MAX_NUM = 30;
	static int leftNum, rightNum;
	
	static int[][] combMatrix;
	
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 한 줄 씩 읽는다. 공백을 기준으로 토크나이징
		st = new StringTokenizer(br.readLine().trim());
		
		testCase = Integer.parseInt(st.nextToken());
		

		combMatrix = new int[MAX_NUM + 1][MAX_NUM + 1];
		combMatrix[1][0] = 1;
		combMatrix[1][1] = 1;
		for (int rIdx = 2; rIdx <= MAX_NUM; rIdx++) {
			combMatrix[rIdx][0] = 1;
			for (int lIdx = 1; lIdx <= MAX_NUM; lIdx++) {
				combMatrix[rIdx][lIdx] =  combMatrix[rIdx - 1][lIdx - 1] + combMatrix[rIdx-1][lIdx];  
			}
		}

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			leftNum = Integer.parseInt(st.nextToken());
			rightNum = Integer.parseInt(st.nextToken());
			sb.append(combMatrix[rightNum][leftNum]).append("\n");
		}
		System.out.println(sb);
	}
}
