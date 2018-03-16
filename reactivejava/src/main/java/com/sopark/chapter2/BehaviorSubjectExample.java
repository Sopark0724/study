package com.sopark.chapter2;

import com.sopark.util.Printer;
import io.reactivex.subjects.BehaviorSubject;

public class BehaviorSubjectExample {

    public void marbleDiagram(){
        Printer.print();

        BehaviorSubject<String> subject = BehaviorSubject.createDefault("6");
        subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
        subject.onNext("1");
        subject.onNext("3");
        subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
        subject.onNext("5");
        subject.onComplete();
    }

    public static void main(String[] args) {
        BehaviorSubjectExample behaviorSubjectExample = new BehaviorSubjectExample();
        behaviorSubjectExample.marbleDiagram();
    }
}
