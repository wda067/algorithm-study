# 2629번 양팔저울
# 주어진 추 만을 사용해서 구슬의 무게를 확인할 수 있는 지 결정하는 프로그램

import sys
input = sys.stdin.readline

# 추: n / 구슬: m
n = int(input())
nList = list(map(int, input().split()))
m = int(input())
mList = list(map(int, input().split()))

# 구슬에 대해 무게 확인이 가능하면 Y / 아니면 N
# 여러가지 경우의 수를 확인해야 함
# 각 추를 놓을 경우의 수 : 추 왼쪽, 추 오른쪽, 추 놓지 않음
# dp 점화식 : dp[i][j] - i번째 추까지 사용해서 j 무게 판별 가능
dp = [[0 for _ in range((500*j)+1)] for j in range(n+1)]
answer = []

def calc(num, weight):
    if num > n:
        return
    if dp[num][weight] == 1:
        return
    dp[num][weight] = 1
    calc(num+1, weight + nList[num-1]) # 1번 추 넣기
    calc(num+1, weight) # 2번 그대로
    calc(num+1, abs(weight - nList[num-1])) # 3번 추 다른쪽

calc(0, 0)

for mm in mList:
    if mm > 500*n:
        answer.append('N')
    elif dp[n][mm] == 1:
        answer.append('Y')
    else:
        answer.append('N')

print(*answer)