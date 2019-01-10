package com.sopark.book.chapter2;

import io.reactivex.subjects.ReplaySubject;

public class ReplaySubjectExample {
    public void marbleDiagram(){
        ReplaySubject<String> subject = ReplaySubject.create();
        subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
        subject.onNext("1");
        subject.onNext("3");
        subject.onNext("5");
        subject.onComplete();
        subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
    }

    public static void main(String[] args) {
        ReplaySubjectExample replaySubjectExample = new ReplaySubjectExample();
        replaySubjectExample.marbleDiagram();
    }
}
