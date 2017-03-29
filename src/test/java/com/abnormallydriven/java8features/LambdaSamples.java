package com.abnormallydriven.java8features;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaSamples {

    @Test
    public void sample1() {

        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from the anonymous inner class");
            }
        };

        myRunnable.run();

        Runnable myVerboseLambdaRunnable = () -> {
            System.out.println("Hello from a lambda");
        };
        Runnable myLambdaRunnable = () -> System.out.println("Hello from a lambda");


        myVerboseLambdaRunnable.run();
        myLambdaRunnable.run();

        Runnable myMethodReference = this::sayHello;

        myMethodReference.run();
    }

    private void sayHello() {
        System.out.println("Hello from a method reference");
    }


    public void sample2() {

        //A Supplier
        Supplier<String> stringSupplier = new Supplier<String>() {
            @Override
            public String get() {
                return "Hello World";
            }
        };

        //as a Lambda
        Supplier<String> lambdaSupplier = () -> "Hello World";

        //A Consumer
        Consumer<String> stringConsumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        //as a lambda
        Consumer<String> lambdaConsumer = s -> System.out.println(s);

        //A Predicate
        Predicate<String> stringPredicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return "Hello World".equals(s);
            }
        };

        //as a lambda
        Predicate<String> lambdaPredicate = s -> "Hello World".equals(s);

        //A Function
        Function<String, String> stringFunction =
                new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        return new StringBuilder(s).reverse().toString();
                    }
                };

        Function<String, String> lambdaFunction =
                s -> new StringBuilder(s).reverse().toString();

    }

    @Test
    public void simpleStreamSample(){
        List<String> wordList = new ArrayList<>();

        wordList.add("Hello");
        wordList.add("Stream");
        wordList.add("World");

        wordList.stream()
                .forEach(System.out::println);

    }

}
