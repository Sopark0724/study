package com.sopark.chapter2;

import com.sopark.common.Order;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Observable에서 Single 클래스 사용.
 * Observable => Single 객체로 변환
 */
public class SingleExample {

    public void emit(){
        // 1. 기존 Observable에서 Single 객체로 변환하기
        Observable<String> source = Observable.just("Hello Single");
        Single.fromObservable(source)
                .subscribe(System.out::println);

        // 2. single() 함수를 통해 Single 객체 생성하기
        Observable.just("Hello Single")
                .single("default item")
                .subscribe(System.out::println);

        // 3. first() 함수를 호출해 Single 객체 생성하기
        String[] colors = {"Red", "Blue", "Gold"};
        Observable.fromArray(colors)
                .first("default value")
                .subscribe(System.out::println);

        // 4. empty Observable에서 Single 객체 생성하기
        Observable.empty()
                .single("default value")
                .subscribe(System.out::println);

        // 5. take() 함수에서 Single 객체 생성하기
        Observable.just(new Order("ORD-1"), new Order("ORD-2"))
                .take(1)
                .single(new Order("default order"))
                .subscribe(System.out::println);
    }

    public static void main(String[] args) {
        SingleExample singleExample = new SingleExample();
        singleExample.emit();
    }
}
