package stuckinjava.todolist.general.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stuckinjava.todolist.general.model.ToDoList;

import java.util.List;

@Repository
public interface ToDoListRepository extends CrudRepository<ToDoList, Integer> {
    ToDoList findById (int id);
    List<ToDoList> findAll ();
}
