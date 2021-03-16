package stuckinjava.todolist.general.model;


import javax.persistence.*;

@Entity
public class ToDoTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String heading;
    private String description;

    private boolean isDone;

    @ManyToOne
    @JoinColumn(name = "toDoList_id")
    private ToDoList toDoList;
}
