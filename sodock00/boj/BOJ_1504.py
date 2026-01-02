# 1504 특정한 최단 경로
# 주어진 두 정점을 반드시 거치는 최단경로
import sys
input = sys.stdin.readline

N, E = map(int, input().split())
graph = [[] for _ in range(N+1)]
for _ in range(E):
    a, b, c = map(int, input().split())
    graph[a].append((b, c))
    graph[b].append((a, c))
v1, v2 = map(int, input().split())
d1 = dijkstra(1)
d2 = dijkstra(v1)
d3 = dijkstra(v2)
result = min(d1[v1]+d2[v2]+d3[N], d1[v2]+d3[v1]+d2[N])
if result < sys.maxsize:
    print(result)
else:
    print(-1)

def dijkstra(start):
    q = []
    heapq.heappush(q, (0, start))
    distance = [INF]*(N+1)
    distance[start] = 0
    while q:
        d, now = heapq.heappop(q)
        if distance[now] < d:
            continue
        for v, w in graph[now]:
            cost = d + w
            if cost < distance[v]:
                distance[v] = cost
                heapq.heappush(q, (cost, v))
    return distance