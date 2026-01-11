import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
백준 / 소문난 칠공주 / 골드3
https://www.acmicpc.net/problem/1941
 */
public class BOJ_1941 {

    private static final int[] dr = {1, 0, -1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    private static final char[][] students = new char[5][5];
    private static final boolean[] selected = new boolean[25];
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            students[i] = br.readLine().toCharArray();
        }

        recur(0, 0, 0);

        System.out.println(answer);
    }

    private static void recur(int start, int depth, int dasomCount) {
        if (depth == 7) {
            // 다솜파 학생이 4명 이상이고, 모든 학생이 인접해 있을 때 카운트
            if (dasomCount >= 4 && isAdjacent()) {
                answer++;
            }
            return;
        }

        // 모든 학생에 대하여 탐색
        for (int i = start; i < 25; i++) {
            selected[i] = true;
            int r = i / 5;
            int c = i % 5;

            // 다솜파 학생일 경우 카운트하여 재귀 호출
            if (students[r][c] == 'S') {
                recur(i + 1, depth + 1, dasomCount + 1);
            } else {
                recur(i + 1, depth + 1, dasomCount);
            }
            selected[i] = false;
        }
    }

    private static boolean isAdjacent() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        int count = 0;

        // 첫번째로 선택된 학생 탐색
        for (int i = 0; i < 25; i++) {
            if (selected[i]) {
                int r = i / 5;
                int c = i % 5;

                q.add(new int[]{r, c});
                visited[r][c] = true;
                break;
            }
        }

        // 첫번째 학생의 인접 학생부터 BFS 탐색
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            count++;

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5) {
                    continue;
                }

                int index = nr * 5 + nc;
                // 인접한 학생일 경우 큐에 추가
                if (!visited[nr][nc] && selected[index]) {
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                }
            }
        }

        return count == 7;  // 인접된 학생의 수가 7일 때 true 반환
    }
}
