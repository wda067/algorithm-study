import sys
input = sys.stdin.readline

N = int(input())
parent = list(map(int, input().split()))
E = int(input())

# step 1. 부모 노드의 list에 자식 노드를 전부 append함
graph = [[] for _ in range(N)]
for i in range(N):
    p = parent[i]
    if p != -1:
        graph[p].append(i)

# step 2. 삭제하는 노드 제거, 삭제 노드의 자식 list도 전부
result = 0
def dfs(node):
    global result
    if node == E:
        return
    child_cnt = 0
    for child in graph[node]:
        if child != E:
            child_cnt += 1
            dfs(child)
    if child_cnt == 0:
        result+=1

# step 3. 자식 노드의 count가 0인 노드 갯수 리턴
dfs(parent.index(-1))
print(result)