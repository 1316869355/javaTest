package com.up72.redis.jedis;

import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.*;
import java.util.*;


/**
 * <desc> </desc>
 *
 * @version V1.1
 * @date 2013-3-20
 */
public class RedisCacheClient {
//    Log log = LogFactory.getLog(RedisCacheClient.class);
    private JedisPool jedisPool;

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }


    public void set(String key, Serializable value) {
        this.set(key, value, null);
    }

    public void set(String key, Serializable value, Integer expireSeconds) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key.getBytes(), SerializationUtils.serialize(value));
            if (expireSeconds != null) {
                jedis.expire(key.getBytes(), expireSeconds);
            }
        } catch (Exception e) {
            //log.error(e.getMessage());
        } finally {
            close(jedis);
        }
    }

    public void hset(String key, String field, Serializable value) {
        this.hset(key, field, value, null);
    }

    public void hset(String key, String field, Serializable value, Integer expireSeconds) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hset(key.getBytes(), field.getBytes(), SerializationUtils.serialize(value));
            if (expireSeconds != null) {
                jedis.expire(key.getBytes(), expireSeconds);
            }
        } catch (Exception e) {
            //log.error(e.getMessage());
        } finally {
            close(jedis);
        }
    }

    public void hset(String key, String field, Object value, Integer expireSeconds) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hset(key.getBytes(), field.getBytes(), ObjectAndByte.toByteArray(value));
            if (expireSeconds != null) {
                jedis.expire(key.getBytes(), expireSeconds);
            }
        } catch (Exception e) {
            //log.error(e.getMessage());
        } finally {
            close(jedis);
        }
    }

    /**
     * 返回hash中指定存储位置的值
     *
     * @param key
     * @param field 存储的名字
     * @return 存储对应的值
     */
    public <T> T hget(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            byte[] value = jedis.hget(key.getBytes(), field.getBytes());
            if (value != null) {
                return (T) SerializationUtils.deserialize(value);
            }
        } catch (Exception e) {
            ////log.error(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    /**
     * 从Hash中删除对象
     */

    public void hdel(String key, String... field) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hdel(key, field);
        } catch (Exception e) {
            ////log.error(e.getMessage());
        } finally {
            close(jedis);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            byte[] value = jedis.get(key.getBytes());
            if (value != null) {
                return (T) SerializationUtils.deserialize(value);
            }
        } catch (Exception e) {
            ////log.error(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    public void delete(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key.getBytes());
        } catch (Exception e) {
            //log.error(e.getMessage());
        } finally {
            close(jedis);
        }
    }

    public boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key.getBytes());
        } catch (Exception e) {
            //log.error(e.getMessage());
        } finally {
            close(jedis);
        }
        return false;
    }

    public Set<String> keys(String pattern) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.keys(pattern);
        } catch (Exception e) {
            //log.error(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    public Long lLength(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.llen(key);
        } catch (Exception e) {
            //log.error(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> lRangeAll(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            List<T> relist = new ArrayList<T>();
            List<byte[]> list = jedis.lrange(key.getBytes(), 0, -1);
            for (byte[] b : list) {
                if (b != null) {
                    relist.add((T) SerializationUtils.deserialize(b));
                }
            }
            return relist;
        } catch (Exception e) {
            //log.error(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    public Long lAdd(String key, Serializable value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.lpush(key.getBytes(), SerializationUtils.serialize(value));
        } catch (Exception e) {
            //log.error(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    public Map<String, String> hgetAll(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hgetAll(key);
        } catch (Exception e) {
            //log.error(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }


    public Long zadd(String key, double score, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zadd(key, score, value);
        } catch (Exception e) {
            //log.error(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    public Jedis getJedis() {
        try {
            return jedisPool.getResource();
        } catch (Exception e) {
            //log.error(e.getMessage());
        }
        return null;
    }

    public void close(Jedis jedis) {
        if (null != jedis) {
            jedis.close();
            jedis = null;
        }
    }

    public static void main(String[] args) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(5);
        config.setTestOnBorrow(false);
//        JedisPool jedisPool = new JedisPool(config, "192.168.1.144", 6379);
        JedisPool jedisPool = new JedisPool(config, "101.201.50.149", 19000, 10000, "hs", 0);
        Jedis jedis = jedisPool.getResource();
//
//        jedis.zadd("all", 2, "b");
//        jedis.zadd("all", 2, "d");
//        jedis.zadd("all", 2, "e");
//        jedis.zadd("all", 2, "1_f");
//        jedis.zadd("all", 2, "2_a");
//        jedis.zadd("all", 2, "c");
//        jedis.zadd("all", 2, "g");
//        Set<String> set = jedis.zrange("all",0,-1);
//        Iterator<String> it = set.iterator();
//        while (it.hasNext()) {
//            String s = it.next();
//            System.out.println(jedis.zscore("all",s) + " "+s);
//
//        }
//        System.out.println(jedis.zrangeByScore("all", 3,6));
//        Map<String, String> o = jedis.hgetAll("hiseeBattery_" + 1090);;//.hget("hiseeSpeed_514","1_"+System.currentTimeMillis());
        Map<String, String> o = jedis.hgetAll("hiseeHR_" + 4);
//        System.out.println(jedis.get("hiseeBattery_" + 5));
//        Map<String, String> o = jedis.hgetAll("hiseePos_"+4);
//		jedis.hset("hiseePer", "123123", "123123_12313_12313");
//		Map<String,String> o = jedis.hgetAll("hiseePer");
        Map<String, Map<Long, String>> dataMap = new HashMap<String, Map<Long, String>>();
        if (null != o && !o.isEmpty()) {
            Iterator<String> iterator = o.keySet().iterator();
            while (iterator.hasNext()) {
                String playerTime = iterator.next();
                String[] pt = playerTime.split("_");
                Long time = Long.parseLong(pt[1]);
                String playerId = pt[0];
                Map<Long, String> timePoint = dataMap.get(playerId);
                if (null == timePoint) {
                    timePoint = new HashMap<Long, String>();
                    dataMap.put(playerId, timePoint);
                }
                timePoint.put(time, o.get(playerTime));
            }
        }
//        System.out.println(s);
        for (String playerId : dataMap.keySet()){
            Map<Long, String> map = dataMap.get(playerId);
            System.out.println(playerId);
            Long[] time = map.keySet().toArray(new Long[map.keySet().size()]);
//            if(playerId.equals("1136")){
                quickSort(time, 0, time.length - 1);
                for (Long t:time){
//                    System.out.println(playerId+"_"+DateUtils.getDateTime(t)+" ==== "+map.get(t));
                }
//            }
        }
    }

    /**
     * 按时间排序
     */
    public static void quickSort(Long[] times, int start, int end) {
        if (start < end) {
            long base = times[start]; // 选定的基准值（第一个数值作为基准值）
            long temp; // 记录临时中间值
            int i = start, j = end;
            do {
                while ((times[i] < base) && (i < end))
                    i++;
                while ((times[j] > base) && (j > start))
                    j--;
                if (i <= j) {
                    temp = times[i];
                    times[i] = times[j];
                    times[j] = temp;
                    i++;
                    j--;
                }
            } while (i <= j);
            if (start < j)
                quickSort(times, start, j);
            if (end > i)
                quickSort(times, i, end);
        }
    }
}

class ObjectAndByte {

    /**
     * 对象转数组
     *
     * @param obj
     * @return
     */
    public synchronized static byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    /**
     * 数组转对象
     *
     * @param bytes
     * @return
     */
    public static Object toObject(byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }
}
