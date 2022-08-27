package com.epam.training.fooddelivery.converter;

import com.epam.training.fooddelivery.domain.OrderItem;
import com.epam.training.fooddelivery.model.OrderItemModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderItemModelListConverter implements Converter<List<OrderItemModel>, List<OrderItem>> {

    private SingleFoodModelConverter singleFoodModelConverter;

    public OrderItemModelListConverter(SingleFoodModelConverter singleFoodModelConverter) {
        this.singleFoodModelConverter = singleFoodModelConverter;
    }

    @Override
    public List<OrderItem> convert(List<OrderItemModel> orderItemModels) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemModel orderItemModel : orderItemModels) {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(orderItemModel.getId());
            orderItem.setFood(singleFoodModelConverter.convert(orderItemModel.getFoodModel()));
            orderItem.setPieces(orderItemModel.getPieces());
            orderItem.setPrice(new BigDecimal(orderItemModel.getPrice()));
            orderItems.add(orderItem);
        }
        return orderItems;
    }
}
