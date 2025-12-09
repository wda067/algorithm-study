import sys
input = sys.stdin.readline

N = int(input())
# 퀸 N개를 서로 공격할 수 없으려면?
# 한 줄에 하나씩 퀸을 두는데, 세로줄과 대각선이 겹치면 안된다

def isValid(queenPosition, row, col):
    # 종료
    for r, c in enumerate(queenPosition):
        if c == col:
            return False
        if abs(c - col) == abs(r - row):
            return False
    return True

def backtrack(queenPosition):
    global result
    if len(queenPosition) == N:
        result.append(queenPosition[:])
        return
    for col in range(N):
        if isValid(queenPosition, len(queenPosition), col):
            queenPosition.append(col)
            backtrack(queenPosition) # 다음행
            queenPosition.pop() # 원래 상태로 복구

result = []
backtrack([])
print(len(result))