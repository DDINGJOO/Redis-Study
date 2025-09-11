## InMemory Database
- redis : 
  - Remote Dictionary Server , in-memory, ket/sub
  

  - inmemory database
    - 빠른 응답속도, but 가격이 비쌈
    - 휘발성 데이터임
  - Persistent on Disk // 디스크에 백업
    - RDB(Snapshot) (기본 설정)
      - 특정 시점의 데이터베이스를 디스크에 저장 
      - 스냅샷 과정 자체가 포크 기반 -> 메모리 사용량 급증
    
    - AOF(Append Only File)
      - 데이터 쓰기 작업 명령어를 백업
      - 명령어 만을 백업 하니까 -> 복구의 용의
      - disk I/O 가 자주 일어 나니까 성능 저하 
    
	- Data types 
      - 다양한 타입 지원
      - String, List, Set, Hash, Sorted Set, HyperLogLog, BitMap
      
    - Single Thread
      - 싱글쓰레드 기반 프로세스 
      - 락을 사용하지 않아도 일관성 보장 가능
      - ... IO 가 급증하면..? inMemory DB 라서 빠른 속도로 커버(초당 10만건) 
      - Redis 공홈에서도 알고리즘 복잡도 표서 O(n) 도입시 좀 고려 해야됨.
    
	- Multi Thread
      - Network I/O 에 관해서는 멀티 쓰래드 사용  but  작업은 단일 쓰래드로 IO만 멀티

- 활용 사례 
  - cache server
  - session server 
  - message queue (Pub/Sub) // message broker로의 작동도 가능 
  - Geospatial index // 빠른 응답 속도 -> 내 주면 gps 어쩌구 저쩌구 그거
  - leader board
    



---


### REDIS - cli 
- 기본적인 데이터 삽입/조회
  - set key value
  - get key
  - del key
  - keys *

- redis-cli monitor
	- monitor : 명령어 기록 모니터 
    - ![redis-cli-monitor.png](images/redis-cli-monitor.png)
    - slowlog get : 10ms 이상 걸린 명령어 보여줌
      - format 
        - id
        - 실행시간
        - 수행시간
        - 명령
        - client ip/port
        - client name 

	- info
      - 버전, 메모리 ㅈ정보, 세션 정보 등등... 
      - ![redis-cli-info.png](images/redis-cli-info.png)
    - docker exec -it [constainer name] redis-cli --stat
      - 메모리 사용량, 사용률, 블락 횟수 등등 정보 초당 업데이트해서 보여줌
    - select [db index]
      - db index : 0 ~ 15 // 논리적인 데이터 베이스 파티션 


- redis insight 
  - GOAT -> GUI
