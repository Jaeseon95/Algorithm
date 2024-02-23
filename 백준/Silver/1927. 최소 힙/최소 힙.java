import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 1. 배열에 자연수 X를 넣는다.
 * 2. 가장 작은 값을 출력하고 배열에서 제거한다.
 * 3. 비어있는 경우 0을 출력한다.
 */
public class Main {

    static BufferedReader br;
    static StringBuilder sb;
    static PriorityQueue<Integer> priorityQueue;
    static final int PRINT = 0;

    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int totalNum = Integer.parseInt(br.readLine().trim());
        priorityQueue = new PriorityQueue<>();

        for (int idx =0 ; idx < totalNum; idx++){
            int number = Integer.parseInt(br.readLine().trim());
            if (number == PRINT){
                if(priorityQueue.isEmpty()){
                    sb.append(0).append("\n");
                }else{
                    sb.append(priorityQueue.poll()).append("\n");
                }
            }else{
                priorityQueue.offer(number);
            }
        }
        System.out.println(sb);
    }
}
