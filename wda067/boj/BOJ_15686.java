import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
백준 / 치킨 배달 / 골드5
https://www.acmicpc.net/problem/15686
 */
public class BOJ_15686 {

    private static int N, M;
    private static int[][] map;
    private static List<int[]> houses = new ArrayList<>();
    private static int min =  Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 0: 빈칸, 1: 집, 2: 치킨집

        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int val = Integer.parseInt(st.nextToken());
                map[i][j] = val;
                if (val == 1) {
                    houses.add(new int[] {i, j});
                }
            }
        }

        List<int[]> chickens = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] == 2) {
                    chickens.add(new int[]{i, j});
                    dfs(i, j, 1, chickens);
                    chickens.remove(chickens.size() - 1);
                }
            }
        }

        System.out.println(min);
    }

    private static void dfs(int r, int c, int depth, List<int[]> chickens) {
        if (depth == M) {
            min = Math.min(min, calculate(chickens));
            return;
        }

        int start = (r - 1) * N + c + 1;  // (r, c) 다음 칸을 1차원으로
        int end = N * N;

        for (int pos = start; pos <= end; pos++) {
            int i = (pos - 1) / N + 1;
            int j = (pos - 1) % N + 1;

            if (map[i][j] == 2) {
                chickens.add(new int[]{i, j});
                dfs(i, j, depth + 1, chickens);
                chickens.remove(chickens.size() - 1);
            }
        }
    }

    // 집마다 치킨 거리 구하기
    private static int calculate(List<int[]> chickens) {
        int total = 0;

        for (int[] house : houses) {
            int hr = house[0];
            int hc = house[1];

            int best = Integer.MAX_VALUE;
            for (int[] chicken : chickens) {
                int cr = chicken[0];
                int cc = chicken[1];
                best = Math.min(best, Math.abs(hr - cr) + Math.abs(hc - cc));
            }

            total += best;
        }
        return total;
    }
}
