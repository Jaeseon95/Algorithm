import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine().trim());
        int numRow = Integer.parseInt(st.nextToken());
        int numCol = Integer.parseInt(st.nextToken());

        int[][] map = new int[numRow][numCol];
        for (int row = 0; row < numRow; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < numCol; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] dp = new int[3][numRow][numCol];
        for (int idx = 0; idx < 3; idx++){
            for (int row = 0; row < numRow; row++){
                for (int col = 0; col < numCol; col++){
                    dp[idx][row][col] = Integer.MIN_VALUE;
                }
            }
        }

        //위에서 오는 경우
        dp[0][0][0] = map[0][0];
        // 왼쪽에서 오는 경우
        dp[1][0][0] = map[0][0];
        // 오른쪽
        dp[2][0][0] = map[0][0];

        // 첫째줄 계산
        for (int col = 1; col < numCol; col++) {
            dp[0][0][col] = dp[0][0][col - 1] + map[0][col];
            dp[1][0][col] = dp[0][0][col - 1] + map[0][col];
            dp[2][0][col] = dp[0][0][col - 1] + map[0][col];
        }

        for (int row = 1; row <numRow; row++){
            // 위에서 먼저 내려오기
            for (int col = 0; col < numCol; col++){
                int maxVal = Math.max(dp[0][row-1][col], dp[1][row-1][col]);
                maxVal = Math.max(maxVal, dp[2][row-1][col]);
                dp[0][row][col] = maxVal  + map[row][col];
            }
            // 오른쪽으로 이동해보기
            for (int col = 1; col < numCol; col++){
                dp[1][row][col] = Math.max(dp[0][row][col-1]  , dp[1][row][col-1]) + map[row][col];
            }

            // 왼쪽으로 이동해보기
            for (int col = numCol - 2; col >= 0; col--){
                dp[2][row][col] = Math.max(dp[0][row][col+1]  , dp[2][row][col+1]) + map[row][col];
            }
        }


        int ans = Math.max(dp[0][numRow -1][numCol - 1], dp[1][numRow - 1][numCol - 1]);
        ans = Math.max(ans, dp[2][numRow - 1][numCol - 1]);
        System.out.println(ans);
    }


}
