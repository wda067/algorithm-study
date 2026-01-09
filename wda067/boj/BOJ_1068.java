import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 / 트리 / 골드5
https://www.acmicpc.net/problem/1068
 */
public class BOJ_1068 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());  // 노드의 개수

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
        }

        int root = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int node = Integer.parseInt(st.nextToken());
            if (node == -1) {
                root = i;
                continue;
            }

            adjList.get(node).add(i);
        }

        int target = Integer.parseInt(br.readLine());  // 지울 노드의 번호

        // 루트를 지우면 0
        if (root == target) {
            System.out.println(0);
            return;
        }

        int count = 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int cur = q.poll();

            int childCount = 0;
            for (int next : adjList.get(cur)) {
                if (next == target) {
                    continue;
                }
                q.add(next);
                childCount++;
            }

            // 자식이 없는 노드 and 자식이 삭제되어 자기가 리프 노드가 되는 경우
            if (childCount == 0) {
                count++;
            }
        }

        System.out.println(count);
    }
}
