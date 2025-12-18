import sys
input = sys.stdin.readline

N = int(input())
tops = list(map(int, input().split(" ")))

# N : 1이상 500000이하
# 높이 : 1이상 100000000이하

# 각각의 탑에서 발사한 레이저 신호를 어느 탑에서 수신하는지 출력
# 수신하는 탑 없으면 0
# 나보다 크거나 같고, 가장 거리가 가까운 탑

# 그냥 반복문으로 돌리면 -> 시간초과
# 자료 구조 스택 사용해야함!

answer = [0] * N
stack = []
for i in range(N):
    while stack:
        if stack[-1][1] > tops[i]:
            answer[i] = stack[-1][0] + 1
            break
        else:
            stack.pop()
    stack.append((i, tops[i]))

print(answer)