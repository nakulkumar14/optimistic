package com.locking.optimistic.service;

import com.locking.optimistic.dao.PersonRepository;
import com.locking.optimistic.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAll(){
        return (List<Person>) personRepository.findAll();
    }

    public Optional<Person> getById(int id){
        return personRepository.findById(id);
    }

    public void save(Person person){
        log.info("Person saved : ", person);
        Person save = personRepository.save(person);
        log.info("Person saved : ", save);
    }
}
