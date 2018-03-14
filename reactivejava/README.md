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
- 차가운 Observable (게으른 접근법)
 
Observable 을 선언하고 just(), fromIterable() 항수를 호출해도 옵저버가 subscribe() 함수를 호출하여 구독하지 않으면 데이터를 발생하지 않습니다.
### 
 



    

