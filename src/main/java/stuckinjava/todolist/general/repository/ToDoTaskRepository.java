package stuckinjava.todolist.general.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stuckinjava.todolist.general.model.ToDoTask;

import java.util.List;

@Repository
public interface ToDoTaskRepository extends CrudRepository<ToDoTask, Integer> {
    List<ToDoTask> findAllByToDoListId (int id);
    ToDoTask findById (int id);
    ToDoTask findToDoTaskByIdAndToDoListId (int todotaskId, int todolistId);
    List<ToDoTask> findAll();
}
