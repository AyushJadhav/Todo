package com.irish.bank.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.irish.bank.dto.TodoRequest;
import com.irish.bank.model.Todo;
import com.irish.bank.service.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {

	
    @Autowired
    private TodoService todoService;

    @PostMapping("/add")
    public Todo addTodo(@RequestBody TodoRequest todo, Authentication auth) {

        String username = auth.getName();

        return todoService.addTodo(username, todo);
    }

    @GetMapping("/")
    public List<Todo> getTodos(Authentication auth) {

        String username = auth.getName();

        return todoService.getTodos(username);
    }

    @GetMapping("/date/{date}")
    public List<Todo> getByDate(@PathVariable String date, Authentication auth) {

        String username = auth.getName();

        return todoService.getTodosByDate(
                username,
                LocalDate.parse(date)
        );
    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable Long id, @RequestBody Todo todo) {
        return todoService.updateTodo(id, todo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }
}