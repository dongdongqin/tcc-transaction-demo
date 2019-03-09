package com.xxx.tcc.service;

import com.xxx.api.service.CapitalTradeOrderService;
import com.xxx.api.service.domain.CapitalTradeOrderDto;
import org.mengyun.tcctransaction.api.Compensable;

import java.util.Date;

/**
 * Created by dongdongqin on 2019-03-03.
 */
//@Component
public class CapitalTradeOrderServiceImpl2 implements CapitalTradeOrderService {

    @Compensable(confirmMethod = "confirmRecord", cancelMethod = "cancelRecord", transactionContextEditor = Compensable.DefaultTransactionContextEditor.class)
    public String record(CapitalTradeOrderDto tradeOrderDto) {
        System.out.println("capital try record called. time seq:" + new Date());

        return "success";
    }

    public void confirmRecord(CapitalTradeOrderDto tradeOrderDto) {

        System.out.println("capital confirm record called. time seq:" + new Date());

    }


    public void cancelRecord(CapitalTradeOrderDto tradeOrderDto) {

        System.out.println("capital cancel record called. time seq:" + new Date());
    }
}
