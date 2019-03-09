package com.xxx.tcc.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xxx.api.service.CapitalTradeOrderService;
import com.xxx.api.service.domain.CapitalTradeOrderDto;
import com.xxx.tcc.domain.Order;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.dubbo.context.DubboTransactionContextEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by changming.xie on 4/1/16.
 */

@Service
public class PaymentServiceImpl {

    @Reference(version = "1.0.0")
    //@Autowired
    CapitalTradeOrderService capitalTradeOrderService;

    @Autowired
    RedPacketTradeOrderServiceImpl redPacketTradeOrderService;


    /**
     * 有一个问题，这个地方，就是说， 当出现下面的异常的时候，如果是这个jvm 上的时候，很正常， 可以回滚。
     * 但是问题在于，dubbo provider 那边，怎么知道这比交易已经有问题了呢
     * 需要回滚
     * @param order
     * @param redPacketPayAmount
     * @param capitalPayAmount
     */
    @Compensable(confirmMethod = "confirmMakePayment", cancelMethod = "cancelMakePayment", transactionContextEditor = DubboTransactionContextEditor.class)
    public void makePayment(Order order, BigDecimal redPacketPayAmount, BigDecimal capitalPayAmount) {
        System.out.println("order try make payment called.time seq:" + DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));
        // throw new RuntimeException("gollback"); 这个地方回滚就会很正常

        capitalTradeOrderService.record(buildTraderDto());
        redPacketTradeOrderService.record(buildTraderDto());
        // 这个地方的回滚就会产生问题了，前面的  capitalTradeOrderService.record， redPacketTradeOrderService.record 都已经成功了
        throw new RuntimeException("gollback");
    }

    public void confirmMakePayment(Order order, BigDecimal redPacketPayAmount, BigDecimal capitalPayAmount) {

        System.out.println("order confirm make payment called. time seq:" + DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));


    }

    public void cancelMakePayment(Order order, BigDecimal redPacketPayAmount, BigDecimal capitalPayAmount) {

        System.out.println("order cancel make payment called.time seq:" + DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));

        // TODO FIXME for below questions
        // 这个时候，才会是对的，我觉得应该再那个里面做以下，判断好是不是dubbo的，然后再继续， confirm 和 cancel 都是mideng 的才可以
        // 量外 confirm 的时候，应该有confirm 的机制，传过去，使用RPCContext来做
        // mideng 到底应该怎么设置呢，涉及到数据库表的话，最后是confirm 的时候才会去提交 try 和 confirm 这个
        // 如果confirm 出错呢? 此时也是有问题的把
       // capitalTradeOrderService.record(buildTraderDto());

    }

    private CapitalTradeOrderDto buildTraderDto() {
        CapitalTradeOrderDto tradeOrderDto = new CapitalTradeOrderDto();
        tradeOrderDto.setSelfUserId(1);
        tradeOrderDto.setAmount(new BigDecimal(20));
        return tradeOrderDto;
    }


}
