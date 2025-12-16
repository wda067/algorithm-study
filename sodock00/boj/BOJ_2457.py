import sys
input = sys.stdin.readline

# 매일 꽃이 한 가지 이상 피어 있어야 할 것 
# 정원에 심는 꽃의 수는 최대한 적게

# 풀이
# 3월 1일 이전에 피기시작하는 꽃을 최초로 심음
# 해당 꽃이 

N = int(input())
flower = [list(map(int, input().split())) for _ in range(N)]
flower.sort()

lastEnd = (3, 1)
i = 0
result = 0
while i < N:
    sm, sd, em, ed = flower[i]
    if (sm, sd) <= lastEnd < (em, ed):
        maxEnd = (em, ed)
        while i < N-1:
            nsm, nsd, nem, ned = flower[i+1]
            if lastEnd < (nsm, nsd):
                break
            if maxEnd < (nem, ned):
                maxEnd = (nem, ned)
            i+=1
        result += 1
        lastEnd = maxEnd

        if (11, 30) < lastEnd:
            print(result)
            exit(0)
    i+=1
print(0)
