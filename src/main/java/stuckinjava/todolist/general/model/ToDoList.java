package stuckinjava.todolist.general.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class ToDoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @OneToMany(mappedBy = "toDoList")
    private List<ToDoTask> toDoTask;
}
