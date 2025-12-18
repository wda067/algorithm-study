import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] topHeight = new int[n];
        for (int i = 0; i < n; i++) {
            topHeight[i] = Integer.parseInt(st.nextToken());
        }

        ArrayDeque<Integer> stack =  new ArrayDeque<>();
        int[] answer = new int[n];

        // 뒤에서부터 탐색 (오른쪽에서 왼쪽으로)
        for (int i = n - 1; i >= 0; i--) {
            int current = topHeight[i];

            // 현재 탑보다 낮은 탑들은 현재 탑에서 수신 가능
            while (!stack.isEmpty() && current > topHeight[stack.peek()]) {
                int lastIdx = stack.pop();
                answer[lastIdx] = i + 1;
            }
            stack.push(i);  // 현재 탑을 스택에 추가 (다음 탐색 대상)
        }

        StringBuilder sb = new StringBuilder();
        for (int num : answer) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}