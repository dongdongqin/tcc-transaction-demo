package com.xxx.tcc.service;

import com.xxx.api.service.CapitalTradeOrderService;
import com.xxx.api.service.domain.CapitalTradeOrderDto;
import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.dubbo.context.DubboTransactionContextEditor;

import java.util.Date;

/**
 * Created by changming.xie on 4/2/16.
 */
//@Service(version = "1.0.0", timeout = 5000)
public class CapitalTradeOrderServiceImpl implements CapitalTradeOrderService {

    @Compensable(confirmMethod = "confirmRecord", cancelMethod = "cancelRecord", transactionContextEditor = DubboTransactionContextEditor.class)
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
