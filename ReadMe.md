## InMemory Database
- 실습 환경    docker container 
- docker exec -it redis redis-cli
- 
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
    ![redis-cli-monitor.png](ReadMe_images/striongs/redis-cli-monitor.png)
    - redis - banchmark(성능 테스트) 
  ![img.png](ReadMe_images/striongs/img.png)
  ![img_1.png](ReadMe_images/striongs/img_1.png)
      - 초당 92만개 정도 처리 가능
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
      - ![redis-cli-info.png](ReadMe_images/striongs/redis-cli-info.png)
    - docker exec -it [constainer name] redis-cli --stat
      - 메모리 사용량, 사용률, 블락 횟수 등등 정보 초당 업데이트해서 보여줌
    - select [db index]
      - db index : 0 ~ 15 // 논리적인 데이터 베이스 파티션 


- redis insight 
  - GOAT -> GUI



### DATA Types

- Key/ Value  가 기본 구성 
  - Key : binary and Text string
    - 숫자 , 문자 , 특문 지원 512MB 까지 설정 가능 
  - Value : binary and Text string
    - Strings Lists , Sets, Sorted Sets, Hashes, Bitmaps, Geospatial... 등등 올 수 있음 
    - 


---



### Strings Type
- 가장 기본적인   Data type ,
	- 기본적으로 바이너리 저장, 512MB까지 저장 가능
      - 이미지, 직렬화된 데이터(json) 등등 저장 가능 
    - 증가 감소에 대한 원자적 연산가능
      - increase/decrease 


- command 
  - SET ,SETNX (O(N))
    - 벨류가 있는경우 -> SET
    - 벨류가 없는 경우 -> SETNX (NX -> If NOT EXISTS) 
  
  - GET , MGET (O(N))
    - GET, MGET(Multiple GET)
 
  - INCR ,DECR
    - 증가/감소 원자적 계산
  
  - DEL [key] / UNLINK [key]
    - 키 데이터 삭제 
    - DEL : 동기적 삭제 : 삭제 완료까지 버퍼링 
    - UNLINK : 비동기적 삭제  : 삭제 요청후 알빠누 빠이 < 리스트 같은 데이터가 큰 객체 삭제할때 사용 
    
  - *TTL ,  EXPIRE*
    - TTL [key] : 남은 만료시간 체크 
    - EXPIRE [KEY] [SECOND] : 만료시간 설정 (TTL 설정)

  - MEMORY USAGE 
    - 메모리 적재 량 확인 
    
![CommandString_1.png](ReadMe_images/striongs/CommandString_1.png)
- users:1:email > 이런식으로, 여러 정보를 키에 같이 저장하는 경우가 많음
- 문법적 강제는 아닌 관행
- MGET < - 여러 키값을 한번에 가져와야할때 혹은 배치 작업을 할때 성능상 이득을 취할 수있음.


- 원자적 증/감 실습 코드 
![CommandString_2.png](ReadMe_images/striongs/CommandString_2.png)
- 원자적 증가가 필요한 이유 : 
  - 여러 어플리케이션이 count 값을 하나 증가 시킬때 GET ,SET 만 있다면, 
  - GET 호출 시점이랑 SET 호출 시점의 차이 때문에 싱글 쓰레드로 운영되는 레디스에서 덮어쓰기를 할때 의도하지않은 작업이 일어 날 수 있음.
    - ex. 조회수 증가 로직을 설정해야할때
      - App1:GET counter  (counter = 1)
      - App2 : GET counter (counter = 1)
      - App2 : SET counter 2 (counter = 2)
      - App1 : SET counter 2 (counter = 2)
        - Redis 저장 기댓값 -> 3 , 실제 -> 2

- TTL 관련 실습 코드 

![CommandTTL_2.png](ReadMe_images/striongs/CommandTTL_2.png)

- TTL 설정 X -> -1 , 파기된 KEY(OR 없는 키) -> -2

- MEMORY USAGE
![MEMORY_USAGE.png](ReadMe_images/striongs/MEMORY_USAGE.png)



---

### LISTS
- Linked List 구조로 데이터 저장
- Commands
  - LPUSH, RPUSH
  - LPOP, RPOP
  - LLEN 
  - LRANGE
    - 리스트 인덱스 값으로 조회
    - 0 -1 : 전체 조회

![Lpush.png](ReadMe_images/striongs/Lpush.png)
![전체조회.png](ReadMe_images/%EC%A0%84%EC%B2%B4%EC%A1%B0%ED%9A%8C.png)



---

### SETS
- Unordered Collection, 유니크 값 보장 , 순서 보장 X
- Commands
  - SADD, SREM
  - SISMEMBER  
    - Set Is Member : 맴버 있는지 조회
  - SMEMBERS
    - 전체 맴버 조회
  - SCARD
    - SETS 에 있는 데이터 수 조회
  - SINTER(O(MN)) // 나머지는 기본적으로 O(N)
    - SINTER : 두개의 SETS에서 공통 데이터 조회

![Sets.png](ReadMe_images/striongs/Sets.png)


--- 
### SORTED SETS
- ordered collection , 유니크 값 보장, 특정 비교 방식(커스텀 정렬) 가능 
  - leader board, rate limit 
  - leader board, rate limit 에서 사용

- Commands
  - ZADD , ZREM
    - zadd [key] [score] [value]
    - zrem [key] [value]
  - ZRANGE
  - ZCARD
  - ZRANK / ZREVERANK
    - 오름차순, 내림차순
  - ZINCRBY
    - 정렬 점수 원자적 증가
    
![zrank.png](ReadMe_images/striongs/zrank.png)
![zset.png](ReadMe_images/striongs/zset.png)


---

### HASHES
- 자바의 HashMap  key ->  { (field ,value) , (field ,value) }
- Commands
  - HSET /, HGET , HMGET , HGETALL
    - hset [key] [field] [value] [field] [value] ...
    - hget [key] [field]
    - hgetall [key]
  - HDEL
  - HINCRBY
![Hasj.png](ReadMe_images/striongs/Hasj.png)



---

### Geospatial
- 위도, 경도 위치 저장 
- Commands
  - GEOADD O(n)
    - geoadd [key] [longitude] [latitude] [member] [longitude] [latitude] [member] ...
  - GEODIST
    - geodist [key] [member1] [member2]
  - GEOHASH
    - geohash [key] [member1] [member2]
  - GEOPOS
    - geopos [key] [member1] [member2]
  - GEOSEARCH // O(n+logm)
    - geosearch [key] [longitude] [latitude] [radius] [unit] [withcoord] [withdist] [withhash] [count] [sort] [store] [storedist] [storehash] 




---

### BITMAP 
- bit array  // 0과 1 값만 으로 이루어진 비트배열 
  - 메모리를 적게 사용하여 대량의 데이터 저장에 유리 SET -> BITMAP 검토도 메모리 리소스의 관리의 옵션중하나
- Commands
  - SETBIT , GETBIT , BITCOUNT
    - setbit [key] [offset] [value]
    - getbit [key] [offset]
    - bitcount [key] [start] [end]
  - BITOP
    - bitop [operation] [destination] [source1] [source2] ...

---
### Redis transaction

- 명령들을 큐에 모아두고, 트랜잭셩 완료를 전달하면, 일련의 명령을 한번에 처리하는 것 말함
- single thread 1step (isolate)

![img_2.png](ReadMe_images/Bitmap/img_2.png)
- 트랜잭션 종료전에 요청시 Nil 값 반환
- 중간에 잘못된 명령어 있을시 전부 롤백 (근데.. 실글사이드 쓰래드 단위이전에 가능..?)
- 인자값이 잘못된경우는 그냥 빼고 처리

- Transactional commands
  - MULTI :  시작지점 
  - EXEC : 명령어 집합 실행
  - DISCARD : 트랜잭션 취소
  - WATCH
    - watch [key] [key] ...
    - unwatch
      - 동시의 같은 키를 수정하느 상황일시 트랜잭션 취소

      - ![img_3.png](ReadMe_images/Bitmap/img_3.png)
      - ![img_4.png](ReadMe_images/Bitmap/img_4.png)
---
### 레디스 성능 짤 
![img.png](ReadMe_images/img.png)
- 파이프 라인 했을 경우 , 200000만건 입력시 2초 이내로 소요(빌드 통신 뭐 등등 시간 다 합쳐서)
- 파이프 라인을 해봅시가...
![img.png](ReadMe_images/Bitmap/12312341₩.png)



---

### 레디스 성능저하 명령어 
- 대부분 O(1) 의 복잡도를 갖고 있지만 
- O(N)의 복잡도는 생각을 좀 하고 씁시다.
- O(N)
- keys 
  - 레디스의 존재하는 키를 조회 ,모든 키, 특정키 조회 가능

- Data type
  - ListSERT (특정 인덱스에 값 삽입)
  - Hkeys, Hgetall (해쉬 관련)
  - smemers (Sets관련)


- keys -> scan 으로 적당히 해결
  - scan [cursor] [match] [count]
    - 페이징 기반 -> 커서기반 탐색  정도 성능 향상 있음





## cache 
- 변경 적고 조회 많은 애들 히또!

- cache aside pattern
  - 흔히 말하는 그거 ㅇㅇ
  
- cache write-back pattern
  - 캐시에 버퍼를 쌓고 한번에 배치작업 
  - I/O의 리소스 이득 but  유실 가능성 유의
  
- local cache, 분산 캐시 패턴
  - 서비스 간 공통 캐시 영역



---

## Spring Boot Cache

- Redis Client
  - Jedis
  - Lettuce : Default 
    - 이래서 명령어 찾기 힘들었나봄;;; 

- Redis Template 
  - Spring Boot 에서 제공하는 Redis Client 
  - 추상화, 연결관리, 직렬/역직렬 알아서 해준대 
    - -> 걍써!
    - opsForValue 이런거 opsForList , opsForSet , opsForHash , opsForZSet , opsForGeo

- Redis Template -> Lettuce -> Redis 
  - 이런식으로 작동 


- StringRedisTemplate -> 벨류에 관해서 항상 자바의 String 객체로 받겠다 라는 말

---
