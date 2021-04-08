package stuckinjava.todolist.general.service;

import stuckinjava.todolist.general.dto.ToDoListDto;
import stuckinjava.todolist.general.model.ToDoList;

import java.util.List;
import java.util.Optional;

public interface ToDoListService {

    void create (ToDoList toDoList);

    ToDoListDto findByIdListWithoutTasks (int id);

    ToDoListDto findByIdListWithTasks (int id);

    List<ToDoListDto> findAll();

    void delete (int id);

    ToDoList edit (int id, ToDoList toDoList);
}
