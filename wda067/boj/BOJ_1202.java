import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
백준 / 보석 도둑 / 골드2
https://www.acmicpc.net/problem/1202
 */
public class BOJ_1202 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 보석 무게 오름차순
        PriorityQueue<Jewel> jewels = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            jewels.add(new Jewel(M, V));
        }

        // 가방 무게 오름차순
        PriorityQueue<Integer> bags = new PriorityQueue<>();
        for (int i = 0; i < K; i++) {
            bags.add(Integer.parseInt(br.readLine()));
        }

        // 보석 후보, 가치 내림차순
        PriorityQueue<Integer> values = new PriorityQueue<>(Comparator.reverseOrder());
        long total = 0;  // 1,000,000 * 300,000

        // 작은 가방부터 꺼냄 -> 넣을 수 있는 가장 가치가 높은 보석 탐색
        while (!bags.isEmpty()) {
            int bag = bags.poll();

            // 현재 가방에 넣을 수 있는 보석 모두 삽입
            while (!jewels.isEmpty() && jewels.peek().weight <= bag) {
                values.add(jewels.poll().value);  // 넣을 수 있는 보석의 가치를 삽입
            }

            // 가장 가치가 높은 보석 하나 선택
            if (!values.isEmpty()) {
                total += values.poll();
            }
        }

        System.out.println(total);
    }

    private static class Jewel {
        private int weight, value;

        public Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
