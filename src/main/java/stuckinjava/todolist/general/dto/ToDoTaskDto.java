package stuckinjava.todolist.general.dto;

import lombok.Data;

@Data
public class ToDoTaskDto {

    private int id;

    private String heading;
    private String description;
    private boolean isDone;
}
