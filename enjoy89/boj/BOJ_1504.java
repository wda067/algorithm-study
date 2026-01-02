package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1504 {

	static class Node implements Comparable<Node> {
		int vertex;
		int cost;

		Node(int vertex, int cost) {
			this.vertex = vertex;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	static int N;
	static List<List<Node>> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b, c));
			graph.get(b).add(new Node(a, c));
		}

		st = new StringTokenizer(br.readLine());
		// 반드시 거쳐야 하는 경로 2개
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());

		int[] dist = dijkstra(1);
		int[] distV1 = dijkstra(v1);
		int[] distV2 = dijkstra(v2);

		// 1 -> v1 -> v2 -> N
		long r1 = (long)dist[v1] + distV1[v2] + distV2[N];
		// 1 -> v2 -> v1 -> N
		long r2 = (long)dist[v2] + distV2[v1] + distV1[N];

		long answer = Math.min(r1, r2);
		if (answer >= Integer.MAX_VALUE) {
			answer = -1;
		}
		System.out.println(answer);
	}

	public static int[] dijkstra(int start) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		dist[start] = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			for (Node next : graph.get(cur.vertex)) {
				int newCost = cur.cost + next.cost;
				if (dist[next.vertex] > newCost) {
					dist[next.vertex] = newCost;
					pq.offer(new Node(next.vertex, newCost));
				}
			}
		}
		return dist;
	}
}
