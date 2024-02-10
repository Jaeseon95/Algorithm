import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 팰린드롬: 앞에서 읽으나 뒤에서 읽으나 같게 읽히는 문자열.
 * 동호는 규완이가 적어놓고 간 문자열 s에 0개 이상의 문자를 뒤에 추가하여 만들려고한다.
 * 만들 수 있는 가장 짧은 팰린드롬 문자열의 길이를 나타내시오
 */
public class Main {

    static char[] input;
    static BufferedReader br;
    static int length;

    private static boolean checkEvenSymmetric(int start) {
        for (int idx = 1; idx < length - start; idx++) {
            char front = input[start - idx + 1];
            char back = input[start + idx];
            if (front != back) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkOddSymmetric(int start) {
        for (int idx = 1; idx < length - start; idx++) {
            char front = input[start - idx];
            char back = input[start + idx];
            if (front != back) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().toCharArray();
        length = input.length;
        int ans = 0;
        int mid = length / 2;
        if (((length & 1) == 0 )&& checkEvenSymmetric(mid -1)) {
            System.out.println(length);
            return;
        }
        for (int midIdx = mid; midIdx < length; midIdx++){
            if(checkOddSymmetric(midIdx)){
                System.out.println(2*midIdx +1);
                return;
            }
            if(checkEvenSymmetric(midIdx)) {
                System.out.println(2*(midIdx +1));
                return;
            }
        }
    }
}
