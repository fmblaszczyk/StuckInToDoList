package stuckinjava.todolist.general.service;

import stuckinjava.todolist.general.dto.ToDoTaskDto;
import stuckinjava.todolist.general.model.ToDoTask;

import java.util.List;

public interface ToDoTaskService {

    ToDoTask create (ToDoTask toDoTask, int toDoListId);

    void delete (int id);

    ToDoTask edit (ToDoTask toDoTask, int id);

    List<ToDoTaskDto> getListsTask (int toDoListId);

    ToDoTaskDto find (int toDoTaskId, int toDoListId);

    List<ToDoTaskDto> findAll ();
}
