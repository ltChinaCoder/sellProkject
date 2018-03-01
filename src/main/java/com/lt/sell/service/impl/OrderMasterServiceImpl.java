package com.lt.sell.service.impl;

import com.lt.sell.dataobject.OrderDetail;
import com.lt.sell.dataobject.OrderMaster;
import com.lt.sell.dataobject.ProductInfo;
import com.lt.sell.dto.CartDto;
import com.lt.sell.dto.OrderMasterDto;
import com.lt.sell.enums.OrderStatusEnum;
import com.lt.sell.enums.PayStatusEnum;
import com.lt.sell.enums.SellErrorEnum;
import com.lt.sell.exception.SellException;
import com.lt.sell.repository.OrderDetailRepository;
import com.lt.sell.repository.OrderMasterRepository;
import com.lt.sell.service.OrderMasterService;
import com.lt.sell.service.ProductInfoService;
import com.lt.sell.service.WebSocket;
import com.lt.sell.util.KeyGenerateUtil;
import com.lt.sell.util.ListMapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderMasterServiceImpl implements OrderMasterService {
    @Autowired
    OrderMasterRepository orderMasterRepository;
    @Autowired
    ProductInfoService productInfoService;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    WebSocket webSocket;
    @Override
    @Transactional
    public OrderMasterDto create(OrderMasterDto orderMaster) {
        List<OrderDetail> orderDetails = orderMaster.getDetails();

        BigDecimal sum = new BigDecimal(0.00);
        String orderId = KeyGenerateUtil.getUniqueKey();
        List<CartDto> cartDtos = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null)
                throw new SellException(SellErrorEnum.PRODUCT_NOT_EXIST);
            if (productInfo.getProductStock() < orderDetail.getProductQuantity())
                throw new SellException(SellErrorEnum.STOCK__NOT_ENOUGH);
            BeanUtils.copyProperties(productInfo, orderDetail);
            sum = sum.add(orderDetail.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())));
            orderDetail.setDetailId(KeyGenerateUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            orderDetailRepository.save(orderDetail);
            cartDtos.add(new CartDto(orderDetail.getProductId(), orderDetail.getProductQuantity()));
        }
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(sum);
        OrderMaster orderMaster1 = new OrderMaster();
        BeanUtils.copyProperties(orderMaster, orderMaster1);
        orderMasterRepository.save(orderMaster1);
        productInfoService.decreaseStock(cartDtos);
        webSocket.sendMessage(orderMaster1.toString());
        return orderMaster;
    }

    @Override
    public OrderMasterDto findByOrderId(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (orderMaster == null)
            throw new SellException(SellErrorEnum.ORDER_NOT_EXIST);
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
        if (ListMapUtil.isNullAndEmpty(orderDetails))
            throw new SellException(SellErrorEnum.DETAIL_NOT_EXIST);
        OrderMasterDto orderMasterDto = new OrderMasterDto();
        BeanUtils.copyProperties(orderMaster, orderMasterDto);
        orderMasterDto.setDetails(orderDetails);
        return orderMasterDto;
    }

    @Override
    public Page<OrderMaster> findByOpenId(String openId, Pageable pageable) {
        return orderMasterRepository.findByBuyOpenid(openId, pageable);
    }

    @Override
    @Transactional
    public OrderMasterDto cancelOrder(OrderMasterDto orderMaster) {
        OrderMaster order = orderMasterRepository.findOne(orderMaster.getOrderId());
        if (order == null) {
            log.error("【取消订单】订单不存在，orderId={},orderStatus={} ", orderMaster.getOrderId(), order.getOrderStatus());
            throw new SellException(SellErrorEnum.ORDER_NOT_EXIST);
        }
        if (!order.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】订单状态不对，orderId={},orderStatus={} ", orderMaster.getOrderId(), order.getOrderStatus());
            throw new SellException(SellErrorEnum.ORDER_STATUS_ERROR);
        }
        if (CollectionUtils.isEmpty(orderMaster.getDetails())) {
            log.error("【取消订单】订单细节为空，orderMaster={} ", orderMaster);
            throw new SellException(SellErrorEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDto> cartDtos = orderMaster.getDetails().stream().
                map(e -> new CartDto(e.getProductId(), e.getProductQuantity())).
                collect(Collectors.toList());
        productInfoService.increaseStock(cartDtos);
        order.setOrderStatus(OrderStatusEnum.CANCELED.getCode());
        orderMaster.setOrderStatus(OrderStatusEnum.CANCELED.getCode());
        OrderMaster orderMaster1 = orderMasterRepository.save(order);
        if (orderMaster1 == null) {
            log.error("【取消订单】订单更新状态失败，orderId={},orderStatus={} ", orderMaster.getOrderId(), order.getOrderStatus());
            throw new SellException(SellErrorEnum.ORDER_STATUS_UPDATE_ERROR);
        }
        if (order.getPayStatus().equals(PayStatusEnum.PAY)) {
            //TODO
            // 退款
        }
        return orderMaster;
    }

    @Override
    @Transactional
    public OrderMasterDto finishOrder(OrderMasterDto orderMaster) {
        OrderMaster order = orderMasterRepository.findOne(orderMaster.getOrderId());
        if (order == null) {
            log.error("【完成订单】订单不存在，orderId={}", orderMaster.getOrderId());
            throw new SellException(SellErrorEnum.ORDER_NOT_EXIST);
        }
        if (!order.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完成订单】订单状态不对，orderId={},orderStatus={} ", orderMaster.getOrderId(), order.getOrderStatus());
            throw new SellException(SellErrorEnum.ORDER_STATUS_ERROR);
        }
        order.setOrderStatus(OrderStatusEnum.COMPLETED.getCode());
        OrderMaster orderMaster1 = orderMasterRepository.save(order);
        orderMaster.setOrderStatus(OrderStatusEnum.COMPLETED.getCode());
        if (orderMaster1 == null) {
            log.error("【完成订单】订单更新状态失败，orderId={},orderStatus={} ", orderMaster.getOrderId(), order.getOrderStatus());
            throw new SellException(SellErrorEnum.ORDER_STATUS_UPDATE_ERROR);
        }
        return orderMaster;
    }

    @Override
    @Transactional
    public OrderMasterDto payOrder(OrderMasterDto orderMaster) {
        OrderMaster order = orderMasterRepository.findOne(orderMaster.getOrderId());
        if (order == null) {
            log.error("【完成支付】订单不存在，orderId={}", orderMaster.getOrderId());
            throw new SellException(SellErrorEnum.ORDER_NOT_EXIST);
        }
        if (!order.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完成支付】订单状态不对，orderId={},orderStatus={} ", orderMaster.getOrderId(), order.getOrderStatus());
            throw new SellException(SellErrorEnum.ORDER_STATUS_ERROR);
        }
        if (order.getPayStatus().equals(PayStatusEnum.PAY.getCode())) {
            log.error("【完成支付】订单付款状态已付款，orderId={},payStatus={} ", orderMaster.getOrderId(), order.getPayStatus());
            throw new SellException(SellErrorEnum.ORDER_PAY_STATUS_ERROR);
        }
        order.setPayStatus(PayStatusEnum.PAY.getCode());
        orderMaster.setPayStatus(PayStatusEnum.PAY.getCode());
        OrderMaster orderMaster1 = orderMasterRepository.save(order);
        if (orderMaster1 == null) {
            log.error("【完成支付】订单付款状态更新失败，orderId={},payStatus={} ", orderMaster.getOrderId(), order.getPayStatus());
            throw new SellException(SellErrorEnum.ORDER_STATUS_UPDATE_ERROR);
        }
        return orderMaster;
    }

    @Override
    public Page<OrderMaster> findAll(Pageable pageable) {
        return orderMasterRepository.findAll(pageable);
    }
}
