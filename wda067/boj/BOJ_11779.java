import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
백준 / 최소비용 구하기 2 / 골드3
https://www.acmicpc.net/problem/11779
 */
public class BOJ_11779 {

    private static List<List<Edge>> adjList = new ArrayList<>();
    private static int[] dist, prev;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());  // 도시의 개수: 1,000
        int m = Integer.parseInt(br.readLine());  // 버스의 개수: 100,000

        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        prev = new int[n + 1];
        Arrays.fill(prev, -1);

        visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());  // 비용

            adjList.get(a).add(new Edge(b, c));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        dist[start] = 0;
        dijkstra(start);

        System.out.println(dist[end]);

        // 경로 추적
        List<Integer> path = new ArrayList<>();
        int cur = end;
        while (cur != -1) {
            path.add(cur);
            if (cur == start) {
                break;
            }
            cur = prev[cur];  // 경로 갱신
        }

        Collections.reverse(path);

        System.out.println(path.size());

        StringBuilder sb = new StringBuilder();
        for (int p : path) {
            sb.append(p).append(" ");
        }

        System.out.println(sb);
    }

    private static void dijkstra(int node) {
        PriorityQueue<Edge> q = new PriorityQueue<>(Comparator.comparing(o -> o.weight));
        q.add(new Edge(node, 0));

        while (!q.isEmpty()) {
            Edge cur = q.poll();
            int curNode = cur.to;

            if (visited[curNode]) {
                continue;
            }

            visited[curNode] = true;

            for (Edge next : adjList.get(curNode)) {
                int nextNode = next.to;
                int weight = next.weight;
                int newDist = dist[curNode] + weight;

                if (newDist < dist[nextNode]) {
                    dist[nextNode] = newDist;
                    prev[nextNode] = curNode;
                    q.add(new Edge(nextNode, dist[nextNode]));
                }
            }
        }
    }

    private static class Edge {
        private final int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
