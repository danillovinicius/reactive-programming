package com.dvlima.rxjava;

import rx.Observable;
import rx.observables.GroupedObservable;

import java.util.Arrays;
import java.util.List;

public class ObservableExamples {

    private static Integer[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i"};
    private static String[] titles = {"title"};
    private static List<String> titleList = Arrays.asList(titles);

    static Observable<String> getTitle() {
        return Observable.from(titleList);
    }

    public static void main(String[] args) {

        print("Just");
        Observable<String> just = Observable
                .just("Hello");
        just.subscribe(
                System.out::println,                        //  onNext
                Throwable::printStackTrace,                 //  onError
                () -> System.out.println("onCompleted")     //  onCompleted
        );

        print("Map");
        Observable<String> map = Observable
                .from(letters)
                .map(String::toUpperCase);
        map.subscribe(System.out::println);


        print("FlatMap");
        Observable<String> flatMap = Observable
                .just("book1", "book2")
                .flatMap(s -> getTitle());
        flatMap.subscribe(System.out::println);


        print("Scan");
        Observable<StringBuilder> scan = Observable
                .from(letters)
                .scan(new StringBuilder(), StringBuilder::append);
        scan.subscribe(System.out::println);


        print("GroubBy");
        Observable<GroupedObservable<String, Integer>> groupBy = Observable
                .from(numbers)
                .groupBy(i -> 0 == (i % 2) ? "EVEN" : "ODD");
        groupBy.subscribe((group) -> group.subscribe((number) -> {
            System.out.println(group.getKey() + " : " + number);
        }));


        print("Filter");
        Observable<Integer> filter = Observable
                .from(numbers)
                .filter(i -> (i % 2 == 1));
        filter.subscribe(System.out::println);


        print("DefaultIfEmpty");
        Observable.empty()
                .defaultIfEmpty("Observable is empty")
                .subscribe(System.out::println);


        print("DefaultIfEmpty First");
        Observable.from(letters)
                .defaultIfEmpty("Observable is empty")
                .first()
                .subscribe(System.out::println);


        print("TakeWhile");
        Observable.from(numbers)
                .takeWhile(i -> i < 5)
                .subscribe(System.out::println);
    }

    private static void print(String text) {
        String token = "=".repeat(5);
        System.out.println(String.format("\n \t %s %s %s", token, text, token));
    }

}
