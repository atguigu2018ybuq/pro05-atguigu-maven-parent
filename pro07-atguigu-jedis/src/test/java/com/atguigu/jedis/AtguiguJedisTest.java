package com.atguigu.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Protocol;

import java.util.List;

public class AtguiguJedisTest {

    private String host = "192.168.198.100";
    private int port = Protocol.DEFAULT_PORT;

    @Test
    public void testJedisPool() {
        // 1.创建连接池对象
        JedisPool jedisPool = new JedisPool(host, port);

        // 2.获取Jedis对象
        Jedis resource = jedisPool.getResource();

        // 3.执行具体操作
        String ping = resource.ping();
        System.out.println(ping);

        // 4.关闭连接
        resource.close();
    }

    @Test
    public void testJedis() {
        // 1.创建Jedis对象
        Jedis jedis = new Jedis(host, port);

        // 2.调用和Redis命令同名的方法执行操作
        String ping = jedis.ping();
        System.out.println(ping);

        Long result = jedis.lpush("fruit:list", "apple", "banana");
        System.out.println("result="+result);

        List<String> fruitList = jedis.lrange("fruit:list", 0, -1);
        for (String fruit : fruitList) {
            System.out.println("fruit="+fruit);
        }

        // 3.关闭连接
        jedis.close();
    }

}
