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
    

