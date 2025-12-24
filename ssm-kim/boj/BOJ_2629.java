import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import javax.management.BadBinaryOpValueExpException;

public class Main {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 추 입력
        int n = Integer.parseInt(br.readLine());
        int[] chu = new int[n];

        st = new StringTokenizer(br.readLine());
        int maxWeight = 0;
        for (int i = 0; i < n; i++) {
            chu[i] = Integer.parseInt(st.nextToken());
            maxWeight += chu[i];  // 모든 추를 더한 최대 무게
        }

        // DP 배열 : 이 무게를 만들 수 있나?
        boolean[] possible = new boolean[maxWeight + 1];
        possible[0] = true;  // 0g은 항상 만들 수 있음

        // 각 추마다 처리
        for (int i = 0; i < n; i++) {
            int weight = chu[i];

            // 뒤에서부터 처리 (중복 사용 방지)
            boolean[] newPossible = possible.clone();

            for (int j = 0; j <= maxWeight; j++) {
                if (possible[j]) {  // j 무게 만들 수 있다면

                    // 1. 현재 추를 더하기
                    if (j + weight <= maxWeight) {
                        newPossible[j + weight] = true;
                    }

                    // 2. 현재 추를 빼기 (같은 쪽에 놓기)
                    newPossible[Math.abs(j - weight)] = true;
                }
            }
            possible = newPossible;
        }

        // 구슬 무게 확인
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int marble = Integer.parseInt(st.nextToken());

            if (marble > maxWeight) {
                sb.append("N ");
            } else {
                sb.append(possible[marble] ? "Y " : "N ");
            }
        }
        System.out.println(sb);
    }
}