package com.project.bookingnetic.controller;

import com.project.bookingnetic.exception.MyException;
import com.project.bookingnetic.models.Address;
import com.project.bookingnetic.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Address>> get(){
        return ResponseEntity.ok(service.get());
    }

    @PostMapping
    public ResponseEntity<Address> save(@RequestBody Address address){
        return ResponseEntity.ok(service.save(address));
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Address> update(@PathVariable Long id, @RequestBody Address address) throws MyException {
        var update = service.update(address, id);
        if(update != null) {
            return ResponseEntity.ok(update);
        }
        throw new MyException("Address Not found");
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id  ){
        return service.deleteById(id);
    }
}
