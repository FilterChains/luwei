package com.example.mongodb_demo.controller;

import com.example.mongodb_demo.pojo.User;
import com.google.common.collect.Lists;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.util.JSON;
import netscape.javascript.JSObject;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

@RestController
public class TestController {

    @Autowired
    @Qualifier(value = "mongoTemplate")
    protected MongoTemplate mongoTemplate;

    @RequestMapping("/test")
    public String test(){
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("卢伟"));
        User user = mongoTemplate.findOne(query, User.class);
        return user.toString();
    }

    @RequestMapping("/document")
    public void document(){
        MongoCollection<Document> user = mongoTemplate.getCollection("User");
        Document document = new Document();
        document.append("name","Hello");
        document.append("password","123456");
        user.insertOne(document);
    }

    @RequestMapping("/save")
    public void save(){
        mongoTemplate.save(User.builder().account("19922218826").name("卢伟")
                .password("123456").phone("19922218826").build());
        mongoTemplate.save(User.builder().account("19922218826").name("卢伟")
                .password("123456").phone("19922218826").build(),"User");
    }

    @RequestMapping("/update")
    public void update(){
        MongoCollection<Document> user = mongoTemplate.getCollection("User");
        user.updateOne(Filters.eq("name", "Hello"),
                new Document("$set",new Document("name","你好")));
    }

    @RequestMapping("/getUserMessage")
    public List<User> getUserMessage(){
        MongoCollection<Document> user = mongoTemplate.getCollection("user");
        Class<Document> documentClass = user.getDocumentClass();
        System.out.println(documentClass);
        FindIterable<Document> documents = user.find();
        MongoCursor<Document> iterator = documents.iterator();
        List<User> list = Lists.newArrayList();
        while(iterator.hasNext()){
            Document next = iterator.next();
            System.out.println(next);
            list.add(User.builder()
                    .account(next.getString("account"))
                    .password(next.getString("password"))
                    .name(next.getString("name"))
                    .phone(next.getString("phone")).build());
        }
        return list;
    }

    @RequestMapping("/findUser")
    public List<User> findUser(){
        MongoCollection<Document> user = mongoTemplate.getCollection("User");
        FindIterable<Document> documents = user.find(Filters.eq("name", "卢伟"));
        MongoCursor<Document> iterator = documents.iterator();
        List<User> list = Lists.newArrayList();
        while(iterator.hasNext()){
            Document next = iterator.next();
            System.out.println(next);
            list.add(User.builder()
                    .account(next.getString("account"))
                    .password(next.getString("password"))
                    .name(next.getString("name"))
                    .phone(next.getString("phone")).build());
        }
        DeleteResult deleteResult = user.deleteOne(Filters.eq("name", "你好"));
        System.err.println("删除结果"+deleteResult);
        return list;
    }

    @RequestMapping("/create")
    public void createCollection(){
        //也可以不先创建等到插入数据的时候在进行创建
        //创建对应实体类mongodb集合（mysql->table）
        MongoCollection<Document> collection = mongoTemplate.createCollection(User.class);
        System.out.println(collection);
        //创建任意mongodb集合（mysql ->table)
        MongoCollection<Document> collection1 = mongoTemplate.createCollection("mongoStudy");
        System.out.println(collection1);
    }
}

