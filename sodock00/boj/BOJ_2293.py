import sys
input = sys.stdin.readline

# 입력
N, K = map(int, input().split())
coins = [int(input()) for _ in range(N)]

# step 1. dp 테이블 선언
dp = [0] * (K + 1)
dp[0] = 1  # 0원을 만드는 방법은 1가지

# step 2. 각 동전마다 dp 업데이트
for coin in coins:
    for j in range(coin, K + 1):
        dp[j] += dp[j - coin]

# step 3. 정답 출력
print(dp[K])
