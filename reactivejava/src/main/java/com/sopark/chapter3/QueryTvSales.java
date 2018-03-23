package com.sopark.chapter3;

import io.reactivex.Maybe;
import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;

public class QueryTvSales {

    /**
     * 1. 전체 매출 데이터를 입력함.
     * 2. 매출 데이터 중 TV 매출을 필터링함.
     * 3. TV 매출의 합을 구함.
     */
    public void run(){
        List<ProductSales> productSales = new ArrayList<>();
        productSales.add(new ProductSales("TV", 2500));
        productSales.add(new ProductSales("CAMERA", 300));
        productSales.add(new ProductSales("TV", 1600));
        productSales.add(new ProductSales("PHONE", 800));

        Maybe<Integer> maybe = Observable.fromIterable(productSales)
                .filter((productSales1) -> (productSales1.getName() == "TV"))
                .map(product -> product.getSale())
                .reduce((result1, result2) -> result1 + result2);

        maybe.subscribe(total -> System.out.println("TV Sales : " + total));

    }

    public static void main(String[] args) {
        QueryTvSales queryTvSales = new QueryTvSales();
        queryTvSales.run();
    }

    class ProductSales {
        private String name;
        private int sale;

        public ProductSales(String name, int sale) {
            this.name = name;
            this.sale = sale;
        }

        public String getName() {
            return name;
        }

        public int getSale() {
            return sale;
        }
    }
}
