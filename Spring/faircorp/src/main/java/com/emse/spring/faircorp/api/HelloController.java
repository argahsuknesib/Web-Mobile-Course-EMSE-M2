package com.emse.spring.faircorp.api;

import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/hello")
@Transactional
public class HelloController {
    
    @GetMapping(value = "/{name}")
    public MessageDto welcome(@PathVariable String name){
        return new MessageDto("Hello " + name);
    }

    class MessageDto {
        String message;

        public MessageDto(String message){
            this.message = message;
        }

        public String getMessage(){
            return message;
        }
    }
}
