# 한 개의 회의실, N개의 회의
# 최대 사용할 수 있는 회의의 최대 개수 출력

# 최대한 많은 회의를 넣으려면, 끝나는 시간이 빠른 순으로 기입하는 것이 맞음
# N이 100000이라서 브루트포스는 불가능 -> 그리디로 가야함

import sys
input = sys.stdin.readline

N = int(input())
time = [list(map(int, input().split())) for _ in range(N)]
time.sort(key = lambda x : (x[1],x[0])) # 끝나는 시간 기준 정렬, 끝나는 시간 같다면 시작
ans = []

lastTime = 0
for i in range(N):
    # 시작 시간이 현재 예약되어있는 마지막 시간보다 늦다면
    if time[i][0] >= lastTime:
        ans.append(time[i])
        lastTime = time[i][1]

print(len(ans))