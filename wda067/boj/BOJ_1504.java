import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
백준 / 특정한 최단 경로 / 골드4
https://www.acmicpc.net/problem/1504
 */
public class BOJ_1504 {

    // 간선의 최대 개수 20만, 가중치의 최대값 1천
    private static final int INF = 200_000_000;
    static boolean[] visit;
    static int[] dist;
    static HashMap<Integer, List<Node>> adjList;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 정점의 개수
        int E = Integer.parseInt(st.nextToken());  // 간선의 개수
        visit = new boolean[N + 1];
        dist = new int[N + 1];

        if (E == 0) {
            System.out.println(-1);
            return;
        }

        // 인접 리스트 초기화
        adjList = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            adjList.put(i, new ArrayList<>());
        }

        // 입력값 세팅
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList.get(start).add(new Node(end, weight));
            adjList.get(end).add(new Node(start, weight));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        // 1 -> v1 -> v2 -> N
        int a = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N);
        // 1 -> v2 -> v1 -> N
        int b = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N);

        int result = Math.min(a, b);
        // 최소 비용이 무한대일 경우 -1 출력
        if (result >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    private static int dijkstra(int start, int end) {
        // 배열 초기화
        Arrays.fill(dist, INF);
        Arrays.fill(visit, false);

        // 가까운 순서대로 처리하므로 큐 사용
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        dist[start] = 0;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int curNode = current.end;

            if (!visit[curNode]) {
                visit[curNode] = true;

                // 현재 노드의 인접 노드 탐색
                for (Node node : adjList.get(curNode)) {
                    int nextNode = node.end;
                    //방문한 노드는 패스
                    if (visit[nextNode]) {
                        continue;
                    }
                    // 현재 노드에서 인접 노드로 가는 비용
                    int nextDist = dist[curNode] + node.weight;
                    // 기존의 최소 비용보다 저렴하다면 갱신
                    if (dist[nextNode] > nextDist) {
                        dist[nextNode] = nextDist;
                        // 갱신된 노드를 큐에 추가
                        queue.add(new Node(nextNode, dist[nextNode]));
                    }
                }
            }
        }

        return dist[end];
    }

    static class Node implements Comparable<Node> {

        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }
}
