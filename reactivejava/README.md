예제소스 : https://github.com/yudong80/reactivejava

RxJava 스터디 소스
====
## 1. 리액티브 프로그래밍 소개 

## 2. Observable 처음 만들기

2.2 Single 클래스 

- Single은 Observable의 특수한 형태이므로 Observable에서 변환할 수 있음.
- Observable 클래스는 데이터를 무한하게 발생할 수 있지만, Single 클래스는 오직 1개의 데이터만 발행하도록 한정됨
- 보통 결과가 유일한 서버 API를 호출할 때 유용하게 사용할 수 있음.
- 데이터 하나가 발행과 동시에 종료됨.
- onNext()와 onComple() 항수가 onSuccess() 함수로 통합
- Single 클래스의 라이프 사이클 함수는 onSuccess (T value) 함수와 onError() 항수로 구성됨.


2.2.1 just() 함수

CODE
~~~java
Single<String> source = Single.just("Hello Single")
source.subscribe(System.out::println)</code>
~~~

결과값
~~~
Hello Single
~~~


2.2.2 Observable에서 Single 클래스 사용

- Single은 Observable의 특수한 형태이므로 Observable에서 변환할 수 있습니다.
- 예제 SingleExample.java 참고


2.2.3 Single 클래스의 올바른 사용방법

- Observable 에서 Single 객체를 생성할 때 데이터 하나만 발생하도록 보장
- just() 함수에 여러개 값을 넣으면 에러

~~~java
// Error 발생
Single<String> source = Observable.just("hello single", "error")
    .single("default item");

source.subscribe(System.out::println);
~~~

~~~java
Exception in thread "main" io.reactivex.exceptions.OnErrorNotImplementedException: Sequence contains more than one element!
	at io.reactivex.internal.functions.Functions$OnErrorMissingConsumer.accept(Functions.java:704)
	at io.reactivex.internal.functions.Functions$OnErrorMissingConsumer.accept(Functions.java:701)
	at io.reactivex.internal.observers.ConsumerSingleObserver.onError(ConsumerSingleObserver.java:45)
	at io.reactivex.internal.operators.observable.ObservableSingleSingle$SingleElementObserver.onNext(ObservableSingleSingle.java:82)
	
~~~


2.3 Maybe 클래스

- Maybe 클래스는 RxJava2 에 처음 도입
- Single 클래스와 마찬가지로 최대 데이터 하나를 가질 수 있지만 데이터 발행 없이 바로 데이터 발생을 완료(Single 클래스는 1개완료, Maybe클래스는 0 혹은 1개 완료)할 수도 있습니다. 
- onComplete 이벤트가 추가된 형태


2.4 뜨거운 Observable
- 차가운 Observable (준비된 데이터를 처음부터 발행 - 게으른 접근법)

Observable 을 선언하고 just(), fromIterable() 항수를 호출해도 옵저버가 subscribe() 함수를 호출하여 구독하지 않으면 데이터를 발생하지 않습니다.

ex) 웹 요청, 데이터베이스 쿼리와 파일 읽기등

- 뜨거운 Observable (구독한 시점부터 발행한 값을 받음)

구독자가 존재 여부와 관련없이 데이터를 발생하는 Observable. 여러구독자를 고려할 수 있고, 구독자로서는 Observable에서 발생하는 데이터를 처음부터 모두 수신할 것으로 보장할 수 없음. 하지만 뜨거운 Observable은 구독한 시점부터 Observable에서 발생한 값을 받음.
뜨거운 Observable에는 배압을 고려해야 합니다. 배압은 Observable에서 데이터를 발생하는 속도와 구독자가 처리하는 속도의 차이가 클때 발생합니다.

ex) 마우스 이벤트, 키보드 이벤트, 시스템 이벤트등


2.5 Subject 클래스

- 차가운 Observable을 뜨거운 Observable로 바꿈.
- Observable의 속성과 구독자의 속성이 모두 있음. (Observable 처럼 데이터를 발생할 수도 있고 구독자처럼 발생된 데이터를 바로 처리할 수도 있음)


2.5.1 AsyncSubject 클래스
 
- Observable 에서 발행한 마지막 데이터를 얻어올 수 있는 Subject 클래스. 완료되기 전 마지막 데이터에만 관심이 있으며 이전 데이터는 무시


2.5.2 BehaviorSubject 클래스

- 구독자가 구독을 하면 가장 최근 값 혹은 기본값을 넘겨주는 클래스

2.5.3 PublishSubject 클래스

- 구독자가 subscripbe() 함수를 호출하면 값을 발행
- 해당 시간에 발생한 데이터를 구독자에게 전달


2.5.4 ReplaySubject 클래스

- Subject 클래스의 목적은 뜨거운 Observable을 활용하는 것인데 차가운 Observable 처럼 동작
- ReplaySubject 클래스는 구독자가 새로 생기면 항상 데이터의 처음부터 끝까지 발행하는 것을 보장


2.6 ConnectableObservable 클래스

- Subject 클래스처럼 차가운 Observable을 뜨거운 Observable 로 변환
- subscribe()함수를 호출해도 아무 동작이 일어나지 않음. 새로 추가된 connect() 를 호출하는 시점부터 subscribe() 함수를 호출한 구독자에게 데이터를 발행.
    
## 3. 리액티브 연산자 입문

3.1 map() 함수

- map() 함수는 입력값을 어떤 함수에 넣어서 원하는 값으로 변환하는 함수
- 객체지향 프로그래밍과 다른 점은 '어떤 함수에 넣어서' 입니다.
- map() 함수는 입력 데이터와 그것을 변환해줄 함수를 이어주는 중개업자가 있다고 생각하면 됨.

|인터페이스 이름|포함메서드|설명|
|---|---|---|
|Predicate\<T> | boolean test(T t)| t 값을 받아서 참이나 거짓을 반환합니다.|
|Consumer\<T> |void accept(T t)|t 값을 받아서 처리합니다. 반환값은 없습니다.|
|Function<T,R>|R apply(T t)| t 값을 받아서 결과를 반환합니다.|
 
 - Function 인터페이스는 제네릭 타입 T를 인자로 전달받아 제네릭 타입 R을 반환

3.2 flatMap() 함수

- map() 함수가 일대일 함수라면 flatMap() 함수는 일대다 혹은 일대일 Observable 함수 입니다.
- 즉, map()은 input 값이 1개일 경우 1개를 반환하지만, flatMap() 함수는 input 값이 1개일경우 여러개의 반환값을 만들 수 있음.

3.3 filter() 함수

- filter() 함수는 Observable 에서 원하는 데이터만 걸러내는 역활을 함.
- filter() 함수에는 boolean 값을 리턴하는 함수형 인터페이스인 Predicate를 인자로 넣습니다.(map() 과 flatMap() 하ㅣㅁ수에서 Function 객체를 넣은 것과 다릅니다.)
- filter 함수와 비슷한 함수들
   - first(default) 함수 : Observable의 첫 번째 값을 필터함. 만약 값없이 완료되면 대신 기본값을 리턴함.
   - last(default) 함수 : Observable의 마지막 값을 필터함. 만약 값없이 완료되면 대신 기본값을 리턴함.
   - take(N) 함수 : 최초 N개 값만 가져옴.
   - takeLast(N) 함수 : 마지막 N개 값만 필터함.
   - skip(N) 함수 : 최초 N개 값을 건너뜀.
   - skipLast(N) 함수 : 마지막 N 개 값을 건너뜀.
   
3.4 reduce() 함수

- reduce() 함수는 발생한 데이터를 모두 사용하여 어떤 최종 결과 데이터를 합성할 때 활용합니다.
- reduce 는 Maybe 나 Single 을 리턴함.
- reduce() 함수를 호출하면 인자로 넘긴 람다 표현식에 의해 결과 없이 완료될수도 있어서 Maybe 사용

3.4.1 데이터 쿼리 하기

- 

## 4. 리액티브 연산자의 활용

- 생성 연산자 : Observable 로 데이터 흐름을 만듬
- 변환 연산자와 필터 연산자 : 데이터 흐름을 내가 원하는 방식으로 변형
- 결합 연산자 : 1개의 Observable이 아니라 여러 개의 Observable 을 조합할 수 있도록 해줌.

리액티브 연산자(함수) 분류

|연산자 종류|소개 장|연산자 함수|
|---|---|---| 
|생성 연산자|2,4 장|just(), fromXXX(), create(), interval(), range(), timer(), intervalRange(), defer(), repeat()|
|변환 연산자|3,4,7 장|map(), flatMap(), concatMap(), switchMap(), groupBy(), scan(), buffer(), window()|
|필터 연산자|3 장|flter(), take(), skip(), distinct()|
|결합 연산자|4 장|zip(), combineLastest(), merge(), concat()|
|조건 연산자|4 장|amb(), takeUntil(), skipUntil(), all()|
|에러 처리 연산자|7 장|onErrorReturn(), onErrorResumeNext(), retry(), retryUntil()|
|기타 연산자|2,3,5 장|subscribe(), subscribeOn(), observeOn(), reduce(), count()|

4.1 생성 연산자

- 생성 연ㅅ나자의 역할은 데이터 흐름을 만드는 것.
- 간단하게 Observalbe(Observable, Single, Maybe 객체등) 을 만든다고 생각하면 됩니다.

4.1.1 interval() 함수

- 스케줄러 있음 (별도 쓰레드)
- 일정 시간 간격으로 데이터 흐름을 생성
- 계산 스케줄러에서 실행됨. (현재 스레드가 아니라 계산을 위한 별도의 스레드에서 동작)
- interval() 함수는 기본적으로 영원히 지속 실행되기 때문에 폴링 용도로 많이 사용.

4.1.2 timer() 함수

- 스케줄러 있음 (별도 쓰레드)
- timer()는 interval() 함수와 유사하지만 한 번만 실행하는 함수 입니다. 일정 시간이 지난 후에 한 개의 데이터를 발행하고 onComplete() 이벤트가 발생합니다.

4.1.3 range() 함수

- 스케줄러 없음
- range() 함수는 주어진 값(n) 부터 m개의 Interger 객체를 발행합니다.

4.1.4 intervalRange() 함수

- 별도 쓰레드
- intervalRange() 함수는 interval() 과 range() 를 혼합해놓은 함수
- interval() 함수 처럼 일정한 시간 간격으로 값을 출력하지만 range() 함수처럼 시작 숫자 (n) 로 부터 m개마늠의 값만 생성하고 onComplete 이벤트가 발생합니다.

4.1.5 defer() 함수

- 스케줄러가 없기 때문에 현재 스레드에서 실행되며 인자로는 Callable<Observable<T>> 를 받음
- defer() 함수는 timer() 함수와 비슷하지만 데이터 흐름 생성을 구독자가 subscribe() 함수를 호출할 때까지 미룰수 있음
- defer() 함수를 활용하면 Subscribe() 함수를 호출할 때의 상황을 반영하여 데이터 흐름의 생성을 지연하는 효과를 보여줌
- 내부적으로 구독자가 subscribe()를 호출하면 그때 supplier의 call() 메서드를 호출

4.1.6 repeat() 함수

- 단순 반복 실행
- 사용예 : 서버와 통신을 하면 해당 서버가 잘 살아 있는지 확인
- timer() + repeat() = interval() 함수로 대체 가능

4.2 변화 연산자

4.2.1 concatMap() 함수

- concatMap() 함수는 flatMap() 함수와 매우 비슷합니다. flatMap() 는 먼저 들어온 데이터를 처리하는 도중에 새로운 데이터가 들어오면 나중에 들어온 데이터의 처리 결과가 먼저 출력될 수도 있습니다. 이를 인터리빙(끼어들기)라고 합니다.
- 하지만 concatMap() 함수는 먼저 들어온 데이터 순서대로 처리해서 결과를 낼 수 있도록 보장

4.2.2 switchMap() 함수

- concatMap() 함수가 인터리빙이 발생할 수 있는 상황에서 동작의 순서를 보장해준다면 switchMap() **함수는 순서를 보장하기 위해 기존에 진행 중이던 작업을 바로 중단합니다.
- 그리고 여러 개의 값이 발행되었을때 마지막에 들어온 값만 처리하고 싶을 때 사용합니다.
- 중간에 끊기더라도 마지막 데이터의 처리는 보장하기 때문
- 시간이 겹쳐지지 않음

4.2.3 groupBy() 함수

- 단일 Observable을 여러개로 이루어진 Observable 그룹으로 만듬

~~~
함수 비교

- map() : 1개의 데이터를 다른 값이나 다른 타입으로 변환
- flatMap() : 1개의 값을 받아서 여러 개의 데이터(Observable)로 확장해줌
- groupBy() 함수는 값들을 받아서 어떤 기준에 맞는 새로운 Observable 다수를 생성
~~~ 

4.2.4 scan() 함수

- scan() 함수는 reduce() 와 비슷
- reduce() 함수는 Observable에서 모든 데이터가 입력된 후 그것을 종합하여 마지막 1개의 데이터만을 구독자에게 발행하는 반면 scan() 함수는 실행할 때마다 입력값에 맞는 중간 결과 및 최종 결과를 구독자에게 발행
- reduce() 의 경우 MayBe 타입을 리턴하지만 Observable 타입을 리턴
- reduce() 의 경우 마지막 값이 입력되지 않거나 onComplete 이벤트가 발생하지 않으면 구독자에게 값을 발행하지 않습니다. 최악의 경우에는 값을 전혀 발행하지 않고 종료할 수 도 있으므로 Maybe 클래스 타입으로 정의합니다. 반면 scan() 함수는 값이 입력될 때마다 구독자에게 값을 발행합니다. 따라서 Maybe가 아니라 Observable 입니다.

4.3 결합 연산자

- flatMap() 함수나 groupBy() 함수등은 1개의 Observable 을 확장해주는 반면 결합 연산자들은 **여러개의 Observable 을 내가 원하는 Observable 로 결합** 

4.3.1 zip() 함수

- 각각의 Observable을 모두 활용해 2개 혹은 그 이상의 Observable을 결합
- A, B 두개의 Observable을 결합한다면 2개의 **Observable에서 모두 데이터를 발행해야 결합**할 수 있음. 그전까지는 발행을 기다림.
- Observalble 의 갯수가 다르다면 적은쪽의 Observable갯수를 기준으로 처리함.
- 마지막 파라미터에 어떻게 묶을지에 대한 함수 정의 (BiFunction 정의)
- zipWith 함수는 zip 으로 만들어진 Observable 에 대해서 추가적으로 Observable 을 추가하여 zip 함수를 호출할 때 사용.

> RxJava를 사용하기 좋은 경우는 '비동기 프로그램'입니다. 특히 서버와 통신하는 비동기 로직을 만들 때 좋은 효과를 볼 수 있습니다.

4.3.2 combineLastes() 함수

- 2개 이상의 Observable을 기반으로 Observable 각각의 값이 변경되었을 때 갱신해주는 함수.
- 2개 이상의 Observable이 있을때 두 Observable 모두 값을 발행하면 그때 결과값이 호출됨. **그다음부터는 둘중 어떤 것이 갱신되면 최신 결과값을 보여줌.**

4.3.3 merge() 함수

- 가장 단순한 결합 함수
- 입력 Observable의 순서와 모든 Observable이 데이터를 발행하는지 등에 관여하지 않고 어느 것이든 업스트림에서 **먼저 입력되는 데이터를 그대로 발행**
- zip() 은 모든 Observable 이 발행되어야지 실행되지만 merge 의 경우는 Observable 이 발행되는 순간 마다 실행됨.

4.3.4 concat() 함수

- concat() 은 2개 이상의 Observable 을 이어 붙여주는 함수
- 첫번째 Observable 에 OnComplete 이벤트가 발생해야 두번째 Observable을 구독
- 첫번째 Observable 에  onComplete 이벤트가 발생하지 않게 되면 두번째 Observable은 영원히 대기. 이는 잠재적인 메모리 누수의 위험을 내포함.
- concat() 함수를 활용할 때는 onComplete 이벤트의 발생 여부 확인이 중요. (doOnComplete 메소드를 통해 확인)

4.4 조건 연산자

- 조건 연산자는 Observable의 흐름을 제어하는 역할 
- 필터 연산자가 발행된 값을 채택하느냐 기각하느냐 여부에 초점을 맞춘다면, 조건 연산자는 지금까지의 흐름을 어떻게 제어할 것인지에 초점을 맞춤

~~~

- amb() 함수 : 둘중 어느것이든 먼저 나온 Observable을 채택
- takeUntil(other) 함수 : other Observable 에서 데이터가 발행되기 전까지만 현재 Observable을 채택
- skipUntil(other) 함수 : takeUntil(other) 함수와는 반대로 other Observable 에서 데이터가 발행될 동안 현재 Observable에서 발행하는 값을 무시
- all() 함수 : Observable에 입력된 값이 모두 특정 조건에 맞을 때만 true 를 발행. 만약 조건이 맞지 않으면 바로 false 발행  

~~~ 

4.4.1 amb() 함수

- amb 는 ambiguous (모호한) 라는 영어 단어의 줄임말.
- **여러 개의 Observable 중에서 1개의 Observable을 선택하는데, 선택 기준은 가장 먼저 데이터를 발행하는 Observable** 입니다. 이후에 나머지 Observable에서 발행하는 데이터는 모두 무시
- List Observable 을 파라미터로 받음

4.4.2 takeUntil() 함수

- takeUntil() 함수는 take() 함수에 조건을 설정
- take() 함수처럼 일정 개수만 값을 발행하되 완료 기준을 다른 Observable 에서 값을 발행하는지로 판단.

4.4.3 skipUntil() 함수

- skipUntil() 함수는 takeUntil() 과 정반대의 함수 
- other Observable을 인자로 받는다는 점은 같지만 Observable 에서 데이터를 발행할 떄까지 값을 건너 뜁니다.

4.4.4 all() 함수

- 주어진 조건에 100% 맞을 때만 true 값을 발행하고 조건에 맞지 않는 데이터가 발행되면 바로 false 값을 발행

4.5 수학 및 기타 연산자

4.5.1 수학 함수

4.5.2 delay() 함수

- delay() 함수는 시간을 인자로 받아 Observable 의 데이터 발행을 지연시켜주는 역할
- interval() 함수와 마찬가지로 계산 스케줄러에서 실행 (별도의 쓰레드에서 실행)





 
 
 
 
 
 
 
 