package com.epam.training.fooddelivery.converter;

import com.epam.training.fooddelivery.domain.Food;
import com.epam.training.fooddelivery.model.FoodModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SingleFoodConverter implements Converter<Food, FoodModel> {

    @Override
    public FoodModel convert(Food food) {
        FoodModel foodModel = new FoodModel();
        foodModel.setName(food.getName());
        foodModel.setId(food.getId());
        foodModel.setCalorie(food.getCalorie().intValue());
        foodModel.setPrice(food.getPrice().intValue());
        foodModel.setCategory(food.getCategory().toString());
        foodModel.setDescription(food.getDescription());
        return foodModel;
    }
}