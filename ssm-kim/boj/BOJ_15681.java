import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] graph;
    static int[] subTreeSize;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        subTreeSize = new int[n + 1];

        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            // 무방향 그래프
            graph[u].add(v);
            graph[v].add(u);
        }

        for (ArrayList<Integer> integers : graph) {
            System.out.println(integers);
        }

        // 루트에서 dfs로 모든 서브트리 크기 계산
        dfs(r, -1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int vertex = Integer.parseInt(br.readLine());
            sb.append(subTreeSize[vertex]).append('\n');
        }
        System.out.println(sb);
    }

    static int dfs(int current, int parent) {
        int size = 1;  // 자기 자신 포함

        for (int next : graph[current]) {
            if (next != parent) {
                size += dfs(next, current);
            }
        }

        subTreeSize[current] = size;
        System.out.println(Arrays.toString(subTreeSize));
        return size;
    }
}