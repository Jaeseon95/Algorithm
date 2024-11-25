import java.util.*;
import java.io.*;

/**
 * N명의 아이들이 한 줄로 서서 1인승 놀이기구를 탄다.
 * M개의 놀이기구가 있으며 1~M번으로 번호가 있다.
 * 놀이기구 운행시간이 있어 지나면 탑승하고 있던 아이가 내린다.
 * 기구가 비어있으면 현재 줄에서 가장 앞에 서 있는 아이가 탑승한다.
 * 여러 개의 놀이기구가 동시에 빈다면 더 작은 번호를 탑승한다.
 * 줄의 마지막 아이가 타게되는 놀이 번호를 구하는 프로그램을 작성하시오
 * ===
 * [입력]
 * N과 M이 주어진다.
 */

public class Main {
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	
	static final long MAX_TIME = 30;
	static int numChildren, numRides;
	static int[] coolDownList;
	
	
	public static long getChildrenCnt(long time) {
		long cnt = 0; 
		for (int idx = 0; idx < numRides; idx++) {
			cnt += (time/coolDownList[idx]) + 1;
		}
		return cnt;
	}
	
	public static long getAnswer(long answer) {
		long total = getChildrenCnt(answer - 1);
		for (int idx = 0 ; idx < numRides; idx++) {
			if ((answer % coolDownList[idx]) == 0) {
				total++;
				if (total == numChildren) {
					return idx + 1;
				}
			}
		}
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		// 20억
		numChildren = Integer.parseInt(st.nextToken());
		// 1, 1만 
		numRides = Integer.parseInt(st.nextToken());
		
		coolDownList = new int [numRides];

		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < numRides; idx++) {
			coolDownList[idx] = Integer.parseInt(st.nextToken());
		}
		
		if (numChildren <= numRides) {
			System.out.print(numChildren);
			return;
		}
		
		long low = 0;
		long high = numChildren * MAX_TIME;
		while(low <= high) {
			long mid = low + (high - low)/2;
			long tmp = getChildrenCnt(mid);
			// 판별 로직 
			if (tmp < numChildren) {
				low = mid + 1;
			}else {
				high = mid - 1;
			}
		}
		System.out.println(getAnswer(low));
	}
}
