import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
백준 / 회의실 배정 / 골드5
https://www.acmicpc.net/problem/1931
 */
public class BOJ_1931 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] time = new int[N][2];
        StringTokenizer st;

        // 입력값 세팅
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i][0] = Integer.parseInt(st.nextToken());
            time[i][1] = Integer.parseInt(st.nextToken());
        }

        // time 배열 정렬
        Arrays.sort(time, Comparator
                .comparingInt((int[] end) -> end[1])  // 종료 시간에 대해 정렬
                .thenComparingInt(start -> start[0]));  // 종료 시간이 같을 경우 시작 시간으로 정렬

        int count = 0;
        int beforeEnd = 0;

        for (int i = 0; i < N; i++) {
            int start = time[i][0];
            int end = time[i][1];

            // 시작 시간이 이전 끝시간보다 크거나 같으면 갱신 및 카운트
            if (beforeEnd <= start) {
                beforeEnd = end;
                count++;
            }
        }

        System.out.println(count);
    }
}
