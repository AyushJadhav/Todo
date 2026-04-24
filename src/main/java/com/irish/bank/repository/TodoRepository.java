package com.irish.bank.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irish.bank.model.Todo;
import com.irish.bank.model.User;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByUserAndDate(User user, LocalDate date);

    List<Todo> findByUser(User user);
}