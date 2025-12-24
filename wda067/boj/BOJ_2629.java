import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 양팔저울 / 골드3
https://www.acmicpc.net/problem/2629
 */
public class BOJ_2629 {

    private static final int MAX_WEIGHT = 40_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());  // 추의 개수
        int[] A = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i][j]: i번째 추까지 사용하여 무게 j 측정 가능 여부
        boolean[][] dp = new boolean[N + 1][MAX_WEIGHT];
        dp[0][0] = true;  // 무게 0은 항상 측정 가능

        for (int i = 1; i <= N; i++) {  // 모든 추를 반복
            for (int j = 0; j < MAX_WEIGHT; j++) {  // 만들 수 있는 무게 j
                if (dp[i - 1][j]) { // 현재 무게를 측정할 수 있는 경우
                    int cur = A[i];  // 현재 추의 무게

                    dp[i][j] = true;  // 현재 추를 사용 X

                    // [ 구슬 ] v [    ] -> 구슬은 왼쪽 고정

                    // [ 구슬 + 추 ] v [    ]
                    // 현재 추를 왼쪽에 둠 -> 현재 추만큼 측정할 수 있는 무게 증가
                    if (cur + j < MAX_WEIGHT) {
                        dp[i][cur + j] = true;
                    }

                    // [ 구슬 ] v [  추  ]
                    // 현재 추를 오른쪽에 둠 -> 추의 무게만큼 측정할 수 있는 무게 감소
                    int diff = Math.abs(cur - j);
                    dp[i][diff] = true;
                }
            }
        }

        int M = Integer.parseInt(br.readLine());  // 구슬의 개수
        st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int w = Integer.parseInt(st.nextToken());  // 구슬의 무게

            if (dp[N][w]) {
                sb.append('Y');
            } else {
                sb.append('N');
            }
            sb.append(" ");
        }

        System.out.println(sb);
    }
}
