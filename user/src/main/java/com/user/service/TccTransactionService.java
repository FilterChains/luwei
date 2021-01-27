// package com.user.service;
//
// import io.seata.spring.annotation.GlobalTransactional;
// import jdk.nashorn.internal.ir.annotations.Reference;
// import org.springframework.stereotype.Service;
//
// import javax.annotation.Resource;
//
// @Service
// public class TccTransactionService {
//
//     @Resource
//     private TccActionOne tccActionOne;
//     @Resource
//     private TccActionTwo tccActionTwo;
//
//     /**
//      * 测试分布式事务提交示例
//      */
//     @GlobalTransactional
//     public String testCommit() {
//         // 第一个TCC 事务参与者
//         String result = tccActionOne.prepare("action-one-commit").getMessage();
//         // 第二个TCC 事务参与者
//         result = result + "\n" + tccActionTwo.prepare("action-two-commit").getMessage();
//         // 数据库操作
//         userService.save(getUser());
//         return result;
//     }
//
//     /**
//      * 测试分布式事务回滚示例
//      */
//     @GlobalTransactional
//     public String testRollback() {
//         //第一个TCC 事务参与者
//         String result = tccActionOne.prepare("action-one-rollback").getMessage();
//         // 第二个TCC 事务参与者
//         result = result + "\n" + tccActionTwo.prepare("action-two-rollback").getMessage();
//         // 数据库操作
//         userService.save(getUser());
//         throw new RuntimeException(result);
//     }
// }
