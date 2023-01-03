package com.project.bookingnetic.controller;


import com.project.bookingnetic.models.User;
import com.project.bookingnetic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<User>> get(){
        return ResponseEntity.ok(service.get());
    }


//    @GetMapping("/user/{id}")
//    public ResponseEntity<List<User>> getUserById(){
//        return "user/profile";
//    }

    @PostMapping("/create")
    public ResponseEntity<User> save(@ModelAttribute("user") User user){
        return ResponseEntity.ok(service.save(user));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id  ){
        return service.deleteById(id);
    }

}