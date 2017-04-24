package chapter2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Transform {

    /**
     * string 타입이 아닌 경우에는 원본이 회손될 수 있음.
     */
    @Test
    public void 전통적인_방법(){
        final List<String> friends = Arrays.asList("sopark", "tkddh", "sharky");
        final List<String> uppercaseNames = new ArrayList<>();

        for(String name : friends){
            uppercaseNames.add(name.toUpperCase());
        }
        System.out.println(friends);
        System.out.println(uppercaseNames);
    }

    @Test
    public void 람다표현식(){
        final List<String> friends = Arrays.asList("sopark", "tkddh", "sharky");
        final List<String> uppercaseNames = new ArrayList<>();

        // forEach 안에서는 print 문을 출력할 수 없음.
        friends.forEach(name -> uppercaseNames.add(name.toUpperCase()));
    }

    /**
     * stream 메소드는 java8 에서 모든 컬렉션에서 사용할 수 있음.
     * map은 forEach 와 전혀 다르다.
     * 컬렉션에 있는 각 엘리먼트의 내용에 대한 처리를 하며 map() 메소드는 람다 표현식의 실행 결과를 취합하여 결과 컬렉션으로 리턴
     * 반환타입
     * map : steam
     * forEach : void
     */
    @Test
    public void map메소드사용(){
        final List<String> friends = Arrays.asList("sopark", "tkddh", "sharky");

        friends.stream()
                .map(name -> name.toUpperCase())
                .forEach(name -> System.out.println(name));

    }

    @Test
    public void map메소드사용_메소드레퍼런스(){
        final List<String> friends = Arrays.asList("sopark", "tkddh", "sharky");

        friends.stream()
                .map(name -> name.toUpperCase())
                .forEach(System.out::println);

    }
}
