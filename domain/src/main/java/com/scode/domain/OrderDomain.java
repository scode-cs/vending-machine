package com.scode.domain;

import com.scode.domain.model.OrderModel;
import com.scode.domain.model.OrderReponseModel;

public interface OrderDomain {
    public OrderReponseModel placeOrder(OrderModel orderModel);
}
