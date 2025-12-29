# 2240 자두나무

import sys
input = sys.stdin.readline

T, W = map(int, input().split())
trees = [0] + [ + int(input()) for _ in range (T)]

# 자두가 받을 수 있는 자두의 개수를 구하기
# dp - 지금 선택이 결과에 누적되어 영향을 주기 때문
# t초에 w만큼 이동했을 때 얻을 수 있는 최대 자두의 갯수
# 주의할 점!! 나무가 2개 뿐이라서 w 값에 따라 현재 나무 위치를 알 수 있음

dp = [[0] * (W+1) for _ in range(T+1)]

for t in range(T+1):
    if (trees[t] == 1):
        dp[t][0] = dp[t-1][0] + 1
    else:
        dp[t][0] = dp[t-1][0]

    for w in range(1, W+1):
        if (trees[t] == 2 and w % 2 == 1):
            dp[t][w] = max(dp[t-1][w-1], dp[t-1][w]) + 1
        elif (trees[t] == 1 and w % 2 == 0):
            dp[t][w] = max(dp[t-1][w-1], dp[t-1][w]) + 1
        else:
            dp[t][w] = max(dp[t-1][w-1], dp[t-1][w])

print(max(dp[-1]))