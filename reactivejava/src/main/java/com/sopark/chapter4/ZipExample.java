package com.sopark.chapter4;

import com.sopark.common.Log;
import com.sopark.common.Shape;
import io.reactivex.Observable;

public class ZipExample {

    public void marbleDiagram(){
        String[] shapes = {"BALL", "PENTAGON", "STAR"};
        String[] coloredTriangles = {"2-T", "6-T", "4-T"};

        Observable<String> source = Observable.zip(
                Observable.fromArray(shapes).map(Shape::getSuffix),
                Observable.fromArray(coloredTriangles).map(Shape::getColor),
                (suffix, color) -> color + suffix
        );

        source.subscribe(Log::i);
    }

    public static void main(String[] args) {
        ZipExample zipExample = new ZipExample();
        zipExample.marbleDiagram();
    }
}
