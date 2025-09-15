package com.teambind.lists;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

public class ListsApplication {

    public static void main(String[] args) {
        try {
            JedisPool pool = new JedisPool("127.0.0.1", 6379);

            try (Jedis jedis = pool.getResource()) {

                //list
                // stack
//                jedis.rpush("queueee", "aaaa", "aaaa", "bbbb");
//                List<String> stack1 =  jedis.lrange("queuee", 0, -1);
////                stack1.forEach(System.out::println);
//                System.out.println(jedis.rpop("queueee"));
//                System.out.println(jedis.rpop("queueee"));
//                System.out.println(jedis.rpop("queueee"));
                // queue
                jedis.rpush("que", "aaaa", "aaaa", "bbbb");
                List<String> que =  jedis.lrange("que", 0, -1);
                que.forEach(System.out::println);
                System.out.println("---------------------------------");
//                System.out.println(jedis.lpop("que"));
//                System.out.println(jedis.lpop("que"));
//                System.out.println(jedis.lpop("que"));
                // block pop
                System.out.println(jedis.blpop(10, "que"));
                System.out.println(jedis.blpop(10, "que"));
                System.out.println(jedis.blpop(10, "que"));
                System.out.println(jedis.blpop(2 , "que"));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
