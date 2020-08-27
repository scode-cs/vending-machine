package com.scode.domain;

import com.scode.domain.model.GenericProductResponseModel;
import com.scode.domain.model.OrderModel;
import com.scode.domain.model.OrderReponseModel;

public interface OrderDomain {
    public GenericProductResponseModel placeOrder(OrderModel orderModel);
}
