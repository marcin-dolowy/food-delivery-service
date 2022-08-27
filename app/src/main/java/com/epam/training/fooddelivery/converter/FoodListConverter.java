package com.epam.training.fooddelivery.converter;

import com.epam.training.fooddelivery.domain.Food;
import com.epam.training.fooddelivery.model.FoodModel;
import org.springframework.stereotype.Component;

import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.List;

@Component
public class FoodListConverter implements Converter<List<Food>, List<FoodModel>> {

    @Override
    public List<FoodModel> convert(List<Food> foodList) {
        List<FoodModel> foodModels = new ArrayList<>();
        for (Food food : foodList) {
            FoodModel foodModel = new FoodModel();
            foodModel.setName(food.getName());
            foodModel.setId(food.getId());
            foodModel.setCalorie(food.getCalorie().intValue());
            foodModel.setPrice(food.getPrice().intValue());
            foodModel.setCategory(food.getCategory().toString());
            foodModel.setDescription(food.getDescription());
            foodModels.add(foodModel);
        }
        return foodModels;
    }
}
