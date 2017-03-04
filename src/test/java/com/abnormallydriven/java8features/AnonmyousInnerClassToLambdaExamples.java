package com.abnormallydriven.java8features;

import org.junit.Test;

public class AnonmyousInnerClassToLambdaExamples {


    @Test
    public void theOldAnonymousInnerClass(){

        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from the anonymous inner class");
            }
        };

        myRunnable.run();
    }

    @Test
    public void theLambdaVersion(){
        Runnable myVerboseLambdaRunnable = () -> { System.out.println("Hello from a lambda"); };
        Runnable myLambdaRunnable = () -> System.out.println("Hello from a lambda");


        myVerboseLambdaRunnable.run();
        myLambdaRunnable.run();
    }

    @Test
    public void theMethodReference(){
        Runnable myMethodReference = this::sayHello;

        myMethodReference.run();
    }

    private void sayHello(){
        System.out.println("Hello from a method reference");
    }
}
