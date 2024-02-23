import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * KCPC
 * 첫 째줄 테스트 횟수
 * 팀 개수 n, 문제 수 k, 나의 팀 t, 로그 개수 m이 주어질 때
 * 점수 총합이 가장 높은 팀 같다면 풀이 횟수가 적은팀 횟수도 같다면 제출 시간이 더 빠른팀
 */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static class Team implements Comparable<Team> {
        int teamNumber;
        int totalScore;
        int numSubmit;
        int lastSubmit;
        int[] scores;
        public Team(int teamNumber, int numScore){
            this.teamNumber = teamNumber;
            scores = new int[numScore + 1];
        }

        public void sumScore(){
            for (int score : this.scores) {
                totalScore += score;
            }
        }

        @Override
        public int compareTo(Team o) {
            if (o.totalScore == this.totalScore) {
                if (o.numSubmit == this.numSubmit) {
                    return this.lastSubmit - o.lastSubmit;
                }
                return this.numSubmit - o.numSubmit;
            }
            return o.totalScore - this.totalScore;
        }
    }

    private static int solve(int numTeam, int numScore, int myTeam, int numLog) throws Exception{
        Team[] teams = new Team[numTeam];
        // 팀 생성
        for (int teamIdx = 1; teamIdx <= numTeam; teamIdx++) {
            teams[teamIdx - 1] = new Team(teamIdx, numScore);
        }

        // 로그를 지나가면서 처리
        for (int logIdx = 1; logIdx <= numLog; logIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            int teamId = Integer.parseInt(st.nextToken());
            int problemId = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            Team team = teams[teamId - 1];
            team.scores[problemId] = Math.max(team.scores[problemId], score);
            team.numSubmit += 1;
            team.lastSubmit = logIdx;
        }
        // 팀별로 총합 구하기
        for (int teamIdx = 1; teamIdx <= numTeam; teamIdx++) {
            Team team = teams[teamIdx - 1];
            team.sumScore();
        }

        Arrays.sort(teams);

        int ans = 0;
        for (int idx = 1; idx <= numTeam; idx++){
            Team team = teams[idx - 1];
            if (team.teamNumber == myTeam){
                ans = idx;
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        int numTest = Integer.parseInt(br.readLine().trim());
        sb = new StringBuilder();
        for (int idx = 0; idx < numTest; idx++){
            st = new StringTokenizer(br.readLine().trim());
            int numTeam = Integer.parseInt(st.nextToken());
            int numScore = Integer.parseInt(st.nextToken());
            int myTeam = Integer.parseInt(st.nextToken());
            int numLog = Integer.parseInt(st.nextToken());
            sb.append(solve(numTeam, numScore, myTeam, numLog)).append("\n");
        }
        System.out.println(sb);
    }
}
