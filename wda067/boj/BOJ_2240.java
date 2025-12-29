import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
백준 / 자두나무 / 골드4
https://www.acmicpc.net/problem/2240
 */
public class BOJ_2240 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] arr = new int[T + 1];
        for (int i = 1; i <= T; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // dp[i][j]: i초까지 j번 움직였을 때 최대 자두
        int[][] dp = new int[T + 1][W + 1];
        for (int i = 1; i <= T; i++) {
            int jadu = arr[i];

            // 움직인 횟수 0일 때 1번에 자두가 떨어지는 경우
            if (jadu == 1) {
                dp[i][0] = dp[i - 1][0] + 1;
            }

            // W번까지 움직였을 때
            for (int j = 1; j <= W; j++) {
                int pos = 1;        // 움직인 횟수 짝수 -> 현재 위치 1번
                if (j % 2 != 0) {   // 움직인 횟수 홀수 -> 현재 위치 2번
                    pos = 2;
                }

                int gain = 0;
                if (pos == jadu) {  // 현재 위치에 자두가 떨어지면 획득
                    gain = 1;
                }

                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + gain;
            }
        }

        int result = Arrays.stream(dp[T])
                .max()
                .getAsInt();
        System.out.println(result);
    }
}
