# Cache

## Spring Cache

이번에는 스프링에서 제공하는 Spring Cache 에 대해서 알아보자. 설정은 Second Level Cache 와 동일하게 

```java
@EnableCaching
public class CacheApplication implements ApplicationRunner {
}
```

```@EnableCaching``` 를 붙여주면 자동으로 설정되며 관련 라이브러리를 pom.xml 에 추가하면 된다(구현체에 대한 설정이 없으면 기본적으로는 구현체는 ConcurrentMapCacheManager).  예제에서는 ehcache예를 통해 에 대해서 설명하겠다.

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-cache</artifactId>
        </dependency>
        <dependency>
            <groupId>net.sf.ehcache</groupId>
            <artifactId>ehcache</artifactId>
        </dependency>
```

관련 XML 설정은 [위에 ehcache 설정](#xml_config) 과 동일하다.

cache 관련 어노테이션

| 어노테이션   | 설명                             |
| ------------ | -------------------------------- |
| @Cacheable   | 캐시 설정                        |
| @CachePut    | 캐시 갱신                        |
| @CacheEvict  | 캐시되있는 데이터 지우기         |
| @CacheConfig | 클래스 레벨에서 캐시 설정의 공유 |

어노테이션 설정

| 설정       | 설명                              |
| ---------- | --------------------------------- |
| value      | 캐시이름                          |
| cacheNames | 캐시이름 (value 속성과 같다)      |
| key        | 캐시할 키를 설정                  |
| condition  | 특정 조건에 따라 캐시를 할지 결정 |



#### @Cacheable 

캐시를 설정할때 사용한다. 해당 어노테이션에 조건에 맞으면 이전에 캐시했던 반환값을 반환한다. 이제 샘플 코드를 통해 캐시를 적용해 보고 정상 동작하는지 확인해보자.

```java
@Service
public class UserService {
  ....
		@Transactional
    @Cacheable(value = "GlobalConfig")
    public List<User> findByAllUsers() {
        return userRepository.findAll();
    }
  ....
}
```

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringChacheTest {
  ....
		@Test
    public void springCahce_test(){
        System.out.println("=======================================");
        userService.findByAllUsers();
        System.out.println("=======================================");
        userService.findByAllUsers();
        System.out.println("=======================================");
    }
  ....
}
```

결과

![image](https://user-images.githubusercontent.com/6028071/55843251-409de600-5b72-11e9-85e6-dd94600756cb.png)

![image](https://user-images.githubusercontent.com/6028071/55843450-48aa5580-5b73-11e9-8741-cd0cd9af40f7.png)

예상했던 결과로 첫번째 메소드만 데이터베이스에 쿼리를 하고 2번째 메소드는 데이터베이스에 쿼리를 날리지 않고 캐시에 가지고 있는 값을 가지고 반환을 한다. 반환된 객체의 주소값 또한 캐시된 객체와 동일한 주소값을 가진다.

몇가지 테스트흘 해보자

- 같은 클래스간에 메소드 호출 

```java
		@Test
    @Transactional
    public void springCahce_같은클래스메소드호출(){
        this.userService.findByAllUsers();
        this.userService.printAllMembersType();
    }
```

```java
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
```

TC 코드를 보면 처음 ```findByAllUsers()``` 메소드를 호출하여 cache 를 하고 있고 이후에 ```printAllMembersType()``` 메소드를 호출하여 ```printAllMembersType``` 를 호출한다. 메소드는 같은 클래스에 있는 findAllUsers 메소드를 호출 하고 있다. findAllUsers 메소드를 호출하면 캐시된 값이 나올것 같지만 실행되는 쿼리을 보자.

![image](https://user-images.githubusercontent.com/6028071/55845444-cf176500-5b7c-11e9-867a-9949fa10e8be.png)

캐시한 메소드를 호출했지만 다시 쿼리를 하는 결과가 나왔다. 모든 어노테이션이 비슷하겠지만 어노테이션이 붙은 메소드는 프록시로 동작하기 때문에 외부에서 내부로 호출했을때만 동작한다. 이는 ```@Transaction``` 과 같은 어노테이션도 동일하다.

- 캐시되어 있는 엔티티를 이용한 객체 그래프 탐색

캐시되어 있는 객체를 이용해서 객체 그래프 탐색을 할때는 어떨까? 테스트를 해보자.









