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
                jedis.rpush("queuee", "aaaa", "aaaa", "bbbb");
                List<String> stack1 =  jedis.lrange("queuee", 0, -1);
                stack1.forEach(System.out::println);
                // queue
                // block pop
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
