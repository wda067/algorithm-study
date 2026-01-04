import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[] plums = new int[t + 1];
        for (int i = 1; i <= t; i++) {
            plums[i] = Integer.parseInt(br.readLine());
        }

        // dp[i][j] = i초에 j번 이동했을 때 받은 자두 개수
        int[][] dp = new int[t + 1][w + 1];

        for (int i = 1; i <= t; i++) {
            for (int j = 0; j <= w; j++) {
                // 현재 위치: j가 짝수면 1번 나무, 홀수면 2번 나무
                int pos = (j % 2 == 0) ? 1 : 2;

                // 그대로 있기
                int stay = dp[i-1][j];

                // 이동하기 (j > 0일 때)
                int move = 0;
                if (j > 0) {
                    move = dp[i-1][j-1];
                }

                dp[i][j] = Math.max(stay, move);

                // 자두 받기
                if (pos == plums[i]) {
                    dp[i][j]++;
                }
            }
        }

        // 최댓값 찾기
        int answer = 0;
        for (int j = 0; j <= w; j++) {
            answer = Math.max(answer, dp[t][j]);
        }
        System.out.println(answer);
    }
}