package stuckinjava.todolist.general.dto;

import lombok.Data;

import java.util.List;

@Data
public class ToDoListDto {

    private int id;

    private String title;

    private List<ToDoTaskDto> todotasks;
}
