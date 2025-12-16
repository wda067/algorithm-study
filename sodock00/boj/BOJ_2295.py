import sys
input = sys.stdin.readline

# 세 수의 합이 가장 큰 경우의 수
# 세 수의 합이 있는 경우

N=int(input())
U=set() # 중복 제거 하ㅕ려고
answer=[]

for i in range(N):
    U.add(int(input()))

SUM=set()
for i in U:
    for j in U:
        SUM.add(i+j)

for i in U:
    for j in U:
        if (i-j) in SUM:
            answer.append(i)

answer.sort()
print(answer[-1])