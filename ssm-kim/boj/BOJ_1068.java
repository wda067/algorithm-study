import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, leapNode;
    static boolean[] visited;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        // 부모 정보를 받아 트리를 인접 리스트로 구성
        StringTokenizer st = new StringTokenizer(br.readLine());
        int root = 0;
        for (int node = 0; node < n; node++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = node;
                continue;
            }
            graph[parent].add(node);
        }

        int rmNumber = Integer.parseInt(br.readLine());

        // 삭제할 노드를 부모의 자식 리스트에서 제거
        for (ArrayList<Integer> arr : graph) {
            if (arr.contains(rmNumber)) {
                arr.remove(arr.indexOf(rmNumber));
            }
        }

        visited = new boolean[n];
        leapNode = 0;

        // 삭제할 노드가 루트가 아닐 때만 DFS 수행
        if (root != rmNumber) {
            dfs(root);
        }

        System.out.println(leapNode);
    }

    static void dfs(int current) {
        visited[current] = true;

        // 자식이 없으면 리프 노드로 카운트
        if (graph[current].isEmpty()) leapNode++;

        for (int next : graph[current]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }
}