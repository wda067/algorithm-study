import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
백준 / 트리 / 골드4
https://www.acmicpc.net/problem/4803
 */
public class BOJ_4803 {

    private static List<List<Integer>> adjList;
    private static boolean[] visited;
    private static boolean hasCycle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int testCase = 1;

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());  // 정점의 개수
            int M = Integer.parseInt(st.nextToken());  // 간선의 개수

            if (N == 0) {
                break;
            }

            adjList = new ArrayList<>();
            visited = new boolean[N + 1];
            int tree = 0;

            for (int i = 0; i <= N; i++) {
                adjList.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                adjList.get(a).add(b);
                adjList.get(b).add(a);
            }

            // 모든 노드 탐색
            for (int i = 1; i <= N; i++) {
                if (visited[i]) {
                    continue;
                }

                hasCycle = false;
                dfs(i, 0);
                if (!hasCycle) {
                    tree++;
                }
            }

            sb.append("Case ").append(testCase++).append(": ");
            if (tree == 0) {
                sb.append("No trees.");
            } else if (tree == 1) {
                sb.append("There is one tree.");
            } else {
                sb.append("A forest of ").append(tree).append(" trees.");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int cur, int parent) {
        visited[cur] = true;

        for (Integer next : adjList.get(cur)) {
            if (!visited[next]) {
                dfs(next, cur);
            } else if (next != parent) {
                // 부모가 아닌데 이미 방문한 정점을 다시 만남
                hasCycle = true;
            }
        }
    }
}
