N + 1 쿼리 문제

JPA 를 무심코 사용하다 보면 "어 이상하네 쿼리가 왜케 느리지?" 라고 생각될 때가 있을것이다.
그래서 디버깅을 걸어서 문제되는 부분을 찾곤 한다.
추가적으로 JPA 가 느리다고 생각하는 사람들이 있을텐데 JPA의 특성을 잘 모르거나 JPA의 동작을 잘 이해하지 못해서 생기는 부분이 대부분이다.
오늘은 그중에서 N + 1 쿼리 문제에 대해서 알아보려고 한다.
예를 들어 다음과 같은 관계의 Entity 가 있다고 가정해보자.
Category 하나의 여러개의 Book 을 연결한 구조이다.

![image](https://user-images.githubusercontent.com/6028071/55605316-e6d2a000-57ae-11e9-964b-e35a9ef17eb7.png)

```java
@Entity
public class Catetory {
    @Id @GeneratedValue
    private Long id;

    private String name;
}


@Entity
public class Book {
    @Id @GeneratedValue
    private Long id;

    private String title;

    @ManyToOne
    private Catetory catetory;
} 
```

- 관계조건을 ManyToOne 으로 설정하고 Book 에 대한 정보를 조회해 보자

```java
public interface BookRepository extends JpaRepository<Book, Long> {
    
}
```

SQL을 콘솔에 보기위한 옵션
```
# sql 문을 보기 위한 옵션
spring.jpa.show-sql=true
# sql 문을 줄바꿈해주는 옵션
spring.jpa.properties.hibernate.format_sql=true

# sql 파라미터를 보기위한 옵션
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

위에 코드를 이용해서 테스트 코드를 작성해보자

```java

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaDemoApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void findAll() throws Exception {
        Category category = new Category("IT");
        categoryRepository.save(category);

        Book book1 = new Book("JPA 책", category);
        bookRepository.save(book1);
        Book book2 = new Book("Spring 책", category);
        bookRepository.save(book2);

        bookRepository.findAll();
    }
}
``` 

요렇게 작성해서 돌리면 오류가 난다. 이유는 Entity 는 기본 생성자를 가지고 있어야 하지만 롬복에서 사용되는 @RequiredArgsConstructor는 final 이나 @NonNull 인 컬럼을 이용해서 생성자를 만들기 때문에 기본생성자가 없다.
그래서 entity 에 ```@NoArgsConstructor(access = AccessLevel.PROTECTED)``` 을 이용해서 기본생성자를 만든다. PROTECTED 로 만드는 이유는 사용자가 entity 를 생성할때 기본생성자가 아닌 필수 파라미터를
이용해서 만들기 하기 위함이다.

자 이제 다시 코드로 돌아가서 위에 어노테이션을 추가하여 다시 돌려보자.

> 결과값
```
Hibernate: select book0_.id as id1_0_, book0_.catetory_id as catetory3_0_, book0_.title as title2_0_ from book book0_
Hibernate: select category0_.id as id1_1_0_, category0_.name as name2_1_0_ from category category0_ where category0_.id=?
Hibernate: select category0_.id as id1_1_0_, category0_.name as name2_1_0_ from category category0_ where category0_.id=?
```

다음과 같은 쿼리문이 발생했다. 이제 그이유에 대해서 알아보자.
Book 엔티티에는 Category 와의 관계 조건이 걸려져 있다. ``` @ManyToOne private Catetory catetory; ``` ManyToOne 의 경우 기본 fetch 전략이 eager 로 되어 있다.
``` FetchType fetch() default EAGER; ```

eager 의 경우 Book 이 조회되는 시점에 관련된 엔티티를 같이 조회를 한다. JPA 에서는 BOOK 을 먼저 조회하고 for 문을 돌며 관련 entity 를 DB 에서 조회한다. 지금은 2개만 조회 하지만 만약 category 가 1만개라면
1만개에 대한 쿼리를 발생한다. 그럼 당연히 속도문제가 발생할 수 밖에 없다.

이제 어떤 해결책이 있는지 확인해보자.

**1. 관계조건을 eager에서 lazy 로 변경**

```java
public class Book {
    ....
    @ManyToOne(fetch = FetchType.LAZY)
    private Category catetory;
    ....
}
```
관례조건을 위와 같이 바꾸고 다시 테스트를 돌려보자. 쿼리 결과는 다음과 같다.

> 결과값
```
Hibernate: select book0_.id as id1_0_, book0_.catetory_id as catetory3_0_, book0_.title as title2_0_ from book book0_
```

category 관련 쿼리 문이 없어 졌다. 일단 문제는 해결된것 처럼 보인다. 하지만 때에 따라서는 가져온 Book 의 정보에서 Category 정보를 가져와야 하는 경우도 존재 한다. 
그럴때 개별로 데이터를 가져오는게 아니고 한번에 데이터를 가져오면 불필요한 쿼리를 줄일 수 있다. myBatis 처럼 DB join 쿼리를 이용해서 한번에 처리할 수 있지만 우린 JPA를 사용하기 때문에 JPA 를 이용하여 어떻게 처리하는지 알아보자. 

**2. JPQL 을 이용한 패치 조인**

JPQL은 EntityManager 를 통해 생성할 수 있지만 JpaRepository 에서 ```@Query``` 를 사용해서 작성할 수 도 있다. 패치 조인을 사용하는 방법은 아래 코드를 참고하자. 

```java
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select book from Book book join fetch book.category")
    List<Book> findAllWithCategory();
}
```
> 결과값
```
    select
        book0_.id as id1_0_0_,
        category1_.id as id1_1_1_,
        book0_.category_id as category3_0_0_,
        book0_.title as title2_0_0_,
        category1_.name as name2_1_1_ 
    from
        book book0_ 
    inner join
        category category1_ 
            on book0_.category_id=category1_.id
```

JPQL 을 이용한 패치 조인은 기본적으로 inner join 을 이용하여 쿼리한다.

**3. @EntityGraph 사용**

> @EntityGraph 는 JPA 2.1 부터 지원되는 기능으로 이하 버전일 경우에는 패치 조인을 사용해야 한다.
```@EntityGraph(attributePaths = "category")```를 사용하여 같이 가져올 필드에 대해서 정의를 한다면 해당 필드의 데이터도 한번에 가져온다.

```java
public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(attributePaths = "category")
    @Query("select book from Book book")
    List<Book> findAllByQueryAndEntityGraphWithCategory();
}
```

> 결과값
```
    select
        book0_.id as id1_0_0_,
        category1_.id as id1_1_1_,
        book0_.category_id as category3_0_0_,
        book0_.title as title2_0_0_,
        category1_.name as name2_1_1_ 
    from
        book book0_ 
    left outer join
        category category1_ 
            on book0_.category_id=category1_.id
```

지금까지 OneToMany 의 경우에 대해서 알아봤다. 그럼 ManyToOne 일 경우에도 관련 테이블을
한번에 가져오는 경우에 대해서도 알아보자

```java
public class Category {
    @Id @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book){
        this.books.add(book);

        if(book.getCategory() != this){
            book.setCategory(this);
        }
    }
}
```
category에 book 과의 관계를 추가하였다. Category와 Book에 서로 관계조건이 있는 경우
양방향이라고 보기 보다는 서로다른 단방향 관계 2개이다.

이럴 경우에는 한쪽이 변경되면 다른 한쪽도 변경될 수 있도록 동기화 코드를 넣어줘야 한다.
(밑에 코드 참고)

```java

public class Category {
    
    ....
    
    public void addBook(Book book){
        this.books.add(book);

        if(book.getCategory() != this){
            book.setCategory(this);
        }
    }
}

public class Book {
    
    ....

    public void setCategory(Category category) {
        this.category = category;

        if(!this.category.getBooks().contains(this)){
            this.category.addBook(this);
        }
    }
}

```

자 이제 본론으로 들어가서 Category 에서 book에 대한 정보를 한번에 조회하기 위해서 위에서
배운 2가지 방법중에 한개를 선택할것이다.

> 1. @EntityGraph 사용. (left outer join)
> 2. JPGL의 fetch join 사용. (inner join)

일단 코드를 작성해서 반환 갯수에 대해서 확인해보자.

```java
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @EntityGraph(attributePaths = "books")
    @Query("select category from Category category")
    List<Category> findAllWithEntityGraph();

    @Query("select category from Category category join fetch category.books")
    List<Category> findAllWithFetchJoin();
}
```

```java

    @Test
    public void find_oneToMany_test(){
        Category category = new Category("IT");
        categoryRepository.save(category);
        Category category2 = new Category("자연");
        categoryRepository.save(category2);

        Book book1 = new Book("JPA 책", category);
        bookRepository.save(book1);
        Book book1_1 = new Book("JPA 책2", category);
        bookRepository.save(book1_1);
        Book book2 = new Book("자연관찰", category2);
        bookRepository.save(book2);
        Book book2_2 = new Book("자연관찰2", category2);
        bookRepository.save(book2_2);

        List<Category> allWithEntityGraph = categoryRepository.findAllWithEntityGraph();
        List<Category> allWithFetchJoin = categoryRepository.findAllWithFetchJoin();

        Assertions.assertThat(allWithEntityGraph.size()).isEqualTo(2);
        Assertions.assertThat(allWithFetchJoin.size()).isEqualTo(2);
    }
```

다음과 같이 테스트 코드를 작성했을 경우 테스트는 과연 성공할 것인가?

![image](https://user-images.githubusercontent.com/6028071/55692506-b58ee580-59e3-11e9-887c-77f8dd5c7892.png)

다버깅으로 갯수를 확인해본결과 Category를 2개 저장했지만 fetch join 을 이용했을때는
4개의 category 를 조회한다. 쿼리문만 봤을때는 둘다 4개의 결과물을 반환해야 할것 같지만
@EntityGraph는 내부적으로 중복된 데이터를 제거한다. 그럼 fetch join 을 했을 경우에는
어떤 처리방법이 있을까? 

```java
    @Query("select distinct category from Category category join fetch category.books")
    List<Category> findAllWithFetchJoin();
```
```distinct``` 예약어를 사용하여 중복된 데이터를 제거 할 수 있다.

> 결론

N+1 퀄리에 대해서는 예전부터 많이 발생했던 문제이다. 초반에 개발때는 속도이슈가 발생하지
않지만 운영을 할때는 이야기가 달라진다. 운영에서는 많은 데이터를 필요하기 때문에 그에 따른
속도 이슈는 생길 수 있다. JPA 가 느리다는 말은 이런 부분들에 대해서 잘 학습하지 못해
발생하기도 한다. 아직 배울부분이 많이 남은것 같다.










