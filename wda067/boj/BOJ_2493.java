import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
백준 / 탑 / 골드5
https://www.acmicpc.net/problem/2493
 */
public class BOJ_2493 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] towels = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            towels[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder result = new StringBuilder();
        Stack<int[]> stack = new Stack<>();  // 탑 후보 {인덱스, 높이} 저장
        for (int i = 0; i < N; i++) {
            int curH = towels[i];

            // 현재 탑보다 낮은 후보 제거
            while (!stack.isEmpty() && stack.peek()[1] < curH) {
                stack.pop();
            }

            if (stack.isEmpty()) {  // 탑 후보 X
                result.append("0").append(" ");
            } else {  // 가장 가까운 탑 후보
                result.append(stack.peek()[0]).append(" ");
            }

            // 현재 탑을 후보로 등록
            stack.push(new int[]{i + 1, curH});
        }

        System.out.println(result);
    }
}
