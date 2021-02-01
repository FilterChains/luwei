package com.user.util.schema.practice;

import lombok.Data;

import java.io.Serializable;
@Data
public class Person implements Serializable {

    private String name;

    private Integer age;

    private Byte sex;

    private String idCard;
}
