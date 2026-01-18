import sys
from collections import deque
input = sys.stdin.readline

# 입력
N, M = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(N)]

# 상하좌우 방향
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

year = 0

# 빙산 덩어리 개수 세는 함수
def count_ice():
    visited = [[False]*M for _ in range(N)]
    cnt = 0
    
    for i in range(N):
        for j in range(M):
            if graph[i][j] > 0 and not visited[i][j]:
                cnt += 1
                queue = deque([(i, j)])
                visited[i][j] = True
                while queue:
                    x, y = queue.popleft()
                    for d in range(4):
                        nx = x + dx[d]
                        ny = y + dy[d]
                        if 0 <= nx < N and 0 <= ny < M:
                            if graph[nx][ny] > 0 and not visited[nx][ny]:
                                visited[nx][ny] = True
                                queue.append((nx, ny))
    return cnt

# 빙산 녹이는 함수
def melt():
    # 녹을 양 기록 (빙산 좌표, 녹는 높이)
    diffs = []
    for i in range(N):
        for j in range(M):
            if graph[i][j] > 0:
                sea = 0
                for d in range(4):
                    nx = i + dx[d]
                    ny = j + dy[d]
                    if 0 <= nx < N and 0 <= ny < M and graph[nx][ny] == 0:
                        sea += 1
                if sea > 0:
                    diffs.append((i, j, sea))

    # 실제 낮추기
    for x, y, sea_cnt in diffs:
        graph[x][y] = max(0, graph[x][y] - sea_cnt)

while True:
    cnt = count_ice()
    # 두 덩어리 이상 -> 분리된 연도 출력
    if cnt >= 2:
        print(year)
        break
    # 전부 녹음
    if cnt == 0:
        print(0)
        break
    
    # 빙산 녹이기
    melt()
    year += 1
