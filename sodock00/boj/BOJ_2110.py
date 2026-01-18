import sys
input = sys.stdin.readline

# 입력
N, C = map(int, input().split())
houses = [int(input()) for _ in range(N)]
houses.sort()

# step 1. 가능한 거리 범위
left = 1
right = houses[-1] - houses[0]
answer = 0

# step 2. 거리 d로 설치 가능한지 체크 함수
def can_install(d):
    count = 1
    last = houses[0]
    for h in houses[1:]:
        if h - last >= d:
            count += 1
            last = h
            if count >= C:
                return True
    return False

# step 3. 이분 탐색
while left <= right:
    mid = (left + right) // 2
    if can_install(mid):
        answer = mid
        left = mid + 1
    else:
        right = mid - 1

# step 4. 결과 출력
print(answer)
