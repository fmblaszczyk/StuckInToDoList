package stuckinjava.todolist.repository;

import org.springframework.data.repository.CrudRepository;
import stuckinjava.todolist.model.ToDoTask;

import java.util.List;

public interface ToDoTaskRepository extends CrudRepository<ToDoTask, Integer> {
    List<ToDoTask> findAllByToDoListId (int id);
    ToDoTask findById (int id);
    ToDoTask findToDoTaskByIdAndToDoListId (int todotaskId, int todolistId);
}
