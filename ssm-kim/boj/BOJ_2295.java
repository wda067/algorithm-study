import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    static int n;
    static int[] u;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        u = new int[n];
        for (int i = 0; i < n; i++) {
            u[i] = Integer.parseInt(br.readLine());
        }

        // 모든 두 수의 합(x+y) 저장
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                numbers.add(u[i] + u[j]);
            }
        }

        Arrays.sort(u);
        Collections.sort(numbers);

        // k를 큰 것부터, k-z가 두 수의 합에 존재하는지 이분탐색
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (binarySearch(u[i] - u[j], numbers)) {
                    System.out.println(u[i]);
                    return;
                }
            }
        }
    }

    static boolean binarySearch(int target, ArrayList<Integer> numbers) {
        int lt = 0;
        int rt = numbers.size() - 1;

        while (lt <= rt) {
            int mid = (lt + rt) / 2;

            if (numbers.get(mid) >= target) {
                if (numbers.get(mid) == target) {
                    return true;
                }
                rt = mid - 1;
            } else {
                lt = mid + 1;
            }
        }
        return false;
    }
}