package com.luwei.supermarket.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * <p>@Description : 分页模型</p>
 * <p>@Author : luwei </p>
 * <p>@Date : 2019/3/14 10:47 </p>
 */
@Data
@ApiModel
public class Pagination<T> implements Serializable {

    /**
     * 每页数据
     */
    @ApiModelProperty("返回记录")
    private List<T> records;

    /**
     * 当前页
     */
    @ApiModelProperty("当前页")
    private int pageNo;

    /**
     * 页大小
     */
    @ApiModelProperty("页大小")
    private int pageSize;

    /**
     * 总条数
     */
    @ApiModelProperty("总条数")
    private long totalCount;

    /**
     * 总页数
     */
    @ApiModelProperty("总页数")
    private long totalPages;

    /**
     * 是否统计
     */
    @ApiModelProperty("是否统计")
    private boolean isSearchCount;

    public Pagination(int pageNo, int pageSize) {
        this(pageNo, pageSize, true);
    }

    public Pagination(int pageNo, int pageSize, boolean isSearchCount) {
        this.pageNo = pageNo < 1 ? 1 : pageNo;
        this.pageSize = pageSize < 1 ? 10 : pageSize;
        this.isSearchCount = isSearchCount;
        this.records = Collections.emptyList();
    }
}
