import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 사과를 먹으면 뱀 길이가 늘어난다. 벽 또는 자기 자신의 몸과 부딪히면 게임이 끝난다.
 * NxN 정사각 보드위에서 진행되고, 몇몇 칸에는 사과가 놓여져 있따. 보드의 상화좌우 끝에 벽이 있다.
 * 게임이 시작할 때 뱀은 맨위 좌측에 위치하며 뱀의 길이는 1 머리는 오른쪽 방향을 향한다.
 * 매초마다 다음과 같은 규칙을따른다.
 * - 몸 길이를 늘려 다음칸에 위치시킨다.
 * - 벽에 부딪히면 끝난다.
 * - 이동한 칸에 사과가 있다면 사과가 없어지고 꼬리는 그대로 있는다.
 * - 사과가 없다면 몸 길이를 줄여 꼬리가 한칸 줄여준다.
 * 사과의 위치와 뱀의 이동경로가 주어질 때 몇초에 게임이 끝나는지 계산하여라
 */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static int size;
    static int numApple;
    static int numChange;
    static int direction;
    static int endTime;
    static int[][] map;
    static boolean[][] apple;
    // 상, 우, 하, 좌
    static final int[] DELTA_ROW = {-1, 0, 1, 0};
    static final int[] DELTA_COL = {0, 1, 0, -1};

    static Pos current;
    static Queue<Pos> snake;
    static Queue<Info> infoQueue;
    static class Pos{
        int row;
        int col;
        public Pos(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    static class Info{
        int time;
        char turnType;

        public Info(int time, char turnType){
            this.time = time;
            this.turnType = turnType;
        }
    }

    public static void main(String[] args) throws Exception{
        // 입력처리
        getInput();

        // 시뮬레이션
        simulation();

        // 결과 출력
        System.out.println(endTime);
    }

    private static void simulation() {
        for (int time = 1; time < 10101; time++){
            // 머리 방향대로 이동
            int deltaRow = current.row + DELTA_ROW[direction];
            int deltaCol = current.col + DELTA_COL[direction];

            // 벗어난 경우
            if (!isValid(deltaRow, deltaCol) || map[deltaRow][deltaCol] != 0){
                endTime = time;
                break;
            }

            current = new Pos(deltaRow, deltaCol);
            map[deltaRow][deltaCol] = 1;
            snake.offer(current);

            // 사과 체크
            if (!apple[deltaRow][deltaCol]){
                Pos tail = snake.poll();
                map[tail.row][tail.col] = 0;
            }
            apple[deltaRow][deltaCol] = false;

            // 방향 체크
            if (!infoQueue.isEmpty() && infoQueue.peek().time == time){
                Info info = infoQueue.poll();
                if (info.turnType == 'L'){
                    direction = (direction + 3) % 4;
                }else{
                    direction = (direction + 1) % 4;
                }
            }
        }
    }

    private static boolean isValid(int row, int col){
        return row >= 0 && col >= 0 && row < size && col < size;
    }

    private static void getInput() throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine().trim());
        numApple = Integer.parseInt(br.readLine().trim());

        // 뱀의 맵, 사과 맵 초기화
        map = new int[size][size];
        apple = new boolean[size][size];

        for (int idx = 0; idx < numApple; idx++){
            st = new StringTokenizer(br.readLine().trim());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            apple[row - 1][col -1] = true;
        }

        numChange = Integer.parseInt(br.readLine().trim());
        infoQueue = new ArrayDeque<>();
        for (int idx = 0; idx < numChange; idx++){
            st = new StringTokenizer(br.readLine().trim());
            int time = Integer.parseInt(st.nextToken());
            char turnType = st.nextToken().toCharArray()[0];
            infoQueue.offer(new Info(time, turnType));
        }

        // 맵 초기화
        map[0][0] = 1;
        direction = 1;
        snake = new ArrayDeque<>();
        current = new Pos(0,0);
        snake.offer(current);
    }
}
