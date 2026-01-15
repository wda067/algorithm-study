import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] home = new int[n];
        for (int i = 0; i < n; i++) {
            home[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(home);
        System.out.println(binarySearch(n, c, home));
    }

    static int binarySearch(int n, int c, int[] home) {
        int lt = 1;                      // 최소 간격
        int rt = home[n - 1] - home[0];  // 최대 간격
        int answer = 0;

        while (lt <= rt) {
            int mid = (lt + rt) / 2;  // 시도할 최소 간격

            int installCnt = 1;     // 첫 집에 무조건 설치
            int lastPos = home[0];  // 마지막 설치 위치
            for (int i = 1; i < n; i++) {
                if (home[i] - lastPos >= mid) {
                    lastPos = home[i];
                    installCnt++;
                }
            }

            // c개 설치 가능하면 더 큰 간격으로 시도
            if (installCnt >= c) {
                answer = mid;  // 최적값 갱신
                lt = mid + 1;
            }
            else {
                rt = mid - 1;
            }
        }
        return answer;
    }
}