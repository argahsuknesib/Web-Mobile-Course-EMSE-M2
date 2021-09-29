package com.example.fcc.fccspringvideo.dao;

import com.example.fcc.fccspringvideo.model.Person;

public class FakePersonDataAccessService implements PersonDAO{
    
    private static List<Person> DB = new ArrayList<>();


    @Override
    public int insertPerson(UUID id, Person person){
        DB.add(new Person(id, person.getName()));
        return 1;
    }
}
