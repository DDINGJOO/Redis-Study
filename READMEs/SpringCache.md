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
