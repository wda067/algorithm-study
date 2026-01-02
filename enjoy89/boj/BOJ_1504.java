package boj;

import java.io.*;
import java.util.*;

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

	static List<Node>[] graph;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		graph = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			// 양방향 그래프
			graph[a].add(new Node(b, c));
			graph[b].add(new Node(a, c));
		}

		st = new StringTokenizer(br.readLine());
		// 반드시 거쳐야 하는 경로 2가지
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());

		int[] dist1 = dijkstra(1); // 1번에서 각 노드의 최단 거리
		int[] distV1 = dijkstra(v1); // v1에서 각 노드의 최단 거리
		int[] distV2 = dijkstra(v2); // v2에서 각 노드의 최단 거리

		// 1 -> v1 -> v2 -> N
		long route1 = (long)dist1[v1] + distV1[v2] + distV2[N];

		// 1 -> v2 -> v1 -> N
		long route2 = (long)dist1[v2] + distV2[v1] + distV1[N];

		long answer = Math.min(route1, route2);
		if (answer >= Integer.MAX_VALUE) {
			answer = -1;
		}
		System.out.println(answer);

	}

	public static int[] dijkstra(int start) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			for (Node next : graph[cur.vertex]) {
				int newCost = cur.cost + next.cost;
				if (newCost < dist[next.vertex]) {
					dist[next.vertex] = newCost;
					pq.offer(new Node(next.vertex, newCost));
				}
			}
		}

		return dist;
	}
}
