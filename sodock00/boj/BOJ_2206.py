import sys
from collections import deque
input = sys.stdin.readline

# 최단경로 문제
# 근데 이제 벽을 하나 부술 수 있는...

N, M = map(int, input().split())
board = [list(map(int, input().rstrip())) for _ in range(N)]

def bfs(x, y, b):
    q = deque()
    q.append((x, y, b))
    visited = [[[0]*2 for _ in range(M)] for _ in range(N)]
    visited[x][y][b] = 1
    
    while q:
        cx, cy, b = q.popleft()
        if cx == N-1 and cy == M-1:
            return visited[cx][cy][b]
        for dx, dy in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
            nx, ny = cx + dx, cy + dy
            if 0<=nx<N and 0<=ny<M:
                if board[nx][ny] == 0 and visited[nx][ny][b] == 0:
                    visited[nx][ny][b] = visited[cx][cy][b] + 1
                    q.append((nx, ny, b))

                # 벽인데 아직 안 부쉈다면
                elif board[nx][ny] == 1 and b == 0 and visited[nx][ny][1] == 0:
                    visited[nx][ny][1] = visited[cx][cy][b] + 1
                    q.append((nx, ny, 1))
    return -1 
    
print(bfs(0, 0, 0))