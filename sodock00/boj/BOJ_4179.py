# 4179 불!
import sys
input = sys.stdin.readline
from collections import deque

R, C = map(int, input().split())
board = [list(input().strip()) for _ in range(R)]
jvisited = [[-1]*C for _ in range(R)]
fvisited = [[-1]*C for _ in range(R)]

# 불이 도달하기 전에 미로 탈출 X이면 IMPOSSIBLE 출력
# 미로를 탈출할 수 있는 가장 빠른 탈출 시간

# 불 확장을 반복문을 통해서
# 최단거리는 BFS!! 
# 불의 최단 거리와 병훈이의 최단 거리를 각각 구함
# 지훈이의 최단 거리가 불의 최단 거리보다 가까울 경우가 탈출에 성공한 case임
# 불은 동서남북으로 퍼지니 미리 선언해줌

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

jq = deque()
fq = deque()
for i in range(R):
    for j in range(C):
        if board[i][j] == 'J':
            jvisited[i][j] = 0
            jq.append((i,j))
        elif board[i][j] == 'F':
            fvisited[i][j] = 0
            fq.append((i,j))

# 불
while fq:
    x,y = fq.popleft()
    for d in range(4):
        nx, ny = x+dx[d], y+dy[d]
        if 0 <= nx < R and 0 <= ny < C:
            if board[nx][ny] != '#' and fvisited[nx][ny] == -1:
                fvisited[nx][ny] = fvisited[x][y] + 1
                fq.append((nx,ny))

# 지훈이
while jq:
    x,y = jq.popleft()
    for d in range(4):
        nx, ny = x+dx[d], y+dy[d]
        if not (0 <= nx < R and 0 <= ny < C):
            print(jvisited[x][y] + 1)
            sys.exit()
        if board[nx][ny] == '#' or jvisited[nx][ny] != -1:
            continue
        if fvisited[nx][ny] != -1 and fvisited[nx][ny] <= jvisited[x][y] + 1:
            continue
        jvisited[nx][ny] = jvisited[x][y] + 1
        jq.append((nx,ny))
                
print("IMPOSSIBLE")