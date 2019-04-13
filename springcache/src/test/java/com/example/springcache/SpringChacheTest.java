package com.example.springcache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Vector;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringChacheTest {

    @Test
    public void aaa(){
        // Given

        IntStream.range(0, Integer.valueOf("3")).mapToObj(value -> "hello").forEach(System.out::println);

        // When

        // Then

    }

    @Test
    public void sumTest(){
        // 1~11 까지 합
        Vector<Integer> params = new Vector<>();
        params.add(-10);
        params.add(-7);
        params.add(5);
        params.add(-7);
        params.add(10);
        params.add(5);
        params.add(-2);
        params.add(17);
        params.add(-25);
        params.add(1);
        System.out.println(fastestMaxSum(params));
    }

    public int fastestMaxSum(final Vector<Integer> A){

        return 0;
    }

/*
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MemberService memberService;

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
    public void springCahce_test(){
        System.out.println("=======================================");
        List<User> users = userService.findByAllUsers();
        System.out.println("=======================================");
        List<User> cachedUsers = userService.findByAllUsers();
        System.out.println("=======================================");
    }

    @Test
    public void springCahce_같은클래스메소드호출(){
        System.out.println("========= findByAllUsers ========");
        this.userService.findByAllUsers();
        System.out.println("========= printAllMembersType ========");
        this.userService.printAllMembersType();
        System.out.println("=====================");
    }

    @Test
    public void springCache_entity테스트(){
        this.userService.findByAllUsers();
        this.memberService.printMembers();
    }*/
}
