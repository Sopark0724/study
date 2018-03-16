package com.sopark.chapter2;

import io.reactivex.Observable;
import io.reactivex.subjects.AsyncSubject;

/**
 *
 */

public class AsyncSubjectExample {
    public void marbleDiagram(){
        System.out.println("=================== " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ===================");
        AsyncSubject<String> subject = AsyncSubject.create();

        subject.onNext("1");
        subject.subscribe(data -> System.out.println("Subscriber # 1 => " + data ));
        subject.onNext("3");
        subject.subscribe(data -> System.out.println("Subscriber # 2 => " + data ));
        subject.onNext("5");

        // complete 실행전 마지막 onNext의 값이 실행됨.
        subject.onComplete();
    }

    public void asSubscriber(){
        System.out.println("=================== " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ===================");
        Float[] temperature = {10.1f, 13.4f, 12.5f};
        Observable<Float> source = Observable.fromArray(temperature);

        AsyncSubject<Float> subject = AsyncSubject.create();
        subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));

        // subject 를 통해 source를 구독
        source.subscribe(subject);
    }

    public void afterComplete(){
        System.out.println("=================== " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ===================");

        AsyncSubject<Integer> subject = AsyncSubject.create();
        subject.onNext(10);
        subject.onNext(11);
        subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
        subject.onNext(12);
        subject.onComplete();   // 완료된 이후의 onNext 는 무시
        subject.onNext(13);
        subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
        subject.subscribe(data -> System.out.println("Subscriber #3 => " + data));
    }

    public static void main(String[] args) {
        AsyncSubjectExample asyncSubjectExample = new AsyncSubjectExample();
//        asyncSubjectExample.marbleDiagram();
//        asyncSubjectExample.asSubscriber();
        asyncSubjectExample.afterComplete();
    }
}
