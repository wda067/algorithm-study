import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] meeting;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        meeting = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            meeting[i][0] = start;
            meeting[i][1] = end;
        }

        // 끝나는 시각 기준 오름차순 정렬
        Arrays.sort(meeting, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 끝나는 시각이 같으면 시작 시간이 더 빠른 걸로 정렬
                if (o1[1] == o2[1]) {
                    return Integer.compare(o1[0], o2[0]);
                }
                return Integer.compare(o1[1], o2[1]);
            }
        });

        int answer = 1;
        int beforeEndTime = meeting[0][1];  // 처음 강의 끝나는 시간
        for (int i = 1; i < n; i++) {
            int start = meeting[i][0];
            int end = meeting[i][1];

            if (start >= beforeEndTime){
                beforeEndTime = end;
                answer++;
            }
        }
        System.out.println(answer);
    }
}