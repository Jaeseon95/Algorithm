import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 배낭에 최대한 가치 있게 싸가려한다.
 * 여행에 필요하다고 생각하는 N개의 물건이 있다. 각 물건은 무게  W와 가치 V를 가지는데
 * 해당 물건을 배낭에 넣어서 가면 V만큼 즐길 수 있을 때
 * 최대 K 만큼 넣을 수 잇을 때 최대의 V가 되는 물건들의 가치의 최댓값을 알려주어라
 */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static int numItem;
    static int maxWeight;
    static int[] dp;
    static int[][] items;

    public static void main(String[] args) throws Exception {
        // 입력
        getInput();
    }

    private static void getInput() throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());

        numItem = Integer.parseInt(st.nextToken());
        maxWeight = Integer.parseInt(st.nextToken());

        dp = new int [maxWeight + 1];
        items = new int[numItem][2];

        for (int idx = 0; idx < numItem; idx++){
            st = new StringTokenizer(br.readLine().trim());
            items[idx][0] = Integer.parseInt(st.nextToken());
            items[idx][1] = Integer.parseInt(st.nextToken());
        }

        for (int idx = 0; idx < numItem; idx++){
            int itemWeight = items[idx][0];
            int itemValue = items[idx][1];

            for (int weight = maxWeight - itemWeight; weight >= 0; weight--){
                dp[weight + itemWeight] = Math.max(dp[weight] + itemValue, dp[weight + itemWeight]);
            }
        }

        int maxValue = 0;
        for (int idx = 0; idx <= maxWeight; idx++){
            maxValue = Math.max(dp[idx], maxValue);
        }
        System.out.println(maxValue);
    }
}
