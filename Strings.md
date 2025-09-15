# Strings 

## 실습

--- 

### INIT

- gradle 초기 import문

![StringsInit.png](ReadMe_images/striongs/StringsInit.png)

- main에서의 사용 (try문은 .cloes() 하려고   스코프를 만든것 마지막에 .close() 하면댐

![Strings_main.png](ReadMe_images/striongs/Strings_main.png)


- 결과

![Strings_main_redult.png](ReadMe_images/striongs/Strings_main_redult.png)
![StringsInterminal.png](ReadMe_images/striongs/StringsInterminal.png)
![Strings_monitor.png](ReadMe_images/striongs/Strings_monitor.png)



---

### MGET 
![MGet.png](ReadMe_images/striongs/MGet.png)
![MGet1.png](ReadMe_images/striongs/MGet1.png)


- 결과 
![MgetInSout.png](ReadMe_images/striongs/MgetInSout.png)
![Mget_1.png](ReadMe_images/striongs/Mget_1.png)

---
### INCR
![Incr_1.png](ReadMe_images/striongs/Incr_1.png)
![INcr_2.png](ReadMe_images/striongs/INcr_2.png)



---
### Pipelining
- 배치 처리를 해서 RTT 성능 저하 방지
- Jedis 에서 제공하는 파이프 라인 객체 사용 

![Pipelne.png](ReadMe_images/striongs/Pipelne.png)
![pipe2.png](ReadMe_images/striongs/pipe2.png)


- 올바른 사용 시

![pipe6.png](ReadMe_images/striongs/pipe6.png)
![pipe7.png](ReadMe_images/striongs/pipe7.png)
