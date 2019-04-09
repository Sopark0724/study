# Cache

Cache 를 이용하여 Entity 에 적용하는 Second Level Cache 와 Spring Framework
에서 제공하는 Cache 에 대해서 알아보도록 하겠습니다.

## Second Level Cache (L2 cache, Shared Cache)

#### 동작원리

데이터베이스 통신 비용이 크기 때문에 Spring JPA 에서는 1차 캐시와 2차 캐시를 통해
Entity 에 대한 데이터베이스에 대한 통신 비용을 줄입니다. 1차 캐시는 영속성 컨텍스트
내에서 사용한다면 2차 캐시는 영속성 컨텍스트가 아닌 곳에서도 사용할 수 있습니다.

2차 캐시 동작 방식에 대해서 알아보자.

![image](https://user-images.githubusercontent.com/6028071/55772733-79818080-5ac8-11e9-8eff-20df5a7c2a18.png)

1. 영속성 컨텍스트에 엔티티가 없으면 2차 캐시를 조회한다.
2. 2차 캐시에 엔티티가 없으면 데이터 베이스를 조회한다. 

![image](https://user-images.githubusercontent.com/6028071/55772739-81412500-5ac8-11e9-9a8f-bdd32a64e0c6.png)

3. 데이터베이스에 조회한 데이터는 2차 캐시에 보관한다.
4. 2차 캐시는 자신이 보관하고 있는 엔티티를 복사해서 반환한다.

![image](https://user-images.githubusercontent.com/6028071/55772747-8900c980-5ac8-11e9-9fbe-b2dd963187c4.png)

5. 이후 다음 요청이 있을 경우 1차 캐시에 값이 없을 경우 2차 캐시를 조회하고 2차 캐시에
   값이 있으면 DB 를 조회하지 않고 2차 캐시에 있는 값을 리턴한다.

하이버네이트에서 지원하는 캐시는 3가지가 있다.

1. 엔티티 캐시 : 엔티티 단위로 캐시한다. 식별자로 엔티티를 조회하거나 컬렉션이 아닌 연관된 엔티티를 로딩할 때 사용한다.
2. 컬렉션 캐시 : 엔티티와 연관된 컬렉션을 캐시한다. 컬렉션이 엔티티를 담고 있으면 식별자 값만 캐시한다.
3. 쿼리 캐시 : 쿼리와 파라미터 정보를 키로 사용한다. 결과가 엔티티면 식별자 값만 캐시한다.

#### 적용하기

Second Level Cache 의 구현체는 여러개가 있지만 예제에서는 ehcache의 적용방법에 대해서
알아보자. [다른 구현체에 대한 자료](https://docs.spring.io/spring-boot/docs/2.1.4.RELEASE/reference/htmlsingle/#boot-features-caching-provider)

0. Entity 생성하기

User와 Member Entity 생성하고 해당 Entity에 캐시를 적용하는 방법을 알아보자.

```java

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class User {
    @Id @GeneratedValue @NonNull
    private Long id;

    @NonNull
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    List<Member> members = new ArrayList<>();

    public void addMember(Member member){
        this.members.add(member);
    }
}

@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class Member {

    @Id @GeneratedValue
    private Long id;

    @NonNull
    private String Type;
}

```

1. pom.xml 에 관련 라이브러리 추가 

Second Level Cache를 적용하기 위해서는 우선 pom.xml 에 관련 라이브러리들을
추가해주어야 한다. (다른 부가적인 라이브러리들은 소스파일을 참고)

```xml
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-ehcache</artifactId>
            <version>5.3.9.Final</version>
        </dependency>                
```

> ehcache 라이브러리도 포함해줘야 하지만 hibernate-ehcache 에 dependency 로 잡혀
> 있기 때문에 별다른 설정을 하지 않아도 된다. 만약 Spring Cache 만 사용하고 싶다면
> ehcache 라이브러리만 별도로 추가하면 된다.

![image](https://user-images.githubusercontent.com/6028071/55779423-5367da80-5ae0-11e9-97d4-f3925d5d68d7.png)

2. @EnableCaching 어노테이션 추가

```java
@SpringBootApplication
@EnableCaching
public class CacheApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }
}
```

3. application.json 에 관련 설정 추가

```properties
# ehcache 관련 xml 위치
spring.cache.ehcache.config=classpath:ehcache.xml
# 캐시 구현체 지정
spring.jpa.properties.hibernate.cache.region.factory_class=org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory
# second_leve_cache 사용여부
spring.jpa.properties.hibernate.cache.use_second_level_cache=true
# query cache 사용여부
spring.jpa.properties.hibernate.cache.use_query_cache=true
```

4. ehcache.xml 파일 정의

```xml
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:noNamespaceSchemaLocation="ehcache.xsd" updateCheck="false"
         monitoring="autodetect" dynamicConfig="false">
    <cache name="GlobalConfig"
           eternal="false"
           overflowToDisk="false"
           timeToLiveSeconds="3600"
           timeToIdleSeconds="3600"
           statistics="true"
           maxEntriesLocalHeap="3600"
           memoryStoreEvictionPolicy="LRU">
    </cache>
    <defaultCache
            maxBytesLocalHeap="3600"
            eternal="false"
            timeToLiveSeconds="3600"
            timeToIdleSeconds="3600"
            overflowToDisk="false"
            statistics="true"
            memoryStoreEvictionPolicy="LRU"/>
</ehcache>
```

defaultCache 태그는 반드시 존재하여야 하며 ```org.hibernate.annotations.Cache```
의 경우 이름을 지정하지 않으면 defaultCache 속성 값으로 ```해당 패키지 + 클래스``` 명으로 지정된다.

| 속성값 |설명| 
|---|---|
|name|캐시 이름을 설정한다. 캐시를 식별할때 사용한다.|
|maxBytesLocalHeap| 최대 로컬 힙메모리 사용량 설정, 1k, 1m, 1g 해당 옵션을 사용할 경우 maxEntriesLocalHeap 설정은 사용 할 수 없음.|
|maxElementsInMemory| 메모리에 캐싱 되어질 객체의 최대수|
|maxEntriesLocalHeap| 힙메모리 최대량|
|maxElementsOnDisk| 디스크 캐시에 저장 될 최대 객체의 수를 지정|
|maxEntriesLocalDisk| 로컬 디스크에 유지 될 최대 객체 수|
|eternal|저장된 캐시를 제거할지 여부를 설정한다. true 인 경우 저장된 캐시는 제거되지 않으며 timeToIdleSeconds, timeToLiveSeconds 설정은 무시된다.|
|overflowToDisk| maxElementsInMemory 음계량에 가까우면 오버플로우되는 객체들을 디스크에 저장 할지 결정|
|timeToIdleSeconds| 생성후 해당 시간 동안 캐쉬가 사용되지 않으면 삭제된다. 0은 삭제되지 않는다. 단 eternal=false 인 경우에만 유효하다.| 
|timeToLiveSeconds| 생성후 해당시간이 지나면 캐쉬는 삭제된다. 0은 삭제되지 않는 다. 단 eternal=false 인 경우에만 유효하다. |
|diskExpiryThreadIntervalSeconds|디스크(DiskStore)에 저장된 캐시들을 정리하기 |위한 작업의 실행 간격 시간을 설정한다. 기본값은 120초|
|diskSpoolBufferSizeMB| 스풀버퍼에 대한 디스크(DiskStore) 크기 설정한다.OutOfMemory 에러가 발생 시 설정한 크기를 낮추는 것이 좋다. |
|clearOnFlush|flush() 메서드가 호출되면 메모리(MemoryStore)가 삭제할지 여부를 설정한다. 기본값은 true 이며, 메모리(MemoryStore)는 삭제된다. |
|memoryStoreEvictionPolicy | maxEntriesLocalHeap 설정 값에 도달했을때 설정된 정책에 따리 객체가 제거되고 새로추가된다.<br>LRU: 사용이 가장 적었던 것부터 제거한다.<br>FIFO: 먼저 입력된 것부터 제거한다.<br>LFU: 사용량이 적은 것부터 제거한다.|
|logging | 로깅 사용 여부를 설정한다.|
|maxEntriesInCache | Terracotta의 분산캐시에만 사용가능하며, 클러스터에 저장 할 수 있는 최대 엔트리 수를 설정한다. 0은 제한이 없다. 캐시가 작동하는 동안에 속성을 수정할 수 있다.|
|overflowToOffHeap | 이 설정은 Ehcache 엔터프라이즈 버전에서 사용할 수 있다. true 로 설정하며 성능을 향상시킬 수 있는 Off-heap 메모리 스토리지를 활용하여 캐시를 사용할 수 있다. Off-heap 메모리 자바의 GC에 영향을 주지않는다. (기본값은 false) <br> |

5. @Cache, @Cacheble 적용

사용하는 패치지 구조에 대해서 잘확인하고 사용해야 한다. spring 에서 사용하는 
```org.springframework.cache.annotation.Cacheable``` 도 있기 때문에 구분을 잘해서 사용해야 한다.

- @Cachealbe(javax.persistence.Cacheable) : 캐시 사용할 것인지에 대한 옵션이지만 hibernate cache 를 사용할 경우 해당 옵션은 무시되는 것으로 보인다. 
관련 설정을 하거나 안하거나 같은 결과값이 나온다.

![image](https://user-images.githubusercontent.com/6028071/55780270-8b701d00-5ae2-11e9-9851-e1fa2f7e7db9.png)

- @Cache(org.hibernate.annotations.Cache) : 엔티티나 엔티티의 관련 컬렉션 정보를 캐시할때 사용한다.

![image](https://user-images.githubusercontent.com/6028071/55780248-7bf0d400-5ae2-11e9-962e-361ce6ff9c26.png)

| 속성명  |설명 |
|---|---|
| usage  | CacheConcurrencyStrategy를 이요해서 캐시동시성 전략을 사용할 수 있다. |
| region | ehcache.xml 에 정의된 name 의 값에 저장한다. 저장하지 않을 경우 ```해당 패키지 + 클래스``` 명으로 저장된다. |
| include | all : 모든 필드 <br> non-lazy : non-lazy인 필드들만 |

- CacheConcurrencyStrategy
|속성|설명|
|---|---|
|NONE| 캐시를 설정하지 않음|
|READ_ONLY|읽기 전용으로 설정한다. **등록, 삭제는 가능하지만 수정은 불가능.** 객체를 복사하지 않고 원본 객체를 반환|
|NONSTRICT_READ_WRITE|엄격하지 않은 읽고 쓰기 전략. 동시에 같은 엔티티를 수정하면 데이터 일관성이 깨질 수 있다. **EHCACHE는 데이터를 수정하면 캐시 데이터를 무효화 한다.**|
|READ_WRITE|읽기 쓰기가 가능하고 READ COMMITTED 정도의 격리 수준을 보장한다. EHCACHE는 데이터를 수정하면 캐시 데이터도 같이 수정한다.|
|TRANSACTIONAL|컨테이너 관리 환경에서 사용할 수 있다. 설정에 따란 REPEATABLE READ 정도의 격리 수준을 보장받을 수 있다.|

```java
@Cache(region = "GlobalConfig",usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User {
    ....
    @Cache(region = "GlobalConfig", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    List<Member> members = new ArrayList<>();
    ....
}
```
사용하는 Entity와 List에 ```@Cache``` 를 적용했다. 그럼 실제 캐시가 적용되는지 확인해보자

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheApplicationTests {

    @Autowired
    private UserRepository userRepository;

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
    public void findAllUsers(){
        userRepository.findAll();
        System.out.println("==========================");
        userRepository.findById(1L);
        userRepository.findById(1L);
        userRepository.findById(1L);
        System.out.println("==========================");
    }
}
```
실행결과

![image](https://user-images.githubusercontent.com/6028071/55798168-3e537180-5b09-11e9-88d4-7bea4e7ec95d.png)

실제 쿼리는 ```userRepository.findAll()``` 할때 한번만 날아가고 이후에 ``ùserRepository.findBy(1L)```을
호출했을 때는 쿼리가 안날아가는거 보니 제대로 적용된것 같다.

그럼 다음으로 user객체에 있는 members 에 대해서도 캐시가 잘 적용되었은지 확인해보자.
user객체의 members 는 OneToMany의 Lazy 로 되어 있기때문에 영속성 처리를 위해 UserService
클래스를 만들고 그안에서 호출하도록 해보자.

```java
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void printAllMembers(){
        userRepository.findAll().stream()
                .forEach(user -> System.out.println(user.getMembers()));
    }
}

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheApplicationTests {
    
    ....
    
    @Test
    public void printAllMembers(){
        System.out.println("==========================");
        userService.printAllMembers();
        System.out.println("==========================");
        userService.printAllMembers();
        System.out.println("==========================");
    }
}
```

실행 결과

![image](https://user-images.githubusercontent.com/6028071/55799204-bd49a980-5b0b-11e9-9895-d70e7c640bd9.png)

첫번째 호출할때와는 다르게 2번째 호출할때 더 많은 쿼리가 날아가는 것을 확인할 수 있다.

```java
    @Cache(region = "GlobalConfig", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    List<Member> members = new ArrayList<>();
```

List 에 @Cache를 걸게되면 Member의 id만 캐시하고 있고 실제 Member entity는 캐시를 하고 있지 않는다.
그렇기 때문에 가지고 있는 Member의 id를 가지고 다시 조회를 하는것이다. Member에도 동일한 ```@Cache``를 적용하고 다시 테스드를 돌려보자.

```java
@Entity
@ToString
@Cache(region = "GlobalConfig",usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class Member {

    @Id @GeneratedValue
    private Long id;

    @NonNull
    private String Type;
}
```

![image](https://user-images.githubusercontent.com/6028071/55799546-9d66b580-5b0c-11e9-9733-ae450ec62231.png)

2번째 호출할때 Member Entity 가 캐시가 되어 실행되는 쿼리는 1번이다. 





 
