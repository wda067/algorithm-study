import sys
from collections import deque
input = sys.stdin.readline

N = 5
K = 7
board = [list(input().rstrip()) for _ in range(N)]

# ‘소문난 칠공주’를 결성할 수 있는 모든 경우의 수를 출력
# 서로 가로나 세로로 반드시 인접
# S가 최소 4명 이상
# 7명이어야 함
# BFS로 인접한거 다 넣어야 하나

# 중복없는 조합을 먼저 구한 다음에
# 그 조합이 조건에 부합하는지 BFS를 통해 확인해야함 

answer = 0
selected = []

# 인덱스 -> 좌표 변환
def idx_to_xy(idx):
    return idx // N, idx % N


def bfs_check():
    # 선택된 칸을 빠르게 확인하기 위한 set
    sel_set = set(selected)
    q = deque()
    visited = set()

    # 첫 번째 칸에서 BFS 시작
    x, y = idx_to_xy(selected[0])
    q.append((x, y))
    visited.add(selected[0])

    while q:
        x, y = q.popleft()
        for dx, dy in [(-1,0),(1,0),(0,-1),(0,1)]:
            nx, ny = x + dx, y + dy
            if 0 <= nx < N and 0 <= ny < N:
                ni = nx * N + ny
                if ni in sel_set and ni not in visited:
                    visited.add(ni)
                    q.append((nx, ny))

    # 7칸이 모두 연결되어 있으면 True
    return len(visited) == K


def dfs(start, cnt, s_cnt):
    global answer

    # 가지치기: S가 4개 미만이면 의미 없음
    if cnt == K:
        if s_cnt >= 4 and bfs_check():
            answer += 1
        return

    for i in range(start, N * N):
        x, y = idx_to_xy(i)
        selected.append(i)

        if board[x][y] == 'S':
            dfs(i + 1, cnt + 1, s_cnt + 1)
        else:
            dfs(i + 1, cnt + 1, s_cnt)

        selected.pop()


dfs(0, 0, 0)
print(answer)
