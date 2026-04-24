package com.irish.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.irish.bank.model.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}