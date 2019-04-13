package com.sopark.cache;

import com.sopark.cache.domain.Member;
import com.sopark.cache.domain.User;
import com.sopark.cache.domain.UserRepository;
import com.sopark.cache.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HibernateCacheTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Before
    public void setUp(){
        userRepository.deleteAll();

        IntStream.range(0, 3).forEach(value -> {
            User user = new User(Long.valueOf(value), "sopark1");

            IntStream.range(0, 3).forEach(value1 -> {
                user.addMember(new Member("MEMBER" + value));
            });

            userRepository.save(user);
        });
    }

    @Test
    public void findAllUsers_퀄리1번수(){
        userRepository.findAll();
        System.out.println("==========================");
        userRepository.findById(1L);
        userRepository.findById(1L);
        userRepository.findById(1L);
        System.out.println("==========================");
    }

    @Test
    public void printAllMembers_멤버엔티티캐시됨(){
        System.out.println("==========================");
        userService.printAllMembers();
        System.out.println("==========================");
        userService.printAllMembers();
        System.out.println("==========================");
    }

    @Test
    public void aaa(){
        // Given
        if(aaaa() || aaaa() || aaaa() || aaaa()) {
            System.out.println("finish");
        }
        // When

        // Then

    }

    private boolean aaaa() {
        System.out.println("aaaaa");
        return true;
    }

}
