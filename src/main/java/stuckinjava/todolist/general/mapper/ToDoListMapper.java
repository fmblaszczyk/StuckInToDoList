package stuckinjava.todolist.general.mapper;

import stuckinjava.todolist.general.dto.ToDoListDto;
import stuckinjava.todolist.general.model.ToDoList;

import java.util.ArrayList;
import java.util.List;

public class ToDoListMapper {

    public static ToDoListDto mapEntityToDto(ToDoList toDoList, boolean lazy){
        ToDoListDto toDoListDto = new ToDoListDto();
        toDoListDto.setId(toDoList.getId());
        toDoListDto.setTitle(toDoList.getTitle());
        if (!lazy){
            toDoListDto.setTodotasks(ToDoTaskMapper.mapEntityListToDtoList(toDoList.getToDoTask()));
        }
        return toDoListDto;
    }

    public static List<ToDoListDto> mapEntityListToDtoList(List<ToDoList> toDoLists, boolean lazy){
        List<ToDoListDto> x = new ArrayList<>();
        for (ToDoList toDoList: toDoLists){
            x.add(mapEntityToDto(toDoList, lazy));
        }
        return x;
    }
}
