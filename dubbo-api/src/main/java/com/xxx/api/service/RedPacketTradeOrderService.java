package com.xxx.api.service;

import com.xxx.api.service.domain.CapitalTradeOrderDto;
import org.mengyun.tcctransaction.api.Compensable;

/**
 * Created by changming.xie on 4/1/16.
 */
public interface RedPacketTradeOrderService {

    @Compensable
    public String record(CapitalTradeOrderDto tradeOrderDto);
}
