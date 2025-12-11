import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] t = new int[n];  // 상담 소요 일수
        int[] p = new int[n];  // 상담 금액

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            int endDay = i + t[i];  // i일에 상담 시작하면 끝나는 날

            if (endDay <= n) {
                // 상담 O
                dp[i] = Math.max(p[i] + dp[endDay], dp[i + 1]);
            } else {
                // 상담 X
                dp[i] = dp[i + 1];
            }
        }
        System.out.println(dp[0]);
    }
}