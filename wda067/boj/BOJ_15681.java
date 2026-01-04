import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
백준 / 트리와 쿼리 / 골드5
https://www.acmicpc.net/problem/15681
 */
public class BOJ_15681 {

    private static int N, R, Q;
    private static List<List<Integer>> adjList = new ArrayList<>();
    private static int[] parent;
    private static int[] subtree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  //노드 수
        R = Integer.parseInt(st.nextToken());  //루트 번호
        Q = Integer.parseInt(st.nextToken());  //쿼리 수
        parent = new int[N + 1];
        parent[R] = -1;  // 루트 노드의 부모 노드는 없음
        subtree = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        // 간선 정보
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            adjList.get(U).add(V);
            adjList.get(V).add(U);
        }

        dfs(R);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int U = Integer.parseInt(br.readLine());
            sb.append(subtree[U] + 1).append("\n");
        }
        System.out.println(sb);
    }

    private static int dfs(int node) {
        int child = 0;
        for (Integer next : adjList.get(node)) {  // 자식 노드 탐색
            if (parent[next] == 0) {  // 부모 노드 탐색 전이라면
                parent[next] = node;
                child++;
                child += dfs(next);
            }
        }
        subtree[node] += child;
        return subtree[node];
    }
}
