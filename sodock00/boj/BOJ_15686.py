# 15686 치킨 배달

import sys
input = sys.stdin.readline
from itertools import combinations

n, m = map(int, input().split())
lst = []
for i in range(n):
    lst.append(list(map(int,input().split())))

# 집 좌표, 치킨집 좌표 모두 모은 다음에 치킨집 중에서 m개 고리는 모든 조합 생성
# combinations 사용
# 모든 조합에 대해 모든 집에 대해 가장 가까운 치킨집 거리 계산
# 도시랑 치킨 거리의 최솟값 출력

last = 9999
def calc(hx,hy, bx,by):
    return abs(hx-bx) + abs(hy-by)

home = []
bbq = []
distance = []
comb = [i for i in range(len(bbq))]))
mini = 9999

for i in range(n):
    for j in range(n):
        if lst[i][j] == 1:
            home.append((i,j))
        elif lst[i][j] == 2:
            bbq.append((i,j))

grp = list(combinations(comb,m))
while grp:
    candi = grp.pop()
    total = 0
    for k in range(len(home)):
        home_mini = 9999
        for l in candi:
            bbx, bby = bbq[l]
            home_mini = min(home_mini,calc(bbx,bby, home[k][0],home[k][1]))
        total += home_mini
    mini = min(mini,total)
print(mini)