import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/*
백준 / 트리의 지름 / 골드4
https://www.acmicpc.net/problem/1967
 */
public class BOJ_1967 {

    private static HashMap<Integer, List<Edge>> adjList = new HashMap<>();
    private static int[] dist;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dist = new int[N + 1];
        visited = new boolean[N + 1];

        if (N == 1) {  // 노트가 1개일 때
            System.out.println(0);
            return;
        }

        for (int i = 0; i <= N; i++) {
            adjList.put(i, new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList.get(start).add(new Edge(end, weight));
            adjList.get(end).add(new Edge(start, weight));
        }

        // 루트 노드에서 dfs
        dfs(1, 0);

        int max = 0, maxNode = 0;
        for (int i = 1; i <= N; i++) {
            if (max < dist[i]) {
                max = dist[i];
                maxNode = i;
            }
        }

        // 루트 노드에서 가장 멀리 떨어진 노드에서 dfs
        dfs(maxNode, 0);

        Arrays.stream(dist)
                .max()
                .ifPresent(System.out::println);
    }

    private static void dfs(int start, int weight) {
        // 누적 거리 갱신
        if (dist[start] < weight) {
            dist[start] = weight;
        }

        // 인접 노드 탐색
        for (Edge edge : adjList.get(start)) {
            if (!visited[edge.end]) {  // 방문하지 않은 노드일 경우
                // 현 노드는 방문 처리 후 재귀 호출
                visited[start] = true;
                dfs(edge.end, weight + edge.weight);
                // 재귀 호출이 끝나면 현 노드는 미방문 처리
                visited[start] = false;
            }
        }

    }

    private static class Edge {

        int end;
        int weight;

        public Edge(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
}