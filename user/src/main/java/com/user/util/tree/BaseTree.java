package com.user.util.tree;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class BaseTree implements Serializable {

    /**
     * 整形
     */
    private Integer valueInt;

    /**
     * 字符串
     */
    private String valueStr;

    /**
     * 节点
     */
    private BaseTree node;
}
