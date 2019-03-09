package com.xxx.tcc.service;

import com.xxx.api.service.RedPacketTradeOrderService;
import com.xxx.api.service.domain.CapitalTradeOrderDto;
import org.mengyun.tcctransaction.api.Compensable;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by dongdongqin on 2019-03-03.
 */
@Component
public class RedPacketTradeOrderServiceImpl implements RedPacketTradeOrderService {

    @Compensable(confirmMethod = "confirmRecord", cancelMethod = "cancelRecord", transactionContextEditor = Compensable.DefaultTransactionContextEditor.class)
    public String record(CapitalTradeOrderDto tradeOrderDto) {
        System.out.println("RedPacketTradeOrderServiceImpl try record called. time seq:" + new Date());

        return "success";
    }

    public void confirmRecord(CapitalTradeOrderDto tradeOrderDto) {

        System.out.println("RedPacketTradeOrderServiceImpl confirm record called. time seq:" + new Date());

    }


    public void cancelRecord(CapitalTradeOrderDto tradeOrderDto) {

        System.out.println("RedPacketTradeOrderServiceImpl cancel record called. time seq:" + new Date());
    }

}
