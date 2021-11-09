package com.emse.spring.faircorp.hello;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class DummyUserService implements UserService {

    @Override
    public void greetAll() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("Elodie");
        list.add("Charles");   

        for(String element : list) {
            System.out.println("Hello, "+element + "!");
        }
    }
    
}
