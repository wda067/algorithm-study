import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
백준 / 세 수의 합 / 골드4
https://www.acmicpc.net/problem/2295
 */
public class BOJ_2295 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] set = new int[N];
        for (int i = 0; i < N; i++) {
            set[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(set);

        List<Integer> sum = new ArrayList<>();  // 두 수의 합을 저장
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum.add(set[i] + set[j]);
            }
        }

        Collections.sort(sum);

        int answer = 0;

        for (int k = 0; k < N; k++) {  // 세 수의 합 -> k번째 수
            for (int i = 0; i < N; i++) {  // 세 수 중 하나로 선택된 값
                int target = set[k] - set[i];  // 선택된 수를 제외한 두 수의 합
                if (Collections.binarySearch(sum, target) >= 0) {  // 정렬된 List -> 이진 탐색
                    answer = Math.max(answer, set[k]);
                }
            }
        }

        System.out.println(answer);
    }
}
