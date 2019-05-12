## 생성자 대신 정적 팩터리 메소드를 고려하라

- 장점
  - 이름을 가질 수 있다.
  - 호출될 때마다 인승턴스를 새로 생성하지 않아도 된다.
  - 탄환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있다. (자바8 부터 정적 메소드를 인터페이스가 가질 수 있음. public 이여야함.)
  - 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.
  - 정적 팩터리 메소드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.
- 단점
  - 상속을 하려면 public 이나 protected 생성자가 필요하니 정적 팩터리 메소드만 제공하면 하위 클래스를 만들 수 없다.
  - 정적 팩터리 메소드는 프로그래머가 찾기 어렵다.
- 정적 팩터리 메소드의 사영하는 명명방식

| 이름                      | 설명                                                         |
| ------------------------- | ------------------------------------------------------------ |
| from                      | 매개변수를 하나 받아서 해당 타입의 인스턴스를 반환하는 형변환 메소드 ex) ``` Date d = Date.from(instance); ``` |
| of                        | 여러 매개변수를 받아 적합한 타입의 인스턴스를 반환하는 집계 메소드 ex) ``` Set<Rank> faceCards = EnumSet.of(JACK, QUEEN, KING); ``` |
| valueOf                   | from과 of의 더 자세한 버전 ex) ```BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE); ``` |
| instance 혹은 getInstance | 매개변수로 명시한 인스턴스를 반환하지만, 같은 인스턴스임을 보장하지는 않는다. Ex) ``` StackWalker luke = STackWalker.getInstance(options);``` |
| create 혹은 newInstance   | instance 혹은 getInstance 와 같지만, 매번 새로운 인스턴스를 생성해 반환함을 보장한다. Ex) ``` Object newArray = Array.newInstance(classObject, arrayLen) ``` |
| getType                   | getInstance와 같으나, 생성할 클래스가 아닌 다른 클래스에 팩터리 메소드를 정의할 때 쓴다. "Type"은 팩터리 메소드가 반환할 객체의 타입이다. ex) ``` FileStore fs = Files.getFileStore(path); |
| newType                   | newInstance와 같으나, 생성할 클래스가 아닌 다른 클래스에 팩터리 메소드를 정의할 때 쓴다. "Type"은 팩터리 메소드가 반환할 객체의 타입이다. Ex) ``` BufferedReader br = Files.newBufferedReader(path); ``` |
| type                      | getType과 newType의 간결한 버전 ex) ``` List<Complaint> litany = Collections,list(legacyLitany); ``` |



## 생성자에 매개변수가 많다면 빌더를 고려하라.

- 매개변수가 많을 경우 
  - 점층적 생성자 패턴 : 점층적 생성자 패턴도 쓸 수는 있지만, 매개변수 개수가 많아지면 클라이언트 코드를 작성하거나 읽기 어렵다.
  - 자바빈즈 : 자바빈즈. 패턴으로 변수를 설정할 수 있다. 단점으로 자바빈즈 패턴에서는 객체 하나를 만들려면 메소드를 여러 개 호출해야. 하고, 객체가 완전히 생성되기 전까지는 일관성이 무너질 수도 있다. (**불변 객체를 만들수 없고 스레드 안전성을 얻기 위해 별도의 코딩이 필요.**)
  - 빌더패턴
    - 장점
      - 유연하다.
      - 불변객체를 만들 수 있다. (쓰레드에 안전)
      - 가변인수로 매개변수를 여러개 추가 가능
    - 단점
      - 빌더 생성 비용이 발생 (코드량. but 롬복을 사용하면됨.)

## private 생성자나 열거 타입으로 싱글턴임을 보증하라.

생성자는 private 로 감추고, 유일한 인스턴스에 접근할 수 있는 수단으로 public static apaqjfmf gksk akfusgoensek. 

- final 필드 방식

```java
public class Elvis {
  public static final Elvis INSTANCE = new Elvis();
  private Elvis() {...}
  public void leaveTheBUilding() { ... }
}
```



















