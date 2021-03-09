package stuckinjava.todolist.model;


import javax.persistence.*;

@Entity
public class ToDoTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    private boolean isDone;

    @ManyToOne
    @JoinColumn(name = "todolist_id")
    private ToDoList toDoList;
}
