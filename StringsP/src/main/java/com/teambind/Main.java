package com.teambind;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {

        try{
            JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);

            try{
                Jedis jedis = jedisPool.getResource();
                jedis.set("users:100:email", "ddingsha9@teambind.co.kr");
                jedis.set("users:100:name", "dding joo ");
                jedis.set("users:200:age", "20");
                String value = jedis.get("users:100:email");
                System.out.println(value);

                List<String> val  = jedis.mget("users:100:email", "users:100:name", "users:200:age");
                System.out.println(val.toString());
            }catch(Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
