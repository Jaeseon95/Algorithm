import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * 셋 중 하나를 한다.
 * 1. 주식을 산다.
 * 2. 원하는만큼 주식을 판다.
 * 3. 아무것도 하지 않는다.
 *
 * 마지막날부터 온다.
 * 전날 가격이 최대 금액보다 높으면 갱신
 * 전날 가격이 최대 금액보다 낮으면 금액 차만큼 더해준다.
 */
public class Main {

    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        for (int tc = 0 ; tc < testCase; tc++){
            int numDays = Integer.parseInt(br.readLine());
            int[] costs = new int[numDays];
            st = new StringTokenizer(br.readLine().trim());
            for (int idx = 0; idx < numDays; idx++){
                costs[idx] = Integer.parseInt(st.nextToken());
            }

            int maxCost = costs[numDays - 1];
            long profit = 0;
            for (int idx = numDays - 2; idx >= 0 ; idx--){
                int current = costs[idx];
                if (current >= maxCost){
                    maxCost = current;
                    continue;
                }
                profit += (maxCost - current);

            }
            sb.append(profit).append("\n");
        }
        System.out.println(sb);
    }
}
