package com.luwei.supermarket.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>@Description : service超级父类</p>
 * <p>@Author : QiLin.Xing </p>
 * <p>@Date : 2019/2/12 15:09 </p>
 */
public interface SuperService<T> {

    /**
     * 批量大小
     */
    int BATCH_SIZE = 1024;

    /**
     * <p>@Description : 插入一条记录</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:12 </p>
     *
     * @param entity 实体
     * @return
     */
    boolean insert(T entity);

    /**
     * <p>@Description : 批量插入</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:15 </p>
     *
     * @param entityList 实体集合
     */
    boolean batchInsert(Collection<T> entityList);

    /**
     * <p>@Description : 批量插入</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:15 </p>
     *
     * @param entityList 实体集合
     * @batchSize 更新批次数量（每次更新最大数量）
     */
    boolean batchInsert(Collection<T> entityList, int batchSize);

    /**
     * <p>@Description : 根据@TableId判断是更新还是新增记录</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:36 </p>
     *
     * @param entity 实体
     * @return
     */
    boolean insertOrUpdate(T entity);

    /**
     * <p>@Description : 批量更新插入</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:16 </p>
     *
     * @param entityList 实体集合
     * @return
     */
    boolean batchInsertOrUpdate(Collection<T> entityList);

    /**
     * <p>@Description : 根据ID删除</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:17 </p>
     *
     * @param id 主键ID
     * @return
     */
    boolean deleteById(Serializable id);

    /**
     * <p>@Description : 根据entity条件删除记录</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:18 </p>
     *
     * @param condition 实体  注意：此condition表示删除的条件
     *                  {@link com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper}
     * @return
     */
    boolean delete(T condition);

    /**
     * <p>@Description : 删除所有记录</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:26 </p>
     *
     * @return
     */
    boolean delete();

    /**
     * <p>@Description : 根据ID批量删除</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/3/15 13:51 </p>
     *
     * @param idList ID集合
     * @return
     */
    boolean batchDeleteById(Collection<? extends Serializable> idList);

    /**
     * <p>@Description : 根据ID选择修改</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:26 </p>
     *
     * @param entity 实体
     * @return
     */
    boolean updateById(T entity);

    /**
     * <p>@Description : 根据ID批量更新</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:34 </p>
     *
     * @param entityList 实体集合
     * @param batchSize  更新批次数量（每次更新最大数量）
     * @return
     */
    boolean batchUpdateById(Collection<T> entityList, int batchSize);

    /**
     * <p>@Description : 根据ID批量更新，默认单次1024条数据</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:35 </p>
     *
     * @param entityList 实体集合
     * @return
     */
    default boolean batchUpdateById(Collection<T> entityList) {
        return this.batchUpdateById(entityList, BATCH_SIZE);
    }

    /**
     * <p>@Description : 根据实体包装类的条件更新记录</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:29 </p>
     *
     * @param entity    实体(更新的内容）
     * @param condition 更新条件 {@link com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper}
     * @return
     */
    boolean update(T entity, T condition);

    /**
     * <p>@Description : 根据实体的条件，查询总条数</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:43 </p>
     *
     * @param condition 实体条件
     * @return
     */
    int count(T condition);

    /**
     * <p>@Description : 统计所有数据</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:44 </p>
     *
     * @return
     */
    int count();

    /**
     * <p>@Description : 根据实体的条件，查询数据</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:45 </p>
     *
     * @param condition 实体条件,为null即无条件
     * @return
     */
    List<T> find(T condition);

    /**
     * <p>@Description : 翻页查询</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:49 </p>
     *
     * @param condition  实体条件 {@link com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper}
     * @param pagination 分页模型
     * @return
     */
    Pagination findByPage(T condition, Pagination pagination);

    /**
     * <p>@Description : 翻页查询，返回List,注意：page参数需要设置isSearchCount=false</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/14 13:30 </p>
     *
     * @param condition 实体条件 {@link com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper}
     * @param pageNo    页码
     * @param pageSize  页大小
     * @return
     */
    List<T> findByPageList(T condition, int pageNo, int pageSize);

    /**
     * <p>@Description : 根据ID查询对象</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:40 </p>
     *
     * @param id 主键
     * @return
     */
    T get(Serializable id);

    /**
     * <p>@Description : 根据实体类的条件，获取第一条数据</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 16:17 </p>
     *
     * @param condition 实体条件 {@link com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper}
     * @return
     */
    T get(T condition);

    /**
     * <p>@description : 根据ID集合批量查询数据 </p>
     * <p>@author : Jia.Li </p>
     * <p>@date : 2019/6/19 13:34 </p>
     *
     * @param idList ID集合
     * @return 数据集合
     */
    List<T> findBatchIds(Collection<? extends Serializable> idList);

    /**
     * <p>@Description : 插入一条记录,返回主键</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:12 </p>
     *
     * @param entity 实体
     * @return
     */
    T insertGetKey(T entity);

}
