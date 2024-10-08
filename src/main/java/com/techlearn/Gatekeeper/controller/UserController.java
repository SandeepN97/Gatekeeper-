package com.techlearn.Gatekeeper.controller;



import com.techlearn.Gatekeeper.model.User;
import com.techlearn.Gatekeeper.repository.UserRepository;
import com.techlearn.Gatekeeper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/user")

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user)
    {
        return  userService.addUser(user);
    }

    @GetMapping ("/allusers")
    public  ResponseEntity<List<User>> getAllUser (){
        return userService.getAllUsers();
    }

    @PutMapping ("/update")
    public  ResponseEntity<User> updateUser (@RequestBody User user)
    {
        return  userService.updateUser(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser (@PathVariable long id )
    {
        return userService.deleteUser(id);
    }

    @GetMapping ("/get/{id}")
    public ResponseEntity<User> getUserId (@PathVariable long id){
        return  userService.getUserById(id);
    }


}
