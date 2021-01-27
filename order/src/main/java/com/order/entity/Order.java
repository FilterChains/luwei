package com.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@TableName("td_order")
public class Order implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer count;
    private BigDecimal money;
    private Integer orderStatus;

}
