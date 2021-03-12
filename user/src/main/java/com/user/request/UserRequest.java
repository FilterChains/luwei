package com.user.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserRequest implements Serializable {

    @NotNull(message = "修改时ID不能为空")
    private Long id;

    @NotBlank(message = "名称不能为空")
    private String name;
}
