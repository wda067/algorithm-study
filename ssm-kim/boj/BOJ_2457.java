import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] flowers = new int[n][2];  // [시작일, 종료일]

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sm = Integer.parseInt(st.nextToken());
            int sd = Integer.parseInt(st.nextToken());
            int em = Integer.parseInt(st.nextToken());
            int ed = Integer.parseInt(st.nextToken());

            // 날짜를 정수로 변환 (월*100 + 일)
            flowers[i][0] = sm * 100 + sd;
            flowers[i][1] = em * 100 + ed;
        }

        // 시작일 기준 오름차순
        Arrays.sort(flowers, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        int target = 301;  // 3월 1일부터 시작
        int end = 1201;    // 11월 30일까지
        int count = 0;
        int idx = 0;

        while (target < end) {
            int maxEnd = 0;

            // target 이전에 피기 시작하는 꽃들 중 가장 늦게 피는 꽃 선택
            while (idx < n && flowers[idx][0] <= target) {
                maxEnd = Math.max(maxEnd, flowers[idx][1]);
                idx++;
            }

            // 선택할 꽃이 없다면 불가능
            if (maxEnd == 0) {
                System.out.println(0);
                return;
            }

            count++;
            target = maxEnd;  // 다음 커버 시작점 업데이트
        }

        System.out.println(count);
    }
}