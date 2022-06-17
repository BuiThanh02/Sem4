package com.t2010a.productapi.api;

import com.t2010a.productapi.entity.Product;
import com.t2010a.productapi.entity.User;
import com.t2010a.productapi.service.ProductService;
import com.t2010a.productapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RequestMapping(path = "api/v1/users")
@RestController
@CrossOrigin("*")
public class UserApi {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET,path = "{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id){
        Optional<User> optionalUser = userService.findById(id);
        if (!optionalUser.isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalUser.get());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> create(@RequestBody User user){
        user.setId(UUID.randomUUID());
        if (!userService.findById(user.getId()).isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userService.save(user));
    }

    @RequestMapping(method = RequestMethod.DELETE,path = "{id}")
    public ResponseEntity<?> deleteById(@PathVariable UUID id){
        if (!userService.findById(id).isPresent()){
            ResponseEntity.badRequest().build();
        }
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<User> update(@PathVariable UUID id, @RequestBody User updateUser){
        Optional<User> optionalUser = userService.findById(id);
        if (!optionalUser.isPresent()){
            ResponseEntity.badRequest().build();
        }
        User existUser = optionalUser.get();

        existUser.setCreatedAt(updateUser.getCreatedAt());
        existUser.setUpdatedAt(updateUser.getUpdatedAt());
        existUser.setStatus(updateUser.getStatus());
        existUser.setAddress(updateUser.getAddress());
        existUser.setEmail(updateUser.getEmail());
        existUser.setName(updateUser.getName());
        existUser.setPhone(updateUser.getPhone());

        return ResponseEntity.ok(userService.save(existUser));
    }
}
