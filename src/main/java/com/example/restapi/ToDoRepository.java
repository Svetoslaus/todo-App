package com.example.restapi;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;


public interface ToDoRepository extends CrudRepository<ToDO, Integer> {


    Set<ToDO> findAllByUserId(int userId);



}
