import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node {
    int vertex, weight;

    public Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}

public class Main {

    static int n, m;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[s].add(new Node(e, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start, end);
    }

    static void dijkstra(int start, int end) {
        // 최단 거리 저장 배열
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // 경로 추적 배열 (이전 정점 저장)
        int[] path = new int[n + 1];

        // 최소 힙 (가중치 기준 오름차순)
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.weight, o2.weight);
            }
        });

        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            // 가지치기: 이미 처리된 더 짧은 경로가 있으면 스킵
            if (cur.weight > dist[cur.vertex]) continue;

            for (Node next : graph[cur.vertex]) {
                int sumCost = dist[cur.vertex] + next.weight;

                // 더 짧은 경로 발견 시 갱신
                if (dist[next.vertex] > sumCost) {
                    dist[next.vertex] = sumCost;
                    path[next.vertex] = cur.vertex;  // 이전 정점 기록
                    pq.offer(new Node(next.vertex, sumCost));
                }
            }
        }

        // 역추적: end에서 start까지 거꾸로 올라가기
        ArrayList<Integer> route = new ArrayList<>();
        int current = end;
        while (current != start) {
            route.add(current);
            current = path[current];  // 이전 정점으로 이동
        }
        route.add(start);

        // 경로 뒤집기 (start, end 순서로)
        Collections.reverse(route);

        System.out.println(dist[end]);
        System.out.println(route.size());
        for (int city : route) {
            System.out.print(city + " ");
        }
    }
}