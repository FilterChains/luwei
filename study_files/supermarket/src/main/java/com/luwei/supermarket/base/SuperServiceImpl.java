package com.luwei.supermarket.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
public class SuperServiceImpl<M extends BaseMapper<T>, T> implements SuperService<T> {

    @Autowired
    protected M baseMapper;

    /**
     * <p>@Description : 插入一条记录</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:12 </p>
     *
     * @param entity 实体
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(T entity) {
        return SqlHelper.retBool(baseMapper.insert(entity));
    }

    /**
     * <p>@Description : 批量插入</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:15 </p>
     *
     * @param entityList 实体集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchInsert(Collection<T> entityList) {
        return this.batchInsert(entityList, BATCH_SIZE);
    }

    /**
     * <p>@Description : 批量插入</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:15 </p>
     *
     * @param entityList 实体集合
     * @batchSize 更新批次数量（每次更新最大数量）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchInsert(Collection<T> entityList, int batchSize) {
        if (CollectionUtils.isEmpty(entityList)) {
            return false;
        }

        Class<?> clazz = ReflectionKit.getSuperClassGenericType(this.getClass(), 1);
        int i = 0;
        try (SqlSession batchSqlSession = SqlHelper.sqlSessionBatch(clazz)) {
            for (T entity : entityList) {
                batchSqlSession.insert(SqlHelper.table(clazz).getSqlStatement(SqlMethod.INSERT_ONE.getMethod()), entity);

                //批量更新或插入数据
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
                i++;
            }
            batchSqlSession.flushStatements();
        }
        return true;
    }

    /**
     * <p>@Description : 根据@TableId判断是更新还是新增记录</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:36 </p>
     *
     * @param entity 实体
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertOrUpdate(T entity) {
        if (entity == null) {
            return false;
        }

        Class<?> clazz = entity.getClass();
        TableInfo tableInfo = TableInfoHelper.getTableInfo(clazz);
        if (tableInfo == null || StringUtils.isEmpty(tableInfo.getKeyProperty())) {
            throw ExceptionUtils.mpe("方法执行异常，没有找到@TableId");
        }

        Object idVal = ReflectionKit.getMethodValue(clazz, entity, tableInfo.getKeyProperty());

        //判断主键值是否有值
        if (StringUtils.checkValNull(idVal)) {
            return this.insert(entity);
        } else {
            //有值，根据ID查询数据，存在则更新，不存在则新增
            return Objects.nonNull(this.get((Serializable) idVal)) ? this.updateById(entity) : insert(entity);
        }
    }

    /**
     * <p>@Description : 批量更新插入</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:16 </p>
     *
     * @param entityList 实体集合
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchInsertOrUpdate(Collection<T> entityList) {
        if (CollectionUtils.isEmpty(entityList)) {
            return true;
        }

        Class<?> clazz = ReflectionKit.getSuperClassGenericType(this.getClass(), 1);
        TableInfo tableInfo = TableInfoHelper.getTableInfo(clazz);
        int i = 0;
        try (SqlSession batchSqlSession = SqlHelper.sqlSessionBatch(clazz)) {
            for (T entity : entityList) {
                if (tableInfo != null && StringUtils.isNotEmpty(tableInfo.getKeyProperty())) {
                    //获取主键的值
                    Object idVal = ReflectionKit.getMethodValue(clazz, entity, tableInfo.getKeyProperty());
                    //判断值是否为空，不为空，再根据ID查询数据，为空则新增
                    if (StringUtils.checkValNull(idVal) || Objects.isNull(this.get((Serializable) idVal))) {
                        batchSqlSession.insert(SqlHelper.table(clazz).getSqlStatement(SqlMethod.INSERT_ONE.getMethod()), entity);
                    } else {
                        MapperMethod.ParamMap<T> paramMap = new MapperMethod.ParamMap<>();
                        paramMap.put(Constants.ENTITY, entity);
                        batchSqlSession.update(SqlHelper.table(clazz).getSqlStatement(SqlMethod.UPDATE_BY_ID.getMethod()), paramMap);
                    }

                    //批量更新或插入数据
                    if (i >= 1 && i % BATCH_SIZE == 0) {
                        batchSqlSession.flushStatements();
                    }
                    i++;
                } else {
                    throw ExceptionUtils.mpe("方法执行异常，没有找到@TableId");
                }
            }
            batchSqlSession.flushStatements();
        }
        return true;
    }

    /**
     * <p>@Description : 根据ID删除</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:17 </p>
     *
     * @param id 主键ID
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteById(Serializable id) {
        return SqlHelper.delBool(baseMapper.deleteById(id));
    }

    /**
     * <p>@Description : 根据entity条件删除记录</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:18 </p>
     *
     * @param condition 实体  注意：此condition表示删除的条件
     *                  {@link com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper}
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(T condition) {
        UpdateWrapper<T> wrapper = new UpdateWrapper<>();
        wrapper.setEntity(condition);
        return SqlHelper.delBool(baseMapper.delete(wrapper));
    }

    /**
     * <p>@Description : 删除所有记录</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:26 </p>
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete() {
        return SqlHelper.delBool(baseMapper.delete(Wrappers.emptyWrapper()));
    }

    /**
     * <p>@Description : 根据ID批量删除</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/3/15 13:51 </p>
     *
     * @param idList ID集合
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchDeleteById(Collection<? extends Serializable> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return true;
        }
        return SqlHelper.delBool(baseMapper.deleteBatchIds(idList));
    }

    /**
     * <p>@Description : 根据ID选择修改</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:26 </p>
     *
     * @param entity 实体
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(T entity) {
        return SqlHelper.retBool(baseMapper.updateById(entity));
    }

    /**
     * <p>@Description : 根据ID批量更新</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:34 </p>
     *
     * @param entityList 实体集合
     * @param batchSize  更新批次数量（每次更新最大数量）
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchUpdateById(Collection<T> entityList, int batchSize) {
        if (CollectionUtils.isEmpty(entityList)) {
            return true;
        }

        Class<?> clazz = ReflectionKit.getSuperClassGenericType(this.getClass(), 1);
        int i = 0;
        String sqlStatement = SqlHelper.table(clazz).getSqlStatement(SqlMethod.UPDATE_BY_ID.getMethod());
        try (SqlSession batchSqlSession = SqlHelper.sqlSessionBatch(clazz)) {
            for (T entity : entityList) {
                MapperMethod.ParamMap<T> paramMap = new MapperMethod.ParamMap<>();
                paramMap.put(Constants.ENTITY, entity);
                batchSqlSession.update(sqlStatement, paramMap);
                //批量执行更新
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
                i++;
            }
            batchSqlSession.flushStatements();
        }
        return true;
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
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(T entity, T condition) {
        UpdateWrapper<T> wrapper = new UpdateWrapper<>();
        wrapper.setEntity(condition);
        return SqlHelper.retBool(baseMapper.update(entity, wrapper));
    }

    /**
     * <p>@Description : 统计所有数据</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:44 </p>
     *
     * @return
     */
    @Override
    @DataSource(DataSourceContext.SLAVE)
    public int count() {
        return SqlHelper.retCount(baseMapper.selectCount(Wrappers.emptyWrapper()));
    }

    /**
     * <p>@Description : 根据实体的条件，查询总条数</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:43 </p>
     *
     * @param condition 实体条件
     * @return
     */
    @Override
    @DataSource(DataSourceContext.SLAVE)
    public int count(T condition) {
        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<>();
        wrapper.setEntity(condition);
        return SqlHelper.retCount(baseMapper.selectCount(wrapper));
    }

    /**
     * <p>@Description : 根据实体的条件，查询数据</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:45 </p>
     *
     * @param condition 实体条件
     * @return
     */
    @Override
    @DataSource(DataSourceContext.SLAVE)
    public List<T> find(T condition) {
        if (condition == null) {
            return baseMapper.selectList(Wrappers.emptyWrapper());
        } else {
            QueryWrapper<T> wrapper = new QueryWrapper<>();
            wrapper.setEntity(condition);
            return baseMapper.selectList(wrapper);
        }
    }

    /**
     * <p>@Description : 根据实体包装类的条件，查询数据，响应自定义对象</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 16:06 </p>
     *
     * @param wrapper 实体包装类 {@link com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper}
     * @param mapper  自定义对象类
     * @param <R>
     * @return
     */
    @DataSource(DataSourceContext.SLAVE)
    protected <R> List<R> find(Wrapper<T> wrapper, Function<? super T, R> mapper) {
        return baseMapper.selectList(wrapper).stream().filter(Objects::nonNull).map(mapper).collect(Collectors.toList());
    }

    /**
     * <p>@Description : 翻页查询</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:49 </p>
     *
     * @param condition  实体条件 {@link com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper}
     * @param pagination 分页模型
     * @return
     */
    @Override
    @DataSource(DataSourceContext.SLAVE)
    public Pagination findByPage(T condition, Pagination pagination) {
        IPage<T> page = this.toIPage(pagination);
        if (condition == null) {
            return this.toPagination(baseMapper.selectPage(page, Wrappers.emptyWrapper()), pagination);
        } else {
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.setEntity(condition);
            return this.toPagination(baseMapper.selectPage(page, wrapper), pagination);
        }
    }

    /**
     * <p>@Description : 翻页查询所有数据，自定响应数据对象</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 16:04 </p>
     *
     * @param page   翻页对象
     * @param mapper 自定义对象类
     * @param <R>
     * @return
     */
    @DataSource(DataSourceContext.SLAVE)
    protected <R> IPage<R> findByPage(IPage page, Function<? super T, R> mapper) {
        return baseMapper.selectPage(page, Wrappers.emptyWrapper()).convert(mapper);
    }

    /**
     * <p>@Description : 翻页查询，自定义响应数据对象</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 16:02 </p>
     *
     * @param page    翻页对象
     * @param wrapper 实体包装类 {@link com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper}
     * @param mapper  自定义对象类
     * @param <R>
     * @return
     */
    @DataSource(DataSourceContext.SLAVE)
    protected <R> IPage<R> findByPage(IPage page, Wrapper<T> wrapper, Function<? super T, R> mapper) {
        return baseMapper.selectPage(page, wrapper).convert(mapper);
    }

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
    @Override
    @DataSource(DataSourceContext.SLAVE)
    public List<T> findByPageList(T condition, int pageNo, int pageSize) {
        IPage<T> page = new Page<>(pageNo, pageSize, false);
        if (condition == null) {
            page = baseMapper.selectPage(page, Wrappers.emptyWrapper());
        } else {
            QueryWrapper<T> wrapper = new QueryWrapper<>();
            wrapper.setEntity(condition);
            page = baseMapper.selectPage(page, wrapper);
        }
        return page.getRecords();
    }

    /**
     * <p>@Description : 翻页查询，返回List，自定义数据类型,注意：page需要设置isSearchCount=false</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/14 13:32 </p>
     *
     * @param page    翻页对象
     * @param wrapper 实体包装类 {@link com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper}
     * @param mapper  自定义对象类
     * @param <R>
     * @return
     */
    @DataSource(DataSourceContext.SLAVE)
    private <R> List<R> findByPageList(IPage page, Wrapper<T> wrapper, Function<? super T, R> mapper) {
        page = this.findByPage(page, wrapper, mapper);
        return page.getRecords();
    }

    /**
     * <p>@Description : 根据实体包装类的条件,返回查询后的第一列数据</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:57 </p>
     *
     * @param wrapper 实体包装类 {@link com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper}
     * @param column  列名
     * @param <R>
     * @return
     */
    @DataSource(DataSourceContext.SLAVE)
    protected <R> List<R> findOfObject(Wrapper<T> wrapper, Function<? super Object, R> column) {
        return baseMapper.selectObjs(wrapper).stream().filter(Objects::nonNull).map(column).collect(Collectors.toList());
    }


    /**
     * <p>@Description : 根据ID查询对象</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 15:40 </p>
     *
     * @param id 主键
     * @return
     */
    @Override
    @DataSource(DataSourceContext.SLAVE)
    public T get(Serializable id) {
        return baseMapper.selectById(id);
    }

    /**
     * <p>@Description : 根据实体类的条件，获取第一条数据</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 16:17 </p>
     *
     * @param condition 实体 {@link com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper}
     * @return
     */
    @Override
    @DataSource(DataSourceContext.SLAVE)
    public T get(T condition) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.setEntity(condition);
        return SqlHelper.getObject(baseMapper.selectList(wrapper));
    }

    @Override
    @DataSource(DataSourceContext.SLAVE)
    public List<T> findBatchIds(Collection<? extends Serializable> idList) {
        return baseMapper.selectBatchIds(idList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public T insertGetKey(T entity) {
        baseMapper.insert(entity);
        return entity;
    }

    /**
     * <p>@Description : 根据实体包装类的条件，获取第一条数据，响应自定义对象</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 16:19 </p>
     *
     * @param wrapper 实体包装类 {@link com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper}
     * @param mapper  自定义对象
     * @param <R>
     * @return
     */
    @DataSource(DataSourceContext.SLAVE)
    protected <R> R get(Wrapper<T> wrapper, Function<? super T, R> mapper) {
        return SqlHelper.getObject(this.find(wrapper, mapper));
    }

    /**
     * <p>@Description : 查询所有数据，将响应数据以对象的属性为Key的形式转为Map数据</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 16:25 </p>
     *
     * @param column 实体属性
     * @param <K>
     * @return
     */
    @DataSource(DataSourceContext.SLAVE)
    protected <K> Map<K, T> findOfMap(SFunction<T, K> column) {
        return this.findOfMap(Wrappers.emptyWrapper(), column);
    }

    /**
     * <p>@Description : 根据实体包装类的条件，将响应数据以对象的属性为Key的形式转成Map数据</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/2/12 16:22 </p>
     *
     * @param wrapper 实体包装类 {@link com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper}
     * @param column  实体属性
     * @param <K>
     * @return
     */
    @DataSource(DataSourceContext.SLAVE)
    protected <K> Map<K, T> findOfMap(Wrapper<T> wrapper, SFunction<T, K> column) {
        List<T> list = baseMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }

        Map<K, T> map = new LinkedHashMap<>(list.size());
        for (T entity : list) {
            Field field = ReflectionUtils.findField(entity.getClass(), StringUtils.resolveFieldName(LambdaUtils.resolve(column).getImplMethodName()));
            if (Objects.isNull(field)) {
                continue;
            }
            ReflectionUtils.makeAccessible(field);
            Object fieldValue = ReflectionUtils.getField(field, entity);
            map.put((K) fieldValue, entity);
        }

        return map;
    }

    /**
     * <p>@Description : 转换为Mybatis plus的分页模型</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/3/14 12:10 </p>
     * 为什么有这两个转换器，其目的就是不让调用层为了一个IPage类而引用mybatis plus的jar包
     * 最好的办法就是重写mybatis plus的分页插件(PaginationInterceptor、IPage、Page)
     */
    protected IPage toIPage(Pagination pagination) {
        return new Page(pagination.getPageNo(), pagination.getPageSize(), pagination.isSearchCount());
    }

    /**
     * <p>@Description : Mybatis plus模型转为自定义模型</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/3/14 12:12 </p>
     */
    protected Pagination toPagination(IPage page, Pagination pagination) {
        pagination.setRecords(page.getRecords());
        pagination.setTotalCount(page.getTotal());
        pagination.setTotalPages(page.getPages());
        return pagination;
    }

}
