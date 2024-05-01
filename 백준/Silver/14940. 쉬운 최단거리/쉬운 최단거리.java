import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 지도가 주어지면 모든 지점에 대해서 목표지점까지의 거리를 구하여라.
 * 문제를 쉽게 만들기 위해 오직 가로와 세로로만 움직일 수 있다고 하자.
 */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    // 상, 우, 하, 좌
    static final int[] DELTA_ROW = {-1, 0, 0, 1};
    static final int[] DELTA_COL = {0, 1, -1, 0};

    static final int TARGET = 2;
    static final int WALL = 0;
    static int rowSize, colSize;
    static int[][] map;
    static int[][] distance;
    static Pos start;

    static class Pos {
        int row;
        int col;
        int distance;
        public Pos (int row, int col, int distance){
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());

        rowSize= Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];
        distance = new int[rowSize][colSize];

        for (int row = 0; row < rowSize; row++){
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < colSize; col++){
                int value = Integer.parseInt(st.nextToken());
                map[row][col] = value;
                distance[row][col] = -1;
                if (value == WALL){
                    distance[row][col] = WALL;
                }else if (value == TARGET){
                    start = new Pos(row,col,0);
                    distance[row][col] = 0;
                }
            }
        }

        Queue<Pos> queue = new ArrayDeque<>();
        //bfs
        queue.offer(start);
        while (!queue.isEmpty()){
            Pos current = queue.poll();
            int row = current.row;
            int col = current.col;
            int dist =current.distance;
            for (int idx = 0; idx < 4; idx++){
                int tmpRow = row + DELTA_ROW[idx];
                int tmpCol = col + DELTA_COL[idx];

                // 벗어난 경우
                if (tmpRow < 0 || tmpCol <0 || tmpRow >= rowSize || tmpCol >= colSize){
                    continue;
                }

                // 벽인 경우
                if (map[tmpRow][tmpCol] != 1){
                    continue;
                }

                // 이미 방문한 경우
                if (distance[tmpRow][tmpCol] != -1){
                    continue;
                }

                distance[tmpRow][tmpCol] = dist + 1;
                queue.offer(new Pos(tmpRow, tmpCol, dist + 1));

            }
        }

        sb = new StringBuilder();
        for (int row = 0; row < rowSize; row++){
            for (int col = 0; col < colSize; col++){
                sb.append(distance[row][col]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
