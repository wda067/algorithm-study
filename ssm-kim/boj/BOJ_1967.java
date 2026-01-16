import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Edge {
    int vertex, weight;

    public Edge(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}

public class Main {

    static Edge farNode;
    static int n;
    static ArrayList<Edge>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 1번 부모 노드, 2번 자식 노드, 3번 가중치
            int parentNode = Integer.parseInt(st.nextToken());
            int childNode = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[parentNode].add(new Edge(childNode, weight));
            graph[childNode].add(new Edge(parentNode, weight));
        }

        // 임의의 노드(1번)에서 가장 먼 노드 찾기
        visited = new boolean[n + 1];
        farNode = new Edge(-1, -1);
        dfs(1, 0);

        // 찾은 노드에서 다시 가장 먼 노드까지의 거리 = 트리의 지름
        int farIdx = farNode.vertex;
        farNode = new Edge(-1, -1);
        Arrays.fill(visited, false);
        dfs(farIdx, 0);

        System.out.println(farNode.weight);
    }

    static void dfs(int current, int distance) {
        visited[current] = true;

        // 현재까지의 누적 거리가 최대면 갱신
        if (distance > farNode.weight) {
            farNode = new Edge(current, distance);
        }

        for (Edge next : graph[current]) {
            if (!visited[next.vertex]) {
                dfs(next.vertex, distance + next.weight);  // 가중치 누적하며 탐색
            }
        }
    }
}