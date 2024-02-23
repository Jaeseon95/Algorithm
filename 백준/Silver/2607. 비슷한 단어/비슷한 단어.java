import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 1. 같은 종류의 문자로 이루어져 있다.
 * 2. 같은 문자는 같은 개수 만큼 있다.
 * 3. 완전히 같은 경우 같은단더.
 * 4. 한 문자를 더하거나 빼거나 다른 문자로 바꾼 경우 같은 구성
 */
public class Main {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {
        br  = new BufferedReader(new InputStreamReader(System.in));

        int numWords = Integer.parseInt(br.readLine().trim());
        int ans = 0;

        char[] init = br.readLine().toCharArray();
        int[] initCnt = new int[26];
        for( int idx = 0; idx < init.length; idx++) {
            char initChar = init[idx];
            initCnt[initChar - 'A'] += 1;
        }
        for(int idx = 0; idx < numWords - 1; idx++){
            char[] compare = br.readLine().toCharArray();
            int[] compareCnt = new int[26];
            for( int compareIdx = 0; compareIdx < compare.length; compareIdx++) {
                char compareChar = compare[compareIdx];
                compareCnt[compareChar - 'A'] += 1;
            }

            int diffCnt = 0;
            for (int charIdx = 0; charIdx < 26; charIdx++){
                if (initCnt[charIdx] != compareCnt[charIdx]){
                    diffCnt += Math.abs(initCnt[charIdx] - compareCnt[charIdx]);
                }
            }

            if (diffCnt <= 1){
                ans += 1;
            }else if(diffCnt == 2){
                if ((init.length - compare.length) == 0){
                    ans += 1;
                }
            }
        }
        System.out.println(ans);
    }

}
