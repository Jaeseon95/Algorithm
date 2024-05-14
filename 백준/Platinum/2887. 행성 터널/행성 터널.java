import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1. x기준 정렬, y기준 정렬, z기준 정렬 2. 간선 60만개 생성 3. 프림
 */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static int numPlanet;
    static List<Planet> planetList;
    static int[] parents;
    static PriorityQueue<Edge> edgePriorityQueue;
    static Comparator<Planet> compX, compY, compZ;

    static class Planet {

        int idx;
        int x;
        int y;
        int z;

        public Planet(int idx, int x, int y, int z) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public String toString() {
            return "Planet{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
        }
    }

    static class Edge implements Comparable<Edge> {

        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }

        @Override
        public String toString() {
            return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", cost=" + cost +
                '}';
        }
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        numPlanet = Integer.parseInt(br.readLine().trim());
        planetList = new ArrayList<>(numPlanet);

        int x = 0;
        int y = 0;
        int z = 0;

        for (int idx = 0; idx < numPlanet; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());
            planetList.add(new Planet(idx, x, y, z));
        }
        edgePriorityQueue = new PriorityQueue<>();

        // 정렬 기준 세팅
        setCompByAxis();
        planetList.sort(compX);
        addXEdge(planetList);
        planetList.sort(compY);
        addYEdge(planetList);
        planetList.sort(compZ);
        addZEdge(planetList);

        int numEdge = 0;

        parents = new int[numPlanet];
        for (int idx =0 ;idx < numPlanet; idx++){
            parents[idx] = idx;
        }
        long cost = 0;
        Edge edge;
        while (!edgePriorityQueue.isEmpty() && numEdge < numPlanet - 1) {
            edge = edgePriorityQueue.poll();
            if (!union(edge.from, edge.to)) {
                continue;
            }
            cost += edge.cost;
            numEdge += 1;
        }

        System.out.println(cost);
    }

    private static int find(int idx) {
        if (parents[idx] == idx){
            return idx;
        }
        return parents[idx] = find(parents[idx]);
    }

    private static boolean union(int idx1, int idx2){
        idx1 = find(idx1);
        idx2 = find(idx2);
        if (idx1 == idx2){
            return false;
        }
        parents[idx2] = idx1;
        return  true;
    }

    private static void addXEdge(List<Planet> planetList) {
        // 처음과 마지막은 고정
        Planet prev = planetList.get(0);
        Planet current = planetList.get(1);
        edgePriorityQueue.offer(new Edge(prev.idx, current.idx, Math.abs(prev.x - current.x)));
        Planet next;
        int prevCost, nextCost;
        for (int idx = 1; idx < planetList.size() - 1; idx++) {
            prev = planetList.get(idx - 1);
            current = planetList.get(idx);
            next = planetList.get((idx + 1));
            prevCost = Math.abs(current.x - prev.x);
            nextCost = Math.abs(current.x - next.x);
            edgePriorityQueue.offer(new Edge(current.idx, next.idx, nextCost));
            edgePriorityQueue.offer(new Edge(prev.idx, current.idx, prevCost));
        }
        prev = planetList.get(planetList.size() - 2);
        current = planetList.get(planetList.size() - 1);
        edgePriorityQueue.offer(new Edge(prev.idx, current.idx, Math.abs(prev.x - current.x)));
    }

    private static void addYEdge(List<Planet> planetList) {
        // 처음과 마지막은 고정
        Planet prev = planetList.get(0);
        Planet current = planetList.get(1);
        edgePriorityQueue.offer(new Edge(prev.idx, current.idx, Math.abs(prev.y - current.y)));
        Planet next;
        int prevCost, nextCost;
        for (int idx = 1; idx < planetList.size() - 1; idx++) {
            prev = planetList.get(idx - 1);
            current = planetList.get(idx);
            next = planetList.get((idx + 1));
            prevCost = Math.abs(current.y - prev.y);
            nextCost = Math.abs(current.y - next.y);
            edgePriorityQueue.offer(new Edge(current.idx, next.idx, nextCost));
            edgePriorityQueue.offer(new Edge(prev.idx, current.idx, prevCost));
        }
        prev = planetList.get(planetList.size() - 2);
        current = planetList.get(planetList.size() - 1);
        edgePriorityQueue.offer(new Edge(prev.idx, current.idx, Math.abs(prev.y - current.y)));
    }

    private static void addZEdge(List<Planet> planetList) {
        // 처음과 마지막은 고정
        Planet prev = planetList.get(0);
        Planet current = planetList.get(1);
        edgePriorityQueue.offer(new Edge(prev.idx, current.idx, Math.abs(prev.z - current.z)));
        Planet next;
        int prevCost, nextCost;
        for (int idx = 1; idx < planetList.size() - 1; idx++) {
            prev = planetList.get(idx - 1);
            current = planetList.get(idx);
            next = planetList.get((idx + 1));
            prevCost = Math.abs(current.z - prev.z);
            nextCost = Math.abs(current.z - next.z);
            edgePriorityQueue.offer(new Edge(current.idx, next.idx, nextCost));
            edgePriorityQueue.offer(new Edge(prev.idx, current.idx, prevCost));
        }
        prev = planetList.get(planetList.size() - 2);
        current = planetList.get(planetList.size() - 1);
        edgePriorityQueue.offer(new Edge(prev.idx, current.idx, Math.abs(prev.z - current.z)));
    }

    private static void setCompByAxis() {
        compX = (o1, o2) -> o1.x - o2.x;
        compY = (o1, o2) -> o1.y - o2.y;
        compZ = (o1, o2) -> o1.z - o2.z;
    }

}
