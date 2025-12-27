from collections import deque
import sys
input = sys.stdin.readline

def find_cycle(start):
    flag = False
    q = deque()
    q.append(start)
    while q:
        now = q.popleft()
        if visited[now]:
            flag = True
        visited[now] = 1
        for node in graph[now]:
            if node == now:
                flag = True
            if not visited[node]:
                q.append(node)
    return flag

T = 1
while True:
    n, m = map(int, input().split())
    if n == 0 and m == 0:
        break
    graph = [[] for _ in range(n + 1)]
    visited = [0] * (n + 1)
    result = 0
    for _ in range(m):
        a, b = map(int, input().split())
        graph[a].append(b)
        graph[b].append(a)
    for i in range(1, n + 1):
        if not visited[i]:
            if not find_cycle(i):
                result += 1
    if result == 0:
        answer = "No trees."
    elif result == 1:
        answer = "There is one tree."
    else:
        answer = "A forest of " + str(result) + " trees."
    print(f"Case {T}: {answer}")
    T += 1
