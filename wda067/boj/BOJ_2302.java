import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
백준 / 극장 좌석 / 골드5
https://www.acmicpc.net/problem/2302
 */
public class BOJ_2302 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());  // 좌석의 개수
        int M = Integer.parseInt(br.readLine());  // 고정석의 개수

        List<Integer> seats = new ArrayList<>();
        seats.add(0);
        for (int i = 0; i < M; i++) {
            seats.add(Integer.parseInt(br.readLine()));
        }
        seats.add(N + 1);

        // dp[L]: 길이 L 구간의 경우의 수
        int[] dp = new int[41];
        dp[0] = 1;
        dp[1] = 1;

        // 맨 앞 사람이 안 바꾸는 경우 -> dp[i - 1]
        // 맨 앞 사람이 바꾸는 경우 -> dp[i - 2]
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // 각 구간들의 길이에 맞게 경우의 수를 구함
        int answer = 1;
        for (int i = 1; i < seats.size(); i++) {
            int len = seats.get(i) - seats.get(i - 1) - 1;
            answer += dp[len];
        }

        System.out.println(answer);
    }
}
