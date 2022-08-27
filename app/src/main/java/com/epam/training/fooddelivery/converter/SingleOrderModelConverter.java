package com.epam.training.fooddelivery.converter;

import com.epam.training.fooddelivery.domain.Order;
import com.epam.training.fooddelivery.model.OrderModel;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SingleOrderModelConverter implements Converter<Order, OrderModel> {
    private final OrderItemListConverter orderItemListConverter;

    @Override
    public OrderModel convert(Order order) {
        OrderModel orderModel = new OrderModel();
        orderModel.setId(order.getId());
        orderModel.setPrice(order.getPrice().longValue());
        orderModel.setTimestampCreated(order.getTimestampCreated().toLocalDate());
        orderModel.setOrderItemModels(orderItemListConverter.convert(order.getOrderItems()));
        return orderModel;
    }
}
