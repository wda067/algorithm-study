import sys
import heapq
input = sys.stdin.readline

# 입력
M, N = map(int, input().split())
maze = [list(map(int, input().rstrip())) for _ in range(N)]

# step 1. 거리 저장 배열 (부순 벽 최소 개수)
INF = int(1e9)
dist = [[INF] * M for _ in range(N)]
dist[0][0] = 0

# step 2. 다익스트라(최소 가중치) 탐색
pq = [(0, 0, 0)]  # (부순 벽 개수, x, y)
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

while pq:
    broken, x, y = heapq.heappop(pq)
    if dist[x][y] < broken:
        continue
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if 0 <= nx < N and 0 <= ny < M:
            cost = broken + maze[nx][ny]
            if cost < dist[nx][ny]:
                dist[nx][ny] = cost
                heapq.heappush(pq, (cost, nx, ny))

# step 3. 결과 출력
print(dist[N-1][M-1])
