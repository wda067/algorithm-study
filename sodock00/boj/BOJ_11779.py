import sys
input = sys.stdin.readline

def dijkstra(start):
    q = []
    distance[start] = 0
    heapq.heappush(q, (0, start, [start]))
    while q:
        dist, now, nodes = heapq.heappop(q)
        if distance[now] < dist:
            continue
        for node, weight in graph[now]:
            weight += dist
            if weight < distance[node]:
                distance[node] = weight
                route[node] = nodes + [node]
                heapq.heappush(q, (weight, node, nodes + [node]))

n = int(input())
m = int(input())
graph = [[] for _ in range(n + 1)]
distance = [1e9] * (n + 1)
route = [[] for _ in range(n + 1)]
for _ in range(m):
    s, e, w = map(int, input().split())
    graph[s].append((e, w))
start, end = map(int, input().split())
dijkstra(start)

print(distance[end])
print(len(route[end]))
print(' '.join(map(str, route[end])))