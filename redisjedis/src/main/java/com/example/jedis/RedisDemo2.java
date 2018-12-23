package com.example.jedis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Created by Kelly on 2018/12/22.
 */
public class RedisDemo2 {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("119.23.106.27", 6379);  //指定Redis服务Host和port
        jedis.auth("123456"); //如果Redis服务连接需要密码，制定密码

        System.out.println("清空数据："+jedis.flushDB());
        System.out.println("判断某个键是否存在："+jedis.exists("username"));
        System.out.println("新增<'username','xmr'>的键值对："+jedis.set("username", "xmr"));
        System.out.println(jedis.exists("username"));
        System.out.println("新增<'password','password'>的键值对："+jedis.set("password", "123"));
        System.out.print("系统中所有的键如下：");
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);
        System.out.println("删除键password:"+jedis.del("password"));
        System.out.println("判断键password是否存在："+jedis.exists("password"));
        System.out.println("设置键username的过期时间为5s:"+jedis.expire("username", 8));


        System.out.println("查看键username的剩余生存时间："+jedis.ttl("username"));
        System.out.println("移除键username的生存时间："+jedis.persist("username"));
        System.out.println("查看键username的剩余生存时间："+jedis.ttl("username"));
        System.out.println("查看键username所存储的值的类型："+jedis.type("username"));



        jedis.flushDB();
        System.out.println("===========增加数据===========");
        System.out.println(jedis.set("key1", "value1"));
        System.out.println(jedis.set("key2", "value2"));
        System.out.println(jedis.set("key3", "value3"));
        System.out.println("删除键key2:" + jedis.del("key2"));
        System.out.println("获取键key2:" + jedis.get("key2"));
        System.out.println("修改key1:" + jedis.set("key1", "1"));
        System.out.println("获取key1的值：" + jedis.get("key1"));
        System.out.println("在key3后面加入值：" + jedis.append("key3", "End"));
        System.out.println("key3的值：" + jedis.get("key3"));
        System.out.println("增加多个键值对：" + jedis.mset("key01", "value01", "key02", "value02", "key03", "value03"));
        System.out.println("获取多个键值对：" + jedis.mget("key01", "key02", "key03"));
        System.out.println("获取多个键值对：" + jedis.mget("key01", "key02", "key03", "key04"));
        System.out.println("删除多个键值对：" + jedis.del(new String[]{"key01", "key02"}));
        System.out.println("获取多个键值对：" + jedis.mget("key01", "key02", "key03"));


        jedis.flushDB();
        System.out.println("===========新增键值对,防止覆盖原先值==============");
        System.out.println(jedis.setnx("key1", "value1"));
        System.out.println(jedis.setnx("key2", "value2"));
        System.out.println(jedis.setnx("key2", "value2-new"));
        System.out.println(jedis.get("key1"));
        System.out.println(jedis.get("key2"));

        System.out.println("===========新增键值对并设置有效时间=============");
        System.out.println(jedis.setex("key3", 2, "value3"));
        System.out.println(jedis.get("key3"));

        System.out.println(jedis.get("key3"));

        System.out.println("===========获取原值，更新为新值==========");//GETSET is an atomic set this value and return the old value command.
        System.out.println(jedis.getSet("key2", "key2GetSet"));
        System.out.println(jedis.get("key2"));

        System.out.println("获得key2的值的字串：" + jedis.getrange("key2", 2, 4));

    }


}
