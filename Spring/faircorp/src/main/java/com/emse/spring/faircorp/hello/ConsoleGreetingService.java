package com.emse.spring.faircorp.hello;

import com.emse.spring.faircorp.GreetingService;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class ConsoleGreetingService implements GreetingService {

    @Override
    public void greet(String name) {
        System.out.println("Hello, " + name + "!");
                
    }
        
}
