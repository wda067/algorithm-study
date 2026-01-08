import sys
input = sys.stdin.readline

N = int(input())
M = int(input())
vips = list(int(input()) for _ in range(M))

# step 1. vips 기준으로 seats를 덩어리로 나눈다
seatGroup = []
lastVip = 0
for v in vips:
    # 1-1. seat 덩어리의 수와, 각 덩어리의 갯수만 알면 됨
    seatGroup.append(v-lastVip-1)
    lastVip = v
seatGroup.append(N-lastVip)

# step 2. 각 덩어리 내부에서 가능한 경우의 수를 모두 구한다
# 2-1. max(seatGroup)인 dp 배열을 생성해서 덩어리 수 별 가능한 경우의 수 업데이트
dp = [0]*3 + [0 for _ in range(max(seatGroup)+1)]
dp[0] = 1
dp[1] = 1
dp[2] = 2
for n in range(3, len(dp)):
    dp[n] = dp[n-1] + dp[n-2]

# step 3. 덩어리 별로 나온 경우의 수를 모두 곱한다
result = 1
for group in seatGroup:
    result *= dp[group]

print(result)
    
