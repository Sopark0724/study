package com.sopark.cache;

import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Cacheable(cacheNames = "GlobalConfig", key = "123")
    public void printAllMembers(){
        userRepository.findAll().stream()
                .forEach(user -> System.out.println(user.getMembers()));
    }
}
