# Spring Cache

---
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


## Redis Repository
- Redis Hash -> CrudRepository만 상속받을 수 있음.

---

## 실습 

-이런식으로 사용도 가능
![스크린샷 2025-09-18 오후 5.42.42.png](../ReadMe_images/cache/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202025-09-18%20%EC%98%A4%ED%9B%84%205.42.42.png)

- ver2
![스크린샷 2025-09-18 오후 5.45.20.png](../ReadMe_images/cache/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202025-09-18%20%EC%98%A4%ED%9B%84%205.45.20.png)
