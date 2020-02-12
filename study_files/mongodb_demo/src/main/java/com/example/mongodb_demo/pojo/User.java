package com.example.mongodb_demo.pojo;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

//user 是mongodb数据库中集合名
@Data
@ToString
@Builder
@Document(collection = "user")
public class User {
    private String account, password, name, phone;
}