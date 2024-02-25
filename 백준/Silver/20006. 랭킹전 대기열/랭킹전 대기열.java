import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 비슷한 레벨의 플레이어 매칭 시스템
 * 1. 입장 신청시 방이 없다면 새로운 방을 생성한다. 처음 입장한 플레이어 레벨 -10부터 +10까지 입장 가능하다.
 * 2. 입장 가능한 방이 있다면 입장시킨 후 모두 찰 때까지 기다린다. - 입장 가능한 방이 여러 개라면 먼저 생성된 방에 입장한다.
 * 3. 방의 정원이 모두차면 게임을 시작한다.
 * 플레이어 수p, 닉네임n, 레벨 l, 방의 정원 m이 주어졌을 때
 * 최종적으로 만들어진 방의 상태와 입장 플레이어를 출력하는 프로그램을 작성하자.
 */
public class Main {

    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;
    static List<Room> roomList;


    static class Player implements Comparable<Player>{
        int level;
        String nickname;

        public Player(int level, String nickname){
            this.level = level;
            this.nickname = nickname;
        }

        @Override
        public int compareTo(Player o) {
            return this.nickname.compareTo(o.nickname);
        }

        @Override
        public String toString() {
            return this.level + " " + this.nickname;
        }
    }
    static class Room {

        int initLevel;
        int maxPeople;
        boolean isFull;

        List<Player> playerList;

        public Room(int maxPeople, Player player) {
            this.initLevel = player.level;
            this.maxPeople = maxPeople;
            this.playerList = new ArrayList<>();
            this.playerList.add(player);
            if (this.maxPeople == 1){
                this.isFull = true;
            }
        }

        public boolean isEnter(int level) {
            int diff = Math.abs(this.initLevel - level);
            return diff <= 10;
        }

        public void enter(Player player) {
            this.playerList.add(player);
            if(this.playerList.size() == maxPeople){
                this.isFull = true;
            }
        }
    }

    public static boolean insert(Player player){
        for (int roomIdx = 0; roomIdx < roomList.size(); roomIdx++){
            Room room = roomList.get(roomIdx);
            if (room.isFull){
                continue;
            }
            if (room.isEnter(player.level)){
                room.enter(player);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine().trim());
        int numPeople = Integer.parseInt(st.nextToken());
        int roomLimit = Integer.parseInt(st.nextToken());

        roomList = new ArrayList<>();
        for (int idx = 0; idx < numPeople; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int level = Integer.parseInt(st.nextToken());
            String nickname = st.nextToken();
            Player player = new Player(level, nickname);
            if (insert(player)){
                continue;
            }
            roomList.add(new Room(roomLimit, player));
        }

        for (int idx =0 ;idx < roomList.size(); idx++){
            Room room = roomList.get(idx);
            if (room.isFull){
                sb.append("Started!").append("\n");
            }else{
                sb.append("Waiting!").append("\n");
            }
            Collections.sort(room.playerList);
            for (Player player: room.playerList){
                sb.append(player).append("\n");
            }
        }
        System.out.println(sb);
    }

}
