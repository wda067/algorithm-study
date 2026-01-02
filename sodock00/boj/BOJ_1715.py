# 1715 카드 정렬하기
# 카드 묶음 최소한 비교 횟수 구하기
import sys
input = sys.stdin.readline

N = int(input())
cards = []
for _ in range(N):
    num = int(input())
    heapq.heappush(cards, num)

result = 0
while len(cards)>1:
    n1 = heapq.heappop(cards)
    n2 = heapq.heappop(cards)
    result += n1 + n2
    heapq.heappush(cards, n1+n2)

print(result)