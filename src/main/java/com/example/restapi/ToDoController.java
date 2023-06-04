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

    @GetMapping("/todo/all")
    public ResponseEntity<Iterable<ToDO>> getAll(){
        Iterable<ToDO> getAllTodosInDB = toDoRepository.findAll();
        return new ResponseEntity<Iterable<ToDO>>(getAllTodosInDB, HttpStatus.OK);
    }

    @PostMapping("/todo")
    public ResponseEntity<ToDO> create(@RequestBody ToDO newTodo){
        //save todo in db
        toDoRepository.save(newTodo);
        return new ResponseEntity<ToDO>(newTodo, HttpStatus.OK);
    }

    @DeleteMapping("/todo")
    public ResponseEntity delete(@RequestParam(value = "id") int id){

        Optional<ToDO> toDoInDB = toDoRepository.findById(id);

        if(toDoInDB.isPresent()){
            toDoRepository.deleteById(id);
            return new ResponseEntity("ToDo deleted", HttpStatus.OK);
        }

        return new ResponseEntity("There is no object to delete with id " + id, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/todo")
    public ResponseEntity<ToDO> edit(@RequestBody ToDO editedToDo){

        Optional<ToDO> toDoInDB = toDoRepository.findById(editedToDo.getId());
        if(toDoInDB.isPresent()){
            ToDO savedToDo = toDoRepository.save(editedToDo);
            return new ResponseEntity<ToDO>(savedToDo, HttpStatus.OK);
        }

        return new ResponseEntity("No object to update found with id " + editedToDo.getId(), HttpStatus.OK);
    }

    @PatchMapping("/todo/setDone")
    public ResponseEntity<ToDO> setDone(@RequestParam(value="isDone") boolean isDone,
                                         @RequestParam(value="id") int id){

        Optional<ToDO> toDoinDB = toDoRepository.findById(id);

        if(toDoinDB.isPresent()){
            //update isDone
            toDoinDB.get().setIsDone(isDone);
            ToDO savedTodo = toDoRepository.save(toDoinDB.get());
            return new ResponseEntity<ToDO>(savedTodo, HttpStatus.OK);
        }

        return new ResponseEntity("There is no object with this is to update " + id, HttpStatus.NOT_FOUND);

    }

}
