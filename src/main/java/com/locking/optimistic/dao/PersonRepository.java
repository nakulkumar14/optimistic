package com.locking.optimistic.dao;

import com.locking.optimistic.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {
}
