package com.epam.training.fooddelivery.converter;

import com.epam.training.fooddelivery.domain.Order;
import com.epam.training.fooddelivery.model.OrderModel;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class OrderListConverter implements Converter<List<Order>, List<OrderModel>> {
    private final OrderItemListConverter orderItemListConverter;

    @Override
    public List<OrderModel> convert(List<Order> orders) {
        List<OrderModel> orderModels = new ArrayList<>();
        for (Order order : orders) {
            OrderModel orderModel = new OrderModel();
            orderModel.setId(order.getId());
            orderModel.setPrice(order.getPrice().longValue());
            orderModel.setTimestampCreated(order.getTimestampCreated().toLocalDate());
            orderModel.setOrderItemModels(orderItemListConverter.convert(order.getOrderItems()));
            orderModels.add(orderModel);
        }
        return orderModels;
    }
}