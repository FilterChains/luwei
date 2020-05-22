package com.luwei.supermarket.util.redisconfig;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * @projectName： springbootdubbo
 * @packageName: com.dubbo.common.util
 * @auther: luwei
 * @description: 封装redisTemplate
 * @date: 2020/1/24 22:34
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * @Title: expire
     * @Description: 指定缓存失效时间
     * @Param: [key, time] key ->键,time ->时间(秒)  参数
     * @Return: boolean   返回类型
     * @Date: 2020/1/27 11:56
     */
    public boolean setExpire(String key, long time) {
        Boolean flag = false;
        try {
            if (time > 0) {
                flag = redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * @Title: getExpire
     * @Description: 根据key 获取过期时间
     * @Param: [key] ->键 不能为null
     * @Return: long ->时间(秒) 返回0代表为永久有效
     * @Date: 2020/1/27 11:58
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * @Title: hasKey
     * @Description: 判断hashKey是否存在
     * @Param: [key]  key ->键
     * @Return: boolean ,true ->存在,false->不存在
     * @Date: 2020/1/27 11:59
     */
    public boolean validateHasKey(String key) {
        Boolean flag = false;
        try {
            flag = redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * @Title: deleteKeys
     * @Description: 删除缓存, 根据redisKey 删除相对数据
     * @Param: [key] key ->可以传一个值 或多个
     * @Return: void   返回类型
     * @Date: 2020/1/27 12:06
     * @Alert: String... 表示一个String 数组,即多个参数
     */
    @SuppressWarnings("unchecked")
    public void deleteKeys(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * @Title: vagueFindRediskeys
     * @Description: 模糊查询获取key值
     * @Param: [pattern]   参数
     * @Return: java.util.Set   返回类型
     * @Date: 2020/1/27 14:29
     */
    public Set vagueFindRediskeys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * @Title: redisConvertAndSend
     * @Description: 使用Redis的消息队列
     * @Param: [channel, message]  message ->消息内容
     * @Return: void   返回类型
     * @Date: 2020/1/27 14:30
     */
    public void redisConvertAndSend(String channel, Object message) {
        redisTemplate.convertAndSend(channel, message);
    }

    /*===============================================================*/
    /*============================String=============================*/
    /*===============================================================*/

    /**
     * @Title: get
     * @Description: 普通缓存获取
     * @Param: [key] key ->键
     * @Return: java.lang.Object   返回类型
     * @Date: 2020/1/27 12:13
     */
    public Object getRedisString(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * @Title: setRedisString
     * @Description: 普通缓存放入
     * @Param: [key, value]  key ->键, value ->值
     * @Return: boolean   true ->成功, false ->失败
     * @Date: 2020/1/27 12:16
     */
    public boolean setRedisString(String key, Object value) {
        Boolean temp = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            temp = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * @Title: setRedisStringExpire
     * @Description: 普通缓存放入并设置时间
     * @Param: [key, value, time] key ->键,value ->值,time ->时间(秒) time要大于0如果time小于等于0 将设置无限期
     * @Return: boolean  true ->成功, false ->失败
     * @Date: 2020/1/27 12:17
     */
    public boolean setRedisStringExpire(String key, Object value, long time) {
        Boolean temp = false;
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                setRedisString(key, value);
            }
            temp = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * @Title: setKeyIncrement 并返回新增key
     * @Description: 递增
     * @Param: [key, delta] key ->键, delta ->要增加几(大于0)
     * @Return: long   返回类型
     * @Date: 2020/1/27 12:23
     */
    public long setKeyIncrement(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * @Title: setKeyDecrease
     * @Description: 递减
     * @Param: [key, delta] key ->键,delta ->要减少几(小于0)
     * @Return: long   返回类型
     * @Date: 2020/1/27 12:25
     */
    public long setKeyDecrease(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    /*====================================================================*/
    /*================================Map=================================*/
    /*====================================================================*/

    /**
     * @Title: getRedisMapAssignValue
     * @Description: 根据键, 值。获取指定redis对象
     * @Param: [key, item] key ->键 不能为null,item ->项 不能为null,即:Map的Key
     * @Return: java.lang.Object   返回类型
     * @Date: 2020/1/27 12:35
     */
    public Object getRedisMapAssignValue(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * @Title: getRedisMapValue
     * @Description: 获取hashKey对应的所有键值
     * @Param: [key]  key ->键
     * @Return: java.util.Map<java.lang.Object   ,   java.lang.Object>   返回类型
     * @Date: 2020/1/27 12:33
     */
    public Map<?, ?> getRedisMapValue(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * @Title: setRedisMap
     * @Description: 设置Map对象至redis
     * @Param: [key, map] key ->键,map ->对应多个键值
     * @Return: boolean  true ->成功,false ->失败
     * @Date: 2020/1/27 12:29
     */
    public boolean setRedisMap(String key, Map<String, Object> map) {
        Boolean temp = false;
        try {
            redisTemplate.opsForHash().putAll(key, map);
            temp = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * @Title: setRedisMapExpire
     * @Description: 设置Map对象至redis, 并设置超时时间
     * @Param: [key, map, time] key ->键,map ->对应多个键值,time ->时间(秒)
     * @Return: boolean true ->成功,false ->失败
     * @Date: 2020/1/27 12:39
     */
    public boolean setRedisMapExpire(String key, Map<String, Object> map, long time) {
        Boolean temp = false;
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                setExpire(key, time);
            }
            temp = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * @Title: setRedisMapValueNonexistentCreation
     * @Description: 向一张hash表中放入数据, 如果不存在将创建
     * @Param: [key, item, value] key ->键,item ->Map的Key,value ->值
     * @Return: boolean  true ->成功,false ->失败
     * @Date: 2020/1/27 13:05
     */
    public boolean setRedisMapValueNonexistentCreation(String key, String item, Object value) {
        Boolean temp = false;
        try {
            redisTemplate.opsForHash().put(key, item, value);
            temp = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * @Title: setRedisMapValueExpireNonexistentCreation
     * @Description: 向一张hash表中放入数据, 如果不存在将创建, 并设置过期时间
     * @Param: [key, item, value, time] key ->键,item ->Map的Key,value ->值,time ->时间(秒)注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @Return: boolean  true ->成功,false ->失败
     * @Date: 2020/1/27 13:08
     */
    public boolean setRedisMapValueExpireNonexistentCreation(String key, String item, Object value, long time) {
        Boolean temp = false;
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                setExpire(key, time);
            }
            temp = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * @Title: deleteRedisMapValues
     * @Description: 删除hash表中的值
     * @Param: [key, item]  key ->键 不能为null,item ->项 可以使多个 不能为null,即:Map的Key
     * @Return: void   返回类型
     * @Date: 2020/1/27 13:11
     */
    public void deleteRedisMapValues(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * @Title: validateRedisMapValue
     * @Description: 判断hash表中是否有该项的值
     * @Param: [key, item] key ->键 不能为null,item ->项 不能为null,即:Map的Key
     * @Return: boolean  true ->成功,false ->失败
     * @Date: 2020/1/27 13:13
     */
    public boolean validateRedisMapValue(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * @Title: setRedisMapIncrement
     * @Description: hash递增 如果不存在,就会创建一个 并把新增后的key返回
     * @Param: [key, item, by] key ->键,item ->即:Map的Key,by ->要增加几(大于0)
     * @Return: double   返回类型
     * @Date: 2020/1/27 13:18
     */
    public double setRedisMapIncrement(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * @Title: setRedisMapDecrease
     * @Description: hash递减
     * @Param: [key, item, by] key ->键,item ->即:Map的Key,by ->要增加几(大于0)
     * @Return: double   返回类型
     * @Date: 2020/1/27 13:20
     */
    public double setRedisMapDecrease(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    /*============================================================*/
    /*============================set=============================*/
    /*============================================================*/


    /**
     * @Title: getRedisSet
     * @Description: 根据key获取Set中的所有值
     * @Param: [key]  key ->键
     * @Return: java.util.Set<java.lang.Object>   返回类型
     * @Date: 2020/1/27 13:26
     */
    public Set<?> getRedisSet(String key) {
        Set<?> hashSet = Sets.newHashSetWithExpectedSize(16);
        try {
            hashSet = redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashSet;
    }

    /**
     * @Title: validateRedisSetValue
     * @Description: 根据value从一个set中查询, 是否存在
     * @Param: [key, value]  key ->redisKey,value ->Set集合中的值
     * @Return: boolean  true ->存在, false ->不存在
     * @Date: 2020/1/27 13:28
     */
    public boolean validateRedisSetValue(String key, Object value) {
        Boolean temp = false;
        try {
            temp = redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * @Title: setRedisSetValues
     * @Description: 将数据放入set缓存
     * @Param: [key, values]  key ->键,values ->值 可以是多个
     * @Return: long   返回类型
     * @Date: 2020/1/27 13:36
     */
    public long setRedisSetValues(String key, Object... values) {
        long id = 0;
        try {
            id = redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * @Title: setRedisSetValuesExpire
     * @Description: 将set数据放入缓存, 并设置过期时间
     * @Param: [key, time, values]  key ->键,time ->时间(秒),values ->值 可以是多个
     * @Return: long   成功个数
     * @Date: 2020/1/27 13:41
     */
    public long setRedisSetValuesExpire(String key, long time, Object... values) {
        long count = 0;
        try {
            count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                setExpire(key, time);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * @Title: getRedisSetSize
     * @Description: 获取set缓存的长度
     * @Param: [key]   key ->键
     * @Return: long   Set 长度
     * @Date: 2020/1/27 13:44
     */
    public long getRedisSetSize(String key) {
        long len = 0;
        try {
            len = redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return len;
    }

    /**
     * @Title: setRedisSetRemoveValues
     * @Description: 移除值为value的
     * @Param: [key, values] key ->键,values ->值 可以是多个
     * @Return: long   移除的个数
     * @Date: 2020/1/27 13:47
     */
    public long setRedisSetRemoveValues(String key, Object... values) {
        long count = 0;
        try {
            count = redisTemplate.opsForSet().remove(key, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }


    /*====================================================================*/
    /*===============================list=================================*/
    /*====================================================================*/

    /**
     * @Title: getRedisList
     * @Description: 获取list缓存的内容
     * @Param: [key, start, end]  key ->键,start ->开始,end  ->结束  0 到 -1代表所有值
     * @Return: java.util.List<java.lang.Object>   返回类型
     * @Date: 2020/1/27 13:59
     */
    public List<?> getRedisList(String key, long start, long end) {
        List<?> list = Lists.newArrayList();
        try {
            list = redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @Title: getRedisListSize
     * @Description: 获取list缓存的长度
     * @Param: [key]  key ->键
     * @Return: long   list 长度
     * @Date: 2020/1/27 14:02
     */
    public long getRedisListSize(String key) {
        long len = 0;
        try {
            len = redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return len;
    }

    /**
     * @Title: getRedisListValue
     * @Description: 通过索引 获取list中的值
     * @Param: [key, index]  key ->键,index ->索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @Return: java.lang.Object   返回类型
     * @Date: 2020/1/27 14:05
     */
    public Object getRedisListValue(String key, long index) {
        Object obj = new Object();
        try {
            obj = redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * @Title: setRedisListValue
     * @Description: 将值放入list缓存
     * @Param: [key, value]  key ->键,value ->值
     * @Return: boolean  true ->成功,false ->失败
     * @Date: 2020/1/27 14:09
     */
    public boolean setRedisListValue(String key, Object value) {
        Boolean temp = false;
        try {
            redisTemplate.opsForList().rightPush(key, value);
            temp = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * @Title: setRedisListValueExpire
     * @Description: 将值放入list缓存, 并设置过期时间
     * @Param: [key, value, time]  key ->键,value ->值,time ->时间(秒)
     * @Return: boolean  true ->成功,false ->失败
     * @Date: 2020/1/27 14:11
     */
    public boolean setRedisListValueExpire(String key, Object value, long time) {
        Boolean temp = false;
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                setExpire(key, time);
            }
            temp = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * @Title: setRedisList
     * @Description: 将值放入list缓存
     * @Param: [key, value] key ->键,value ->值
     * @Return: boolean  true ->成功,false ->失败
     * @Date: 2020/1/27 14:14
     */
    public boolean setRedisList(String key, List<?> value) {
        Boolean temp = false;
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            temp = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * @Title: setRedisListExpire
     * @Description: 将list放入缓存, 并设置过期时间
     * @Param: [key, value, time] key ->键,value ->值,time ->时间(秒)
     * @Return: boolean  true ->成功,false ->失败
     * @Date: 2020/1/27 14:17
     */
    public boolean setRedisListExpire(String key, List<?> value, long time) {
        Boolean temp = false;
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                setExpire(key, time);
            }
            temp = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * @Title: setRedisListUpdateIndexValue
     * @Description: 根据索引修改list中的某条数据
     * @Param: [key, index, value] key ->键,index ->索引,value ->值
     * @Return: boolean  true ->成功,false ->失败
     * @Date: 2020/1/27 14:23
     */
    public boolean setRedisListUpdateIndexValue(String key, long index, Object value) {
        Boolean temp = false;
        try {
            redisTemplate.opsForList().set(key, index, value);
            temp = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * @Title: setRedisListRemoveIndexValue
     * @Description: 移除N个值为value的元素(Element)
     * @Param: [key, count, value] key ->键,count ->移除多少个,value ->值
     * @Return: long  移除的个数
     * @Date: 2020/1/27 14:26
     */
    public long setRedisListRemoveIndexValue(String key, long count, Object value) {
        long remove = 0;
        try {
            remove = redisTemplate.opsForList().remove(key, count, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return remove;
    }

    /*==============================================================*/
    /*=============绑定集合List操作(BoundListOperations) 用法==========*/
    /*==============================================================*/

    /**
     * @Title: setRedisValueAddToListRight
     * @Description: 将数据添加到Redis的list中（从右边添加）并设置过期时间
     * @Param: [listKey, expireEnum, values] listKey ->list redis Key,expireEnum ->有效期的枚举类,values ->待添加的数据
     * @Return: void   返回类型
     * @Date: 2020/1/27 14:34
     */
    public void setRedisValueAddToListRight(String listKey, Status.ExpireEnum expireEnum, Object... values) {
        //绑定操作
        BoundListOperations<String, Object> boundValueOperations = redisTemplate.boundListOps(listKey);
        //插入数据
        boundValueOperations.rightPushAll(values);
        //设置过期时间
        boundValueOperations.expire(expireEnum.getTime(), expireEnum.getTimeUnit());
    }

    /**
     * @Title: traverseRedisList
     * @Description: 根据起始结束序号遍历Redis中的list
     * @Param: [listKey, start, end] listKey ->list redis Key,start ->起始序号,end ->结束序号
     * @Return: java.util.List<java.lang.Object>   返回类型
     * @Date: 2020/1/27 14:36
     */
    public List<?> traverseRedisList(String listKey, long start, long end) {
        //绑定操作
        BoundListOperations<String, ?> boundValueOperations = redisTemplate.boundListOps(listKey);
        //查询数据
        return boundValueOperations.range(start, end);
    }

    /**
     * @Title: redisListPop
     * @Description: 弹出右边的值 --- 并且移除这个值
     * @Param: [listKey]  listKey ->list redis Key
     * @Return: java.lang.Object   返回类型
     * @Date: 2020/1/27 14:38
     */
    public Object redisListPop(String listKey) {
        //绑定操作
        BoundListOperations<String, Object> boundValueOperations = redisTemplate.boundListOps(listKey);
        return boundValueOperations.rightPop();
    }

}
