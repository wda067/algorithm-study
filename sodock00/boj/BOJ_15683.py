# CCTV 5종류
# 90도로 회전 가능
# 0: 빈칸/ 6: 벽/ 1-5: CCTV
# 사각지대의 최소 크기

import sys
input = sys.stdin.readline

N, M = map(int, input().split())
board = [[6]*(M+2)] + [[6] + list(map(int, input().split())) + [6] for _ in range(N)] + [[6]*(M+2)]

# CCTV가 여러개인 경우에는 각각의 회전 case를 고려해서 사각지대의 갯수를 result list에 넣음
# CCTV의 direction을 미리 정의해두는게 킥
# CCTV 저장
cList = []
for i in range (1, N+1):
    for j in range (1, M+1):
        if 1 <= board[i][j] <= 5:
            cList.append((i,j))
# 0, 1, 2, 3 : 상, 우, 하, 좌 (회전 순)
cctvDir = [
    [],
    [1],
    [1,3],
    [0,1],
    [0,1,3],
    [0,1,2,3]
]
dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

def cal(tList):
    visited = [[0]*(M+2) for _ in range(N+2)]
    for i in range(CNT):
        si, sj = cList[i]
        rotate = tList[i]
        cType = board[si][sj]

        for dr in cctvDir[cType]:
            dr = (dr + rotate) % 4
            ci, cj = si, sj
            while True:
                ci, cj = ci + dx[dr], cj + dy[dr]
                if board[ci][cj]==6:
                    break
                visited[ci][cj]=1
    # 사각지대 = visited 0 이면서 board 0 인 갯수
    tCnt = 0
    for i in range(1, N+1):
        for j in range(1, M+1):
            if board[i][j]==0 and visited[i][j]==0:
                tCnt+=1
    return tCnt

def dfs(n, tList):
    global answer
    # 종료 조건
    if n == CNT:
        answer = min(answer, cal(tList))
        return
    dfs(n+1, tList+[0])
    dfs(n+1, tList+[1])
    dfs(n+1, tList+[2])
    dfs(n+1, tList+[3])

CNT = len(cList)
answer = N*M
dfs(0, [])
print(answer)
    
