import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * LCS
 */
public class Main {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        char[] sentence1 = br.readLine().trim().toCharArray();
        char[] sentence2 = br.readLine().trim().toCharArray();

        int[][] dp = new int[sentence1.length +1][sentence2.length + 1];

        for (int idx1 = 1; idx1 <= sentence1.length; idx1++){
            char char1 = sentence1[idx1 - 1];
            for (int idx2 = 1; idx2 <= sentence2.length; idx2++){
                char char2 = sentence2[idx2 - 1];
                if (char1 == char2){
                    dp[idx1][idx2] =  dp[idx1-1][idx2-1] + 1;
                    continue;
                }
                dp[idx1][idx2] = Math.max(dp[idx1 -1][idx2], dp[idx1][idx2 -1]);
            }
        }
        System.out.println(dp[sentence1.length][sentence2.length]);
    }
}
