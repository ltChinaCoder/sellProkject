package com.lt.sell.service;


import com.lt.sell.dataobject.OrderMaster;
import com.lt.sell.dto.OrderMasterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderMasterService {
    OrderMasterDto create(OrderMasterDto orderMaster);

    OrderMasterDto findByOrderId(String orderId);

    Page<OrderMaster> findByOpenId(String openId, Pageable pageable);

    OrderMasterDto cancelOrder(OrderMasterDto orderMaster);

    OrderMasterDto finishOrder(OrderMasterDto orderMaster);

    OrderMasterDto payOrder(OrderMasterDto orderMaster);

}
