package com.example.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ToDoController {

    @Autowired
    private ToDoRepository toDoRepository;

    @GetMapping("/todo")
    public ResponseEntity<ToDO> get(@RequestParam(value = "id") int id){

      ToDO newTodo = new ToDO();
      newTodo.setId(id);
      newTodo.setDescription("Buy");
      newTodo.setIsDone(true);

        return new ResponseEntity<ToDO>(newTodo, HttpStatus.OK);
    }



    @PostMapping("/todo")
    public ResponseEntity<ToDO> create(@RequestBody ToDO newTodo){
        //save todo in db
        toDoRepository.save(newTodo);
        return new ResponseEntity<ToDO>(newTodo, HttpStatus.OK);
    }

}
