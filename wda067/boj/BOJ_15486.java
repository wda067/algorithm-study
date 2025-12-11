import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 퇴사 2 / 골드5
https://www.acmicpc.net/problem/15486
 */
public class BOJ_15486 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 2];  // dp[i]: 1일까지 최대 수익
        int[] times = new int[N + 1];
        int[] payments = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());

            times[i] = T;
            payments[i] = P;
        }

        for (int i = 1; i <= N; i++) {
            int endDate = i + times[i];
            if (endDate <= N + 1) {
                dp[endDate] = Math.max(dp[endDate], dp[i] + payments[i]);
            }

            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
        }

        System.out.println(dp[N + 1]);
    }
}

