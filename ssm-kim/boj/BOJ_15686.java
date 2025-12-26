import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int n, m, answer;
    static int[] seq;
    static ArrayList<int[]> chickenPath = new ArrayList<>();
    static ArrayList<int[]> homePath = new ArrayList<>();
    static int[][] board;

    public static void main(String[] args) throws Exception {
         System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][n];

        // 집과 치킨집 위치 저장
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    homePath.add(new int[] {i, j});
                }
                if (board[i][j] == 2) {
                    chickenPath.add(new int[] {i, j});
                }
            }
        }

        seq = new int[m];
        answer = Integer.MAX_VALUE;
        combinations(0, 0);

        System.out.println(answer);
    }

    // 전체 치킨집 중 m개를 선택하는 조합 생성
    static void combinations(int depth, int start) {
        if (depth == m) {
            int totalDist = 0;

            // 각 집마다 선택된 치킨집들 중 최소 거리 계산
            for (int[] hPath : homePath) {
                int homeX = hPath[0];
                int homeY = hPath[1];
                int minChickenDistance = Integer.MAX_VALUE;

                // 선택된 m개 치킨집과의 거리 비교
                for (int i = 0; i < m; i++) {
                    int selectNum = seq[i];
                    int[] cPath = chickenPath.get(selectNum);

                    int chickenX = cPath[0];
                    int chickenY = cPath[1];

                    // 맨해튼 거리 계산
                    int distance = Math.abs(homeX - chickenX) + Math.abs(homeY - chickenY);
                    minChickenDistance = Math.min(minChickenDistance, distance);
                }

                totalDist += minChickenDistance;
            }
            answer = Math.min(answer, totalDist);
            return;
        }

        for (int i = start; i < chickenPath.size(); i++) {
            seq[depth] = i;
            combinations(depth + 1, i + 1);
        }
    }
}