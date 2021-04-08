package stuckinjava.todolist.general.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class ToDoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @JsonManagedReference
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "toDoList")
    private List<ToDoTask> toDoTask;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ToDoTask> getToDoTask() {
        return toDoTask;
    }

    public void setToDoTask(List<ToDoTask> toDoTask) {
        this.toDoTask = toDoTask;
    }

}
