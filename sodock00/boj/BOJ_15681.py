# 15681 
import sys
sys.setrecursionlimit(1000000000)
input = sys.stdin.readline

N,R,Q=map(int,input().split(' '))

m=[[]for _ in range(N+1)]
visit=[-1 for _ in range(N+1)]

for _ in range(N-1):
    a,b=map(int,input())
    m[a].append(b)
    m[b].append(a)

def dfs(now):
    global visit
    visit[now]=1
    for i in m[now]:
        if visit[i]==-1: 
            visit[now]+=dfs(i) 
    return visit[now] 
dfs(R)
for _ in range(Q):
    get=int(input())
    print(visit[get])