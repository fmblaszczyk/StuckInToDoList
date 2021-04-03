package stuckinjava.todolist.general.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stuckinjava.todolist.general.dto.ToDoTaskDto;
import stuckinjava.todolist.general.model.ToDoTask;
import stuckinjava.todolist.general.service.ToDoTaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class ToDoTaskController {

    private ToDoTaskService toDoTaskService;

    @Autowired
    public ToDoTaskController(ToDoTaskService toDoTaskService) {
        this.toDoTaskService = toDoTaskService;
    }


    // 'id' stands for 'todolist id'
    // in postman: /tasks?id=<id of the list>
    @PostMapping
    public ResponseEntity<ToDoTask> create(@RequestBody ToDoTask toDoTask,
                                           @RequestParam int id){
        toDoTaskService.create(toDoTask, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // edit task
    @PutMapping("/edit")
    public ResponseEntity<ToDoTask> edit (@RequestBody ToDoTask toDoTask,
                                          @RequestParam int id){
        return ResponseEntity.ok(toDoTaskService.edit(toDoTask,id));
    }

    // delete task
    @DeleteMapping("/delete")
    public ResponseEntity<ToDoTask> delete (@RequestParam int id){
        toDoTaskService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // show all tasks in db
    // * test - for later remove *
    @GetMapping
    public ResponseEntity<List<ToDoTaskDto>> read (){
        List<ToDoTaskDto> toDoTaskDtos = toDoTaskService.findAll();
        return ResponseEntity.ok(toDoTaskDtos);
    }
}
