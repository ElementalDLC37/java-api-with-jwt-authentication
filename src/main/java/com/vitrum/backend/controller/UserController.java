package com.vitrum.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitrum.backend.model.User;
import com.vitrum.backend.repository.UserRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    
    @GetMapping()
    public List<User> list() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> find(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @PostMapping()
    public String create(@RequestBody User user) {
        userRepository.save(user);
        return "Criado.";
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<User> put(@PathVariable Long id, @RequestBody User user) {        
        return userRepository.findById(id).map(found -> {
            found.setName(user.getName());
            User updated = userRepository.save(found);
            return ResponseEntity.ok(updated);
        }).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {        
        return userRepository.findById(id).map(found -> {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
