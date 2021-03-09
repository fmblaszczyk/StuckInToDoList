package stuckinjava.todolist.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class ToDoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @OneToMany(mappedBy = "todolist")
    private List<ToDoTask> todotasks;
}
