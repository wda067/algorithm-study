# 며칠이 지나면 토마토가 모두 익는지 최소 일 수 구하기
# 하루가 지나면 인접한 부분은 모두 익게 됨
# 반복문을 돌면서 bfs로 인접한 것 1로 변경하면서 진행
# 모두 익지 못하는 상황이면 -1 출력
# 1: 익은 토마토, 0: 익지 않은 토마토, -1: 토마토가 들어있지 않은 칸

# -> 틀린 이유 : 익은 토마토마다 따로 돌리면 안되고, 동시에 돌려야 함
# 큐에 한번에 넣은 다음 한번에 돌려야 함
#######################################

import sys
input = sys.stdin.readline
from collections import deque

M, N = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
def bfs():
    q = deque()
    for i in range(N):
        for j in range(M):
            if board[i][j] == 1:
                q.append((i, j))
    while q:
        cx, cy = q.popleft()
        for i in range(4):
            nx, ny = cx + dx[i], cy + dy[i]
            if nx < 0 or nx >= N or ny < 0 or ny >= M:
                continue
            if board[nx][ny] == 0:
                board[nx][ny] = board[cx][cy] + 1
                q.append((nx, ny))

bfs()

result = 0
for i in range(N):
    for j in range(M):
        if board[i][j] == 0:
            print(-1)
            exit()
        result = max(result, board[i][j])

print(result - 1)