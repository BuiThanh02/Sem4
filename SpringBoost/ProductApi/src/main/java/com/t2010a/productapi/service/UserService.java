package com.t2010a.productapi.service;

import com.t2010a.productapi.entity.User;
import com.t2010a.productapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Iterable<User> findAll(){
        Iterable<User> listUser = userRepository.findAll();
        ArrayList<User> validListUser = new ArrayList<>();
        for (User user: listUser
        ) {
            if (user.getStatus() == 1){
                validListUser.add(user);
            }
        }
        return validListUser;
    }

    public Optional<User> findById(UUID id){
        Optional<User> foundUser = userRepository.findById(id);
        if (!foundUser.isPresent()){
            ResponseEntity.badRequest().build();
        }
        User validUser = foundUser.get();
        if (validUser.getStatus() == 1){
            return foundUser;
        }else {
            return Optional.empty();
        }
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public void deleteById(UUID id){
        Optional<User> deleteUser = userRepository.findById(id);
        if (!deleteUser.isPresent()){
            ResponseEntity.badRequest().build();
        }
        User deletedUser = deleteUser.get();
        deletedUser.setStatus(0);
        userRepository.save(deletedUser);
    }
}
