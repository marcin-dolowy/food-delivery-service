package com.epam.training.fooddelivery.converter;

import com.epam.training.fooddelivery.domain.OrderItem;
import com.epam.training.fooddelivery.model.OrderItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderItemListConverter implements Converter<List<OrderItem>,List<OrderItemModel>> {
    private SingleFoodConverter singleFoodConverter;
    @Autowired
    public OrderItemListConverter(SingleFoodConverter singleFoodConverter) {
        this.singleFoodConverter = singleFoodConverter;
    }

    @Override
    public List<OrderItemModel> convert(List<OrderItem> orderItems) {
        List<OrderItemModel> orderItemModels = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            OrderItemModel orderItemModel = new OrderItemModel();
            orderItemModel.setId(orderItem.getId());
            orderItemModel.setFoodModel(singleFoodConverter.convert(orderItem.getFood()));
            orderItemModel.setPrice(orderItem.getPrice().intValue());
            orderItemModel.setPieces(orderItem.getPieces());
            orderItemModels.add(orderItemModel);
        }
        return orderItemModels;
    }
}
