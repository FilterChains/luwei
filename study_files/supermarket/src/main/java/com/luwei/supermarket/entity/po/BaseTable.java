package com.luwei.supermarket.entity.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.entity.po
 * @auther: luwei
 * @description:
 * @date: 2020/5/14 22:05
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Data
class BaseTable implements Serializable {
    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private Integer updateBy;

    /**
     * 修改时间
     */
    private Date updateTime;
}
