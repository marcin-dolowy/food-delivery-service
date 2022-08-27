package com.epam.training.fooddelivery.controller;

import com.epam.training.fooddelivery.api.FoodserviceApi;
import com.epam.training.fooddelivery.converter.FoodListConverter;
import com.epam.training.fooddelivery.domain.Food;
import com.epam.training.fooddelivery.model.FoodModel;
import com.epam.training.fooddelivery.service.FoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class FoodController implements FoodserviceApi {

    private FoodService foodService;
    private FoodListConverter foodListConverter;

    public FoodController(FoodService foodService, FoodListConverter foodListConverter) {
        this.foodService = foodService;
        this.foodListConverter = foodListConverter;
    }

    @Override
    public ResponseEntity<List<FoodModel>> listAllFoods() {
        List<Food> foods = foodService.listAllFood();
        List<FoodModel> foodModelList = foodListConverter.convert(foods);
        return new ResponseEntity<>(foodModelList, HttpStatus.OK);
    }
}
