import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 바이토닉 수열
 * 특정 수를 기준으로
 * 상승하는 것의 길이 + 감소하는 것의 길이
 * 상승 배열의 길이  + 하강 배열의 길이
 * +
 */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static int length;

    static int[] numList;
    static int[] ascendList;
    static int[] descendList;
    static int[] dp;

    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));

        length = Integer.parseInt(br.readLine().trim());

        numList = new int[length];
        ascendList = new int[length];
        descendList = new int[length];
        dp = new int[length];

        st = new StringTokenizer(br.readLine().trim());
        for (int idx =0; idx < length; idx++){
            numList[idx] = Integer.parseInt(st.nextToken());
            ascendList[idx] = 1;
            descendList[idx] = 1;
        }

        for (int idx = 1; idx < length; idx++){
            int number = numList[idx];
            for (int idx2 = 0; idx2< idx; idx2++){
                if (number > numList[idx2]){
                    ascendList[idx] = Math.max(ascendList[idx], ascendList[idx2] + 1);
                }
            }
        }

        for (int idx = length-2; idx >= 0; idx--){
            int number = numList[idx];
            for (int idx2 = idx + 1; idx2 < length; idx2++){
                if (number > numList[idx2]){
                    descendList[idx] = Math.max(descendList[idx], descendList[idx2] + 1);
                }
            }
        }

        // 최대 값 구하기
        int maxValue = 0;
        for (int idx = 0; idx < length; idx++){
            int value = ascendList[idx] + descendList[idx] - 1;
            maxValue = Math.max(maxValue, value);
        }
        System.out.println(maxValue);
    }
}
