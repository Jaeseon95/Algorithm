import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static final int INF = 100000;
    static int numNode, numResult;
    static int numQuestion;
    static int[][] graph;

    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine().trim());
        numNode = Integer.parseInt(st.nextToken());
        numResult = Integer.parseInt(st.nextToken());

        graph = new int[numNode + 1][numNode + 1];

        for (int row = 1; row <= numNode; row ++){
            for (int col = 1; col <= numNode; col++){
                graph[row][col] =  INF;
            }
        }

        for (int idx = 0; idx < numResult; idx++){
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from][to] = 1;
        }

        for (int mid  = 1; mid <= numNode; mid++){
            for (int start = 1; start <= numNode; start++){
                for (int end = 1; end <= numNode; end++){
                    graph[start][end] = Math.min(graph[start][end], graph[start][mid] + graph[mid][end]);
                }
            }
        }

        sb = new StringBuilder();

        numQuestion = Integer.parseInt(br.readLine().trim());
        for (int idx = 0; idx < numQuestion; idx++){
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            int prev = graph[from][to];
            int after = graph[to][from];

            if(prev == INF && after == INF){
                sb.append(0).append("\n");
            } else if (prev != INF && after == INF){
                sb.append(-1).append("\n");
            } else{
                sb.append(1).append("\n");
            }
        }
        System.out.println(sb);
    }
}