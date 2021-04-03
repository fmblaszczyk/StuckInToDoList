package stuckinjava.todolist.general.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stuckinjava.todolist.general.dto.ToDoListDto;
import stuckinjava.todolist.general.dto.ToDoTaskDto;
import stuckinjava.todolist.general.model.ToDoList;
import stuckinjava.todolist.general.service.ToDoListService;
import stuckinjava.todolist.general.service.ToDoTaskService;

import java.util.List;

@RestController
@RequestMapping("/lists")
public class ToDoListController {

    private ToDoListService toDoListService;
    private ToDoTaskService toDoTaskService;

    @Autowired
    public ToDoListController(ToDoListService toDoListService, ToDoTaskService toDoTaskService) {
        this.toDoListService = toDoListService;
        this.toDoTaskService = toDoTaskService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ToDoList toDoList){
        toDoListService.create(toDoList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // edit list
    @PutMapping("/edit")
    public ResponseEntity<ToDoList> edit (@RequestParam int id,
                                          @RequestBody ToDoList toDoList){
        return ResponseEntity.ok(toDoListService.edit(id,toDoList));
    }

    // delete list
    @DeleteMapping("/delete")
    public ResponseEntity<ToDoList> delete (@RequestParam int id){
        toDoListService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // show all lists with all tasks
    @GetMapping
    public ResponseEntity<List<ToDoListDto>> read(){
        List<ToDoListDto> toDoListDtos = toDoListService.findAll();
        return ResponseEntity.ok(toDoListDtos);
    }

    // show single list by id
    @GetMapping("/special/{toDoList_Id}")
    public ResponseEntity<ToDoListDto> findList (@PathVariable int toDoList_Id){
        ToDoListDto toDoListDto = toDoListService.findByIdListWithoutTasks(toDoList_Id);
        return ResponseEntity.ok(toDoListDto);
    }

    // show single list with all tasks
    @GetMapping("/{toDoList_Id}")
    public ResponseEntity<ToDoListDto> findList2 (@PathVariable int toDoList_Id){
        ToDoListDto toDoListDto = toDoListService.findByIdListWithTasks(toDoList_Id);
        return ResponseEntity.ok(toDoListDto);
    }

    // show only tasks from single list
    @GetMapping("/{toDoList_Id}/tasks")
    public ResponseEntity<List<ToDoTaskDto>> findTasks (@PathVariable int toDoList_Id){
        return ResponseEntity.ok(toDoTaskService.getListsTask(toDoList_Id));
    }

    // show single tasks from single list
    @GetMapping("/{toDoList_Id}/tasks/{toDoTask_Id}")
    public ResponseEntity<ToDoTaskDto> findTaskById (@PathVariable int toDoList_Id,
                                                           @PathVariable int toDoTask_Id){
        ToDoTaskDto toDoTaskDto = toDoTaskService.find(toDoTask_Id, toDoList_Id);
        return ResponseEntity.ok(toDoTaskDto);
    }

}
