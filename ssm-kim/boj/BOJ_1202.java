import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Gem implements Comparable<Gem> {
    int weight, price;

    public Gem(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    @Override
    public int compareTo(Gem o) {
        return Integer.compare(this.weight, o.weight);
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Gem> gemList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            gemList.add(new Gem(m, v));
        }

        int[] bags = new int[k];
        for (int i = 0; i < k; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Collections.sort(gemList);  // 보석 무게 오름차순
        Arrays.sort(bags);          // 가방 용량 오름차순

        // 가격 기준 최대힙 (현재 가방에 넣을 수 있는 보석들의 후보군)
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
        });

        long answer = 0;
        int idx = 0;
        for (int bagCapacity : bags) {
            // 현재 가방에 넣을 수 있는 모든 보석을 후보군(PQ)에 추가
            while (idx < n && bagCapacity >= gemList.get(idx).weight) {
                pq.offer(gemList.get(idx).price);
                idx++;
            }

            // 그리디: 후보 중 가장 비싼 보석 선택
            if (!pq.isEmpty()) {
                answer += pq.poll();
            }
        }

        System.out.println(answer);
    }
}