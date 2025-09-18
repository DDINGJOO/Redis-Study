# Spring Cache

---
- Redis Client
	- Jedis
	- Lettuce : Default
		- 이래서 명령어 찾기 힘들었나봄;;;

- Redis Template
	- Spring Boot 에서 제공하는 Redis Client
- 
  - 추상화, 연결관리, 직렬/역직렬 알아서 해준대
      - -> 걍써!
      - opsForValue 이런거 opsForList , opsForSet , opsForHash , opsForZSet , opsForGeo

- Redis Template -> Lettuce -> Redis
	- 이런식으로 작동


- StringRedisTemplate -> 벨류에 관해서 항상 자바의 String 객체로 받겠다 라는 말

---


## Redis Repository
- Redis Hash -> CrudRepository만 상속받을 수 있음.

---

## 실습 

-이런식으로 사용도 가능
![스크린샷 2025-09-18 오후 5.42.42.png](../ReadMe_images/cache/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202025-09-18%20%EC%98%A4%ED%9B%84%205.42.42.png)

- ver2
![스크린샷 2025-09-18 오후 5.45.20.png](../ReadMe_images/cache/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202025-09-18%20%EC%98%A4%ED%9B%84%205.45.20.png)


- service 에서 사용
![스크린샷 2025-09-18 오후 5.47.50.png](../ReadMe_images/cache/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202025-09-18%20%EC%98%A4%ED%9B%84%205.47.50.png)



- 결과 : 4회요청 
![스크린샷 2025-09-18 오후 5.49.20.png](../ReadMe_images/cache/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202025-09-18%20%EC%98%A4%ED%9B%84%205.49.20.png)
  - 첫 요청 -> db질의 이후 
  - 2~4 -> 3회 캐시 히트
  - 다시보자 ttl 한번더 보자 ttl





---

##  General Redis Template

![스크린샷 2025-09-18 오후 5.58.33.png](../ReadMe_images/cache/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202025-09-18%20%EC%98%A4%ED%9B%84%205.58.33.png)

![스크린샷 2025-09-18 오후 6.02.43.png](../ReadMe_images/cache/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202025-09-18%20%EC%98%A4%ED%9B%84%206.02.43.png)



### 결과 
Redis Monitor에 객체 정보까지 저장해서, 이를 통해 역질렬화 하는것.
![스크린샷 2025-09-18 오후 6.04.13.png](../ReadMe_images/cache/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202025-09-18%20%EC%98%A4%ED%9B%84%206.04.13.png)



---

## Redis Hash Annotation


![스크린샷 2025-09-18 오후 6.12.16.png](../ReadMe_images/cache/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202025-09-18%20%EC%98%A4%ED%9B%84%206.12.16.png)

![스크린샷 2025-09-18 오후 6.12.59.png](../ReadMe_images/cache/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202025-09-18%20%EC%98%A4%ED%9B%84%206.12.59.png)

![스크린샷 2025-09-18 오후 6.12.33.png](../ReadMe_images/cache/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202025-09-18%20%EC%98%A4%ED%9B%84%206.12.33.png)

이런식으로 사용



----

## Spring Boot Cache

- 까먹지 말자 EnableCaching
- 캐시 사용 @Cacheable("str") , @CacheEvict("str")



---
## 실습
![스크린샷 2025-09-18 오후 6.23.30.png](../ReadMe_images/cache/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202025-09-18%20%EC%98%A4%ED%9B%84%206.23.30.png)
![스크린샷 2025-09-18 오후 6.39.30.png](../ReadMe_images/cache/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202025-09-18%20%EC%98%A4%ED%9B%84%206.39.30.png)
![스크린샷 2025-09-18 오후 6.39.08.png](../ReadMe_images/cache/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202025-09-18%20%EC%98%A4%ED%9B%84%206.39.08.png)
![스크린샷 2025-09-18 오후 6.39.46.png](../ReadMe_images/cache/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202025-09-18%20%EC%98%A4%ED%9B%84%206.39.46.png)


- 결과는 똑같다!

---


## 성능 테스트 
- vegeta
  https://github.com/tsenart/vegeta
	- 대량 트래픽 처리 테스트
