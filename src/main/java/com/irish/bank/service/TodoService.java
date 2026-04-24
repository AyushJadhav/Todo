package com.irish.bank.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irish.bank.dto.TodoRequest;
import com.irish.bank.model.Category;
import com.irish.bank.model.Todo;
import com.irish.bank.model.User;
import com.irish.bank.repository.CategoryRepository;
import com.irish.bank.repository.TodoRepository;
import com.irish.bank.repository.UserRepository;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;

    public Todo addTodo(String username, TodoRequest request) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Todo todo = new Todo();
        todo.setTitle(request.getTitle());
        todo.setCategory(category);
        todo.setCompleted(request.isCompleted());
        todo.setDate(request.getDate());
        todo.setUser(user);

        return todoRepository.save(todo);
    }

    public List<Todo> getTodos(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return todoRepository.findByUser(user);
    }

    public List<Todo> getTodosByDate(String username, LocalDate date) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return todoRepository.findByUserAndDate(user, date);
    }

    public Todo updateTodo(Long id, Todo updated) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        todo.setTitle(updated.getTitle());
        todo.setCompleted(updated.isCompleted());

        if (updated.getCategory() != null) {
            Category category = categoryRepository.findByName(updated.getCategory().getName())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            todo.setCategory(category);
        }

        return todoRepository.save(todo);
    }

    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new RuntimeException("Todo not found");
        }
        todoRepository.deleteById(id);
    }
}