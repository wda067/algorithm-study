import java.io.*;
import java.util.*;

public class Main {

    static int[] parent;
    static boolean[] hasCycle;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseNum = 0;

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) break;

            parent = new int[n + 1];
            hasCycle = new boolean[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                union(a, b);
            }

            // 트리 개수 세기
            Set<Integer> trees = new HashSet<>();
            for (int i = 1; i <= n; i++) {
                int root = find(i);

                if (!hasCycle[root]) {
                    trees.add(root);
                }
            }

            caseNum++;
            int treeCnt = trees.size();
            if (treeCnt == 0) {
                System.out.println("Case " + caseNum + ": No trees.");
            }
            else if (treeCnt == 1) {
                System.out.println("Case " + caseNum + ": There is one tree.");
            }
            else {
                System.out.println("Case " + caseNum + ": A forest of " + treeCnt + " trees.");
            }
        }
    }

    // find
    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);  // 경로 압축
    }

    // union
    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        // 이미 같은 집합이면 사이클 발생
        if (rootA == rootB) {
            hasCycle[rootA] = true;
            return;
        }

        // 작은 번호를 부모로
        if (rootA < rootB) {
            parent[rootB] = rootA;
        } else {
            parent[rootA] = rootB;
        }

        // 둘 중 하나라도 사이클 있으면 합친 집합도 사이클 존재
        if (hasCycle[rootA] || hasCycle[rootB]) {
            hasCycle[Math.min(rootA, rootB)] = true;
        }
    }
}
