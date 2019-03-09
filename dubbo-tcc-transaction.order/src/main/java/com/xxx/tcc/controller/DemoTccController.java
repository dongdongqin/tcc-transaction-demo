package com.xxx.tcc.controller;

import com.xxx.tcc.dao.UserDao;
import com.xxx.tcc.domain.Order;
import com.xxx.tcc.model.User;
import com.xxx.tcc.service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class DemoTccController {

    @Autowired
    PaymentServiceImpl paymentService;

    @Autowired
    UserDao userDao;

    @RequestMapping("/sayHello/{name}")
    public String sayHello(@PathVariable("name") String name) throws Exception{
        Order order = new Order();
        BigDecimal redPacketPayAmount = new BigDecimal(10);
        java.math.BigDecimal capitalPayAmount = new BigDecimal(23);

        paymentService.makePayment(order, redPacketPayAmount, capitalPayAmount);
        //User user = new User();
        //user.setName(name);
        //UserInsert(user);
        return "qindongdong";
    }

    @Transactional(value="primaryTransactionManager",rollbackFor= Exception.class)
    public void UserInsert(User user) throws Exception {
            userDao.insert(user);
           // throw new RuntimeException("transaction happened");
    }
}
