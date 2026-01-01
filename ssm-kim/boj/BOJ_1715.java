import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 최소 힙: 가장 작은 카드 묶음부터 꺼내기 위함
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });

        for (int i = 0; i < n; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int compareCnt = 0;
        while (!pq.isEmpty()) {
            if (pq.size() == 1) break;

            // 가장 작은 두 묶음을 꺼내서 합침
            int first = pq.poll();
            int second = pq.poll();
            int sum = first + second;

            // 합친 묶음을 다시 우선순위 큐에 추가
            pq.offer(sum);
            // 현재 합친 비용을 누적 (두 묶음을 합칠 때마다 sum만큼의 비교 발생)
            compareCnt += sum;
        }
        System.out.println(compareCnt);
    }
}