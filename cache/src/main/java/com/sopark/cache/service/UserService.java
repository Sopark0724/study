package com.sopark.cache.service;

import com.sopark.cache.domain.User;
import com.sopark.cache.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void printAllMembers(){
        userRepository.findAll().stream()
                .forEach(user -> System.out.println(user.getMembers()));
    }

    @Transactional
    public void printAllMembersType(){
        this.findByAllUsers().stream()
                .map(user -> user.getMembers())
                .forEach(member -> System.out.println(member.get(0).getType()));
    }

    @Transactional
    @Cacheable(value = "GlobalConfig")
    public List<User> findByAllUsers() {
        return userRepository.findAll();
    }
}
