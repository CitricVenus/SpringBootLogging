package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private Map<Long, User> userStore = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong();

    @GetMapping
    public List<User> getAllUsers() {
        logger.info("Obteniendo lista de usuarios");
        return new ArrayList<>(userStore.values());
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user) {
        if (user.getName() == null || user.getEmail() == null) {
            logger.warn("Intento de crear usuario con datos incompletos");
            return ResponseEntity.badRequest().body("Nombre y email son requeridos");
        }
        long id = idCounter.incrementAndGet();
        user.setId(id);
        userStore.put(id, user);
        logger.info("Usuario creado con ID: {}", id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (!userStore.containsKey(id)) {
            logger.error("Intento de borrar usuario no existente con ID: {}", id);
            return ResponseEntity.notFound().build();
        }
        userStore.remove(id);
        logger.info("Usuario con ID {} eliminado", id);
        return ResponseEntity.ok().build();
    }
}
