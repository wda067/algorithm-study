import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
백준 / 카드 정렬하기 / 골드4
https://www.acmicpc.net/problem/1715
 */
public class BOJ_1715 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        int total = 0;
        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            int sum = a + b;
            total += sum;
            pq.add(sum);
        }

        System.out.println(total);
    }
}
