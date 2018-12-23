package com.example.jedis;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * Created by Kelly on 2018/12/22.
 */
public class RedisDemo1 {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("119.23.106.27", 6379);  //指定Redis服务Host和port
        jedis.auth("123456"); //如果Redis服务连接需要密码，制定密码
        String value = jedis.get("name"); //访问Redis服务
        System.out.println(value);

        // 字符串
        jedis.set("key","redis");
        // 列表
        jedis.lpush("list-demo","redis");
        jedis.lpush("list-demo","java");
        jedis.lpush("list-demo","kafka");
        // 获取数据
        List<String> list = jedis.lrange("list-demo",0,3);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("list-demo:"+list.get(i));
        }

        // 获取所有的keys
        Set<String> lists = jedis.keys("*");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("list keys:"+list.get(i));
        }
        jedis.close(); //使用完关闭连接

    }
}
