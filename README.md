# 미니프로젝트 - 랜덤 미로 생성

## 구현 순서
- 랜덤 미로 생성
- JFrame을 활용해 미로 표현
- Observer Pattern을 활용해 플레이어의 입력 처리
- 추가 구현
  * Thread 이용해서 시간 check
  * Count 제한을 두고 초과시 실패
  * 단계별 미로 정복(예정)
  * 파일 입출력으로 Ranking System 구현(예정)
  * Player의 시야 제한(예정)

### 랜덤 미로 생성
- 출발지에 무조건 나갈 수 있는 도착지 하나 생성
- DFS 활용 -> 재귀함수 사용
- BFS 활용 -> 큐 사용
- 출발점 : `0,1`
- 도착점 : `map.length-1, map[0].length-2;`

### JFrame을 활용해 미로 표현
- int[][] map에 들어있는 데이터를 활용하여 0은 벽, 1은 경로로 판단
- 벽은 Black, 경로는 White로 처리
- 도착지는 Red, 현재 플레이어의 위치는 Blue, 아이템은 Yellow로 처리
- 방향키를 통해 플레이어 위치 실시간 이동(이동할 때마다 이동 횟수 +1)
- 지도 오른쪽에 Info Frame을 통해 현재 이동횟수와 Limit 이동횟수를 알 수 있다.

### Observer Pattern을 활용해 플레이어의 입력 처리
- 현재 위치에서 상하좌우 데이터를 입력한다.
- 입력된 데이터는 Observer에 의하여 감지가 된다.
- 감지된 데이터(상하좌우 이동)을 반영한 데이터를 플레이어의 위치에 저장한다.
- 갱신된 플레이어의 위치를 JPanel에 update한다.

### 아이템 활용
- 타이머 아이템
  * 각 스테이지마다 막다른 길에 랜덤하게 아이템을 생성
  * 아이템 하나당 맵의 `(가로 길이+세로 길이)/2` 만큼의 횟수를 증가
- 망치 아이템(예정)
  * 망치 아이템을 획득한 상태에서 벽으로 이동하려 할 때, 상태창을 통해 벽을 뚫을 것인지 확인
  * Ok를 누르면 벽을 뚫고 해당 위치로 이동하며 망치의 갯수가 하나 차감
