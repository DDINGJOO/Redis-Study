package com.teambind;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.stream.IntStream;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {
        try{
            var jedisPool = new JedisPool("127.0.0.1", 6379);
            try{
                var jedis = jedisPool.getResource();
//                jedis.setbit("request-somepage-2025-09-25", 100, true); // url , userId, value (true =1)
//                jedis.setbit("request-somepage-2025-09-25", 200, true); // url , userId, value (true =1)
//                jedis.setbit("request-somepage-2025-09-25", 300, true); // url , userId, value (true =1)
//
//                // 금일 somepage 방문 유저 합계
//                System.out.println(jedis.bitcount("request-somepage-2025-09-25"));
//
//
//                System.out.println(jedis.getbit("request-somepage-2025-09-25", 100));
//                System.out.println(jedis.getbit("request-somepage-2025-09-25", 200));
//                System.out.println(jedis.getbit("request-somepage-2025-09-25", 300));


                System.out.println("--------------bitmap vs set----------------");
                Pipeline pipeline = jedis.pipelined();
                IntStream.range(0, 1000000).forEach(i -> {
                    jedis.sadd("request-somepage-set-2025-09-22", String.valueOf(i), "1");
                    jedis.setbit("request-somepage--bit-2025-09-22", i, true);
//                    if(i % 10000 == 0) pipeline.sync();

                });









            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
