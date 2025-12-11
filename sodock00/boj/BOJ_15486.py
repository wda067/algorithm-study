import sys
input = sys.stdin.readline

N = int(input())
T = [0] * N
P = [0] * N

for i in range(N):
    T[i], P[i] = map(int, input().split())

# 현재 날짜가 가질 수 있는 최댓값 (이전 값과 현재 값 중 max)
dp = [0] * (N+1)
for i in range(N):
    left_date = N - i + 1
    if left_date > T[i]:
        dp[i + T[i]]= max(dp[i] + P[i] ,dp[i + T[i]])
    dp[i+1] = max(dp[i], dp[i+1])

print(max(dp))