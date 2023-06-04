package com.example.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ToDoController {

    @Autowired
    private ToDoRepository toDoRepository;

    @GetMapping("/todo")
    public ResponseEntity<ToDO> get(@RequestParam(value = "id") int id){
       //mock implementation
      // ToDO newTodo = new ToDO();
     // newTodo.setId(id);
     // newTodo.setDescription("Buy");
      //newTodo.setIsDone(true);

        //get todo from db by id
        Optional<ToDO> toDoInDB = toDoRepository.findById(id);

        if(toDoInDB.isPresent()){
            return new ResponseEntity<ToDO>(toDoInDB.get(), HttpStatus.OK);
        }

        return new ResponseEntity("No toDo found with id " + id, HttpStatus.NOT_FOUND);
    }



    @PostMapping("/todo")
    public ResponseEntity<ToDO> create(@RequestBody ToDO newTodo){
        //save todo in db
        toDoRepository.save(newTodo);
        return new ResponseEntity<ToDO>(newTodo, HttpStatus.OK);
    }

}
