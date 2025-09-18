# 캐시

---

### cache aside pattern
![img.png](../ReadMe_images/cache/img.png)

```java 
    @GetMapping("/users/{id}/email")
    public String getUserEmail(@PathVariable Long id){
        var userEmailUsersKey = "user:%d:email".formatted(id);

        try{
            Jedis jedis = jedisPool.getResource();
            //1. request to cache
            String userEmail = jedis.get(userEmailUsersKey);
            if(userEmail != null){
                return userEmail;
            }
            //2. else db
            userEmail = userRepository.findById(id).map(User::getEmail).orElse("User not found");
            // 3 cache
            jedis.set(userEmailUsersKey, userEmail);
            // 4. end
            return userEmail;

        }catch (Exception e){
            return e.getMessage();
        }

    }
```

#### *다시보자 TTL 또 확인하자 TTL*
![스크린샷 2025-09-18 오후 5.05.17.png](../ReadMe_images/cache/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202025-09-18%20%EC%98%A4%ED%9B%84%205.05.17.png)


--- 

## 실습
- 각 4회 요청 
  - 캐시 사용 X
![스크린샷 2025-09-18 오후 4.59.36.png](../ReadMe_images/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202025-09-18%20%EC%98%A4%ED%9B%84%204.59.36.png)
    - 디비에 4회 요청 

  - 캐시 사용 
  ![스크린샷 2025-09-18 오후 5.00.10.png](../ReadMe_images/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202025-09-18%20%EC%98%A4%ED%9B%84%205.00.10.png)
    - 디비에 1회 요청 


---
