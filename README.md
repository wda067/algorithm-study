# 💻 코딩 테스트 스터디
## 📅 2025년 12월 5주차 문제

### 📌 월요일  
- [[BOJ] 2240번 자두나무](https://www.acmicpc.net/problem/2240)  

### 📌 화요일  
- [[BOJ] 11559번 Puyo Puyo](https://www.acmicpc.net/problem/11559)  

### 📌 수요일  
- [[BOJ] 15681번 트리와 쿼리](https://www.acmicpc.net/problem/15681)  

### 📌 목요일  
- [[BOJ] 1715번 카드 정렬하기](https://www.acmicpc.net/problem/1715)  

### 📌 금요일  
- [[BOJ] 1504번 특정한 최단 경로](https://www.acmicpc.net/problem/1504)  


## ✅ 스터디 진행 방법
1. 현재 저장소를 자신의 깃허브 계정으로 **포크**한다.
2. 포크한 저장소를 로컬 환경에 **클론**한다.
```
git clone https://github.com/{github_id}/algorithm-study.git
```
3. 자신의 깃허브 ID로 **브랜치를 생성**한다.
```
git checkout -b {github_id}
```
4. 본인의 폴더에 문제 풀이 코드를 작성하고 커밋 메시지와 함께 **커밋**한다.
```
git add .
git commit -m "feat: [BOJ] 1234번"
```
5. 작업이 완료되면, 포크한 개인 저장소의 **해당 브랜치로 푸시**한다.
```
git push origin {github_id}
```
6. `main` 브랜치를 대상으로 포크한 저장소의 작업 브랜치에서 **Pull Request**를 생성한다.
7. 관리자가 PR을 확인한 뒤 `main` 브랜치에 **머지**한다.

## ✅ 패키지 구조
- **형식**: `GitHub ID/플랫폼/플랫폼_문제 번호`
  - **예시**: `wda067/boj/BOJ_1234.java`

## ✅ 커밋 컨벤션
- **형식**: `타입: [플랫폼] 문제 번호`
  - **예시**: `feat: [BOJ] 1234번`
- **플랫폼 코드**
  - **BOJ**: 백준
  - **PGS**: 프로그래머스
- **타입**
  - **feat**: 새로운 문제 풀이 추가
  - **fix**: 기존 문제 풀이의 오류 수정
  - **refactor**: 코드 리팩토링
  - **chore**: 기타 변경 사항

## ✅ PR 컨벤션
- **제목**: `타입: [플랫폼] 문제 번호`
  - **예시**: `feat: [BOJ] 1234번`
- **내용**: 문제 접근 방식, 풀이 과정 등 자유롭게 작성
