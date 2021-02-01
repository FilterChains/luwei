package com.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@TableName("td_storage")
public class Product implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer productId;
    private Integer total;
    private Integer used;
    private Integer residue;

}
