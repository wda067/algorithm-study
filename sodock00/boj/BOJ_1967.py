import sys
sys.setrecursionlimit(10**7)
input = sys.stdin.readline

N = int(input())

# step 1. 그래프 구성 (양방향)
graph = [[] for _ in range(N + 1)]
for _ in range(N - 1):
    a, b, w = map(int, input().split())
    graph[a].append((b, w))
    graph[b].append((a, w))

# step 2. DFS 함수 정의
def dfs(start):
    visited = [False] * (N + 1)
    max_dist = 0
    far_node = start

    def _dfs(node, dist):
        nonlocal max_dist, far_node
        visited[node] = True

        if dist > max_dist:
            max_dist = dist
            far_node = node

        for next_node, weight in graph[node]:
            if not visited[next_node]:
                _dfs(next_node, dist + weight)

    _dfs(start, 0)
    return far_node, max_dist

# step 3. 임의의 노드(1)에서 가장 먼 노드 찾기
node, _ = dfs(1)

# step 4. 해당 노드에서 다시 DFS → 트리의 지름
_, answer = dfs(node)

print(answer)
