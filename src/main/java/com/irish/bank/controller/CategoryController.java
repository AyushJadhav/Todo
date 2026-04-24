package com.irish.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irish.bank.model.Category;
import com.irish.bank.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
    
    @PostMapping("/init")
    public String initCategories() {

        if (categoryRepository.count() > 0) {
            return "Categories already exist";
        }

        categoryRepository.saveAll(List.of(
                new Category(null, "BREAKFAST"),
                new Category(null, "LUNCH"),
                new Category(null, "DINNER"),
                new Category(null, "SNACKS"),
                new Category(null, "WORK"),
                new Category(null, "PERSONAL")
        ));

        return "Categories inserted successfully";
    }
}