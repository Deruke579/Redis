package com.example.sentinel;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kelly on 2018/12/23.
 */
public class SentinelTest {

    private JedisSentinelPool jSentinelPool;
    @Before
    public  void setUp(){
        String masterName="mymaster";
        //sentinel地址集合
        Set<String> set=new HashSet<String>();
        set.add("119.23.106.27:26379");
        set.add("119.23.106.27:26380");
        set.add("119.23.106.27:26381");
        GenericObjectPoolConfig gPoolConfig=new GenericObjectPoolConfig();
        gPoolConfig.setMaxIdle(20);
        gPoolConfig.setMaxTotal(100);
        gPoolConfig.setMaxWaitMillis(10000);
        gPoolConfig.setJmxEnabled(true);
        jSentinelPool=new JedisSentinelPool(masterName,set,gPoolConfig);
    }
    @Test
    public void testWriet(){
        Jedis jedis=null;

        for(int i=0;i<10;i++){
            try {

                jedis=jSentinelPool.getResource();

                System.out.println(i);

                String userKey="user"+i;
                jedis.set(userKey, String.valueOf(i));
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            finally{
                if (jedis!=null) {
                    jedis.close();
                }
            }
        }
    }

}
