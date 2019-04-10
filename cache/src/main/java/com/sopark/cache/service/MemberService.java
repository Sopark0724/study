package com.sopark.cache.service;

import com.sopark.cache.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final UserService userService;

    @Transactional
    public void printMembers(){
        List<User> users = userService.findByAllUsers();
        users.stream()
                .map(User::getMembers)
                .forEach(members -> members.forEach(member -> System.out.println(member)));
    }
}
