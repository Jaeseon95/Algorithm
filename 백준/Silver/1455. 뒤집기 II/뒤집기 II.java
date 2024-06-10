import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * n X m개를 가지고 있을 때 앞면을 0, 뒷면을 1이라고 하자 모든 동전을 뒤집어 앞면을 만들려고 한다.
 * (a,b) 칸을 뒤집으려고 한다면 1~a * 1~b 개의 동전이 한번에 모두 뒤집힌다.
 * 세준이가 뒤집어야 하는 동전의 개수를 출력하시오
 * [풀이] 누적합으로 풀면 될듯
 */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static int rowSize, colSize;

    static final int FRONT = 0;
    static final int BACK = 1;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        int[][] map = new int[rowSize][colSize];
        int[][] prefixMap = new int[rowSize + 1][colSize + 1];
        // 초기화
        for (int row = 0; row < rowSize; row++) {
            String[] tmp = br.readLine().split("");
            for (int col = 0; col < colSize; col++) {
                map[row][col] = Integer.parseInt(tmp[col]);
            }
        }

        prefixMap[rowSize - 1][colSize - 1] = map[rowSize - 1][colSize - 1];
        int flipCnt, isFlip;
        for (int row = rowSize - 1; row >= 0; row--) {
            for (int col = colSize - 1; col >= 0; col--) {
                flipCnt = prefixMap[row + 1][col] + prefixMap[row][col + 1] - prefixMap[row + 1][col + 1];
                isFlip = 0;
                // 뒷면인 경우 홀수번 flip하면 0, 앞면인 경우 양수번 flip하면 1
                if (map[row][col] == 0 && (flipCnt & 1) == 1) {
                    isFlip = 1;
                } else if (map[row][col] == 1 && (flipCnt & 1) == 0) {
                    isFlip = 1;
                }
                prefixMap[row][col] = flipCnt + isFlip;
            }
        }
        System.out.println(prefixMap[0][0]);
    }
}
