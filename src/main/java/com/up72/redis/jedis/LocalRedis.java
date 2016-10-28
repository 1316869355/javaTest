package com.up72.redis.jedis;

import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2016/8/26.
 */
public class LocalRedis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接OK");
//        jedis.set("first", "这是我第一次用redis存数据ps:用的jedis");
        System.out.println(get(jedis,"first"));
    }

    public static String get(Jedis jedis, String key) {
        return jedis.get(key);
    }
}
