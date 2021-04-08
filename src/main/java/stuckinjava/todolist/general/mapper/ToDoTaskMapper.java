package stuckinjava.todolist.general.mapper;

import stuckinjava.todolist.general.dto.ToDoTaskDto;
import stuckinjava.todolist.general.model.ToDoTask;

import java.util.ArrayList;
import java.util.List;

public class ToDoTaskMapper {

    public static ToDoTaskDto mapEntityToDto(ToDoTask toDoTask){
        ToDoTaskDto toDoTaskDto = new ToDoTaskDto();
        toDoTaskDto.setId(toDoTask.getId());
        toDoTaskDto.setDescription(toDoTask.getDescription());
        toDoTaskDto.setHeading(toDoTask.getHeading());
        toDoTaskDto.setDone(toDoTask.isDone());
        return toDoTaskDto;
    }

    public static List<ToDoTaskDto> mapEntityListToDtoList(List<ToDoTask> toDoTasks){
        List<ToDoTaskDto> x = new ArrayList<>();
        for (ToDoTask toDoTask: toDoTasks){
            x.add(mapEntityToDto(toDoTask));
        }
        return x;
    }
}
