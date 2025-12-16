import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
백준 / 공주님의 정원 / 골드3
https://www.acmicpc.net/problem/2457
 */
public class BOJ_2457 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Flower> flowers = new PriorityQueue<>(Comparator.comparingInt((Flower f) -> f.start)
                .thenComparing(Comparator.comparingInt((Flower f) -> f.end)
                        .reversed()));

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int start = a * 100 + b;
            int end = c * 100 + d;
            flowers.add(new Flower(start, end));
        }

        int curDate = 301;
        int count = 0;

        while (curDate <= 1130) {  //11일 30일까지 반복
            int max = curDate;

            while (!flowers.isEmpty()) {
                if (curDate < flowers.peek().start) {  //curDate 이후에 꽃이 필 경우 스킵
                    break;
                }

                Flower flower = flowers.poll();
                max = Math.max(max, flower.end);
            }

            if (max == curDate) {
                System.out.println(0);
                return;
            }

            curDate = max;
            count++;
        }

        System.out.println(count);
    }

    static class Flower {
        private final int start, end;

        public Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
