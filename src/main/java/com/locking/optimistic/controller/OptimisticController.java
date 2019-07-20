package com.locking.optimistic.controller;

import com.locking.optimistic.model.Person;
import com.locking.optimistic.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
public class OptimisticController {

    @Autowired
    private PersonService personService;

    @GetMapping("/init")
    public String init(){
        return "app started.";
    }

    @GetMapping("/getAll")
    public List<Person> getAll(){
        return personService.getAll();
    }

    @PostMapping("/save")
    public void save(@RequestBody Person person){
        personService.save(person);
    }

    @GetMapping("/edit")
    public void edit(){

        try {
            while (true) {
                Optional<Person> personOptional = personService.getById(1);
                Person person = personOptional.get();

                Thread thread1 = new Thread(() -> {
                    UUID uuid = UUID.randomUUID();
                    person.setFirstName(uuid.toString().substring(0, 4));
                    person.setLastName(uuid.toString().substring(0, 4));
                    personService.save(person);
                });

                Thread thread2 = new Thread(() -> {
                    UUID uuid = UUID.randomUUID();
                    person.setFirstName(uuid.toString().substring(0, 4));
                    person.setLastName(uuid.toString().substring(0, 4));
                    personService.save(person);
                });

                thread1.start();
                thread2.start();
            }
        }catch (Exception e){
//            log.error("changing already modified object : {}", person);
            e.printStackTrace();
        }
    }
}
