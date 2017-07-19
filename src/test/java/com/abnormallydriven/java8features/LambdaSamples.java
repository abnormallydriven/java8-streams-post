package com.abnormallydriven.java8features;

import org.junit.Test;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Test
    public void noTerminatingStreamStep(){
        Integer[] numbers = new Integer[]{1,2,3,4};
        List<Integer> evenNumbers = new ArrayList<>();
        Stream.of(numbers)
                .filter(integer -> integer % 2 == 0)
                .peek(evenNumbers::add);

        System.out.println(evenNumbers.size());//will print 0
    }

    @Test
    public void aSimpleMappingOperation(){
        Integer[] numbers = new Integer[]{1,2,3,4};
        List<String> stringRepresentation = new ArrayList<>();
        Stream.of(numbers)
                .map(integer -> String.valueOf(integer))
                .forEach(stringRepresentation::add);

        System.out.println(stringRepresentation.size());//will print 4

    }

    @Test
    public void aFlatmappingOperation(){

        Integer[] even = new Integer[]{2,4,6};
        Integer[] odd = new Integer[]{1,3,5};

        List<Integer> allNumbers = new ArrayList<>();

        Stream.of(even,odd)
                .flatMap(integers -> Arrays.stream(integers))
                .forEach(allNumbers::add);


        System.out.println(allNumbers.size());//will print 6

    }


    @Test
    public void setCollectorExample(){
        Integer[] numbers = new Integer[]{1,1,2,2,3,3,4,4,5,5,6,6};

        Set<Integer> uniqueIntegers = Arrays.stream(numbers)
                .filter(integer -> integer % 2 == 0)
                .collect(Collectors.toSet());

    }

    @Test
    public void mapCollectorExample(){
        Car[] cars = new Car[]{
                new Car("Chevrolet", "Cavalier"),
                new Car("Mazda", "3"),
                new Car("Ford", "F150"),
                new Car("Chevrolet", "Volt")
        };

        Map<String, List<Car>> makeToCarsMap = Arrays.stream(cars)
                .collect(Collectors.groupingBy(Car::getMake));

        System.out.println(makeToCarsMap.get("Chevrolet").size());//will print 2

    }

    @Test
    public void anAggregationExample(){
        Integer[] numbers = new Integer[]{1,2,3,4,5};

        Integer sumOfIntegers = Arrays.stream(numbers)
                .reduce(0, (first, second) -> first + second);

        //will print 15
        System.out.println(sumOfIntegers);


    }

    @Test
    public void aggregationWithOptional(){

        Integer[] numbers = new Integer[]{1,2,3};
        Integer[] emptyArray = new Integer[0];

        BinaryOperator<Integer> biFunction = Math::max;
        Optional<Integer> maxOfNumbers = Arrays.stream(numbers)
                .reduce(biFunction);

        //will print 3
        System.out.println(maxOfNumbers.get());

        Optional<Integer> emptyMax = Arrays.stream(emptyArray)
                .reduce(biFunction);

        //will print false
        System.out.println(emptyMax.isPresent());

    }

    static class Car {

        private final String make;
        private final String model;

        public Car(String make, String model){
            this.make = make;
            this.model = model;
        }

        public String getMake() {
            return make;
        }

        public String getModel() {
            return model;
        }
    }


}
