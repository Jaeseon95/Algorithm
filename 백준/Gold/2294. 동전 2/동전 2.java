import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * n가지 종류의 동전이 있다. 이 동전들을 적당히 사용해서, 그 가치의 합이 k원이 되도록 하고 싶다.
 * 그러면서 동전의 개수가 최소가 되도록 하려고 한다.
 * 각각의 동전은 몇 개라도 사용할 수 있다.
 */

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static int numCoin, target;
    static final int MAX_VAL = 100000;
    static int[] coinList;
    static int[] valueList;

    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        numCoin = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        coinList = new int[numCoin];
        valueList = new int[MAX_VAL + 1 + target];
        Arrays.fill(valueList, MAX_VAL);
        valueList[0] = 1;

        for (int idx =0 ; idx < numCoin; idx++){
            coinList[idx] = Integer.parseInt(br.readLine().trim());
        }


        for (int idx =0 ; idx <numCoin; idx++){
            int coin = coinList[idx];
            if (coin > target){
                continue;
            }
            for (int val = 0 ; val <= target; val++){
                // 불가능 한 칸이면 스킵
                if(valueList[val] == 0){
                    continue;
                }
                valueList[val + coin] = Math.min(valueList[val] + 1 , valueList[val + coin]);
            }
        }

        if (valueList[target] == MAX_VAL){
            System.out.println( -1);
            return;
        }
        System.out.println(valueList[target] - 1);
    }
}