package com.convin.dailyexpensessharing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createdUser=userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user= userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user):ResponseEntity.notFound().build();
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users=userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


}