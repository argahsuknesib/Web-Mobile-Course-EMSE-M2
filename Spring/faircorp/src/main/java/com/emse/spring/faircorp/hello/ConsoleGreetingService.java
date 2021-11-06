package com.emse.spring.faircorp.hello;

public class ConsoleGreetingService implements GreetingService{

    public void greet(String name){
        System.out.println("Hello, Spring!");
    }
    
}