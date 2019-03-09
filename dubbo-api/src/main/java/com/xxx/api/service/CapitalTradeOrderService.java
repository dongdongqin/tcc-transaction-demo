package com.xxx.api.service;

import com.xxx.api.service.domain.CapitalTradeOrderDto;
import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.dubbo.context.DubboTransactionContextEditor;

/**
 * Created by changming.xie on 4/1/16.
 */
public interface CapitalTradeOrderService {

    @Compensable(confirmMethod = "confirmMakePayment", cancelMethod = "cancelMakePayment", transactionContextEditor = DubboTransactionContextEditor.class)
    public String record(CapitalTradeOrderDto tradeOrderDto);

}
