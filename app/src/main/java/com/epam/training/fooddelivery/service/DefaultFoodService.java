package com.epam.training.fooddelivery.service;

import com.epam.training.fooddelivery.domain.Food;
import com.epam.training.fooddelivery.repository.FoodRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class DefaultFoodService implements FoodService {

    private FoodRepository foodRepository;

    @Override
    @Transactional
    public List<Food> listAllFood() {
        return foodRepository.findAll();
    }
}
