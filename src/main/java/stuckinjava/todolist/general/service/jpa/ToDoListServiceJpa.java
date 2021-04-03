package stuckinjava.todolist.general.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stuckinjava.todolist.general.dto.ToDoListDto;
import stuckinjava.todolist.general.mapper.ToDoListMapper;
import stuckinjava.todolist.general.model.ToDoList;
import stuckinjava.todolist.general.repository.ToDoListRepository;
import stuckinjava.todolist.general.service.ToDoListService;

import java.util.List;

@Service
public class ToDoListServiceJpa implements ToDoListService {

    private ToDoListRepository listRepo;

    @Autowired
    public ToDoListServiceJpa(ToDoListRepository listRepo) {
        this.listRepo = listRepo;
    }

    @Override
    public void create(ToDoList toDoList) {
        ToDoList toDoLists = new ToDoList();
        toDoLists.setTitle(toDoList.getTitle());
        listRepo.save(toDoLists);
    }

    @Override
    public ToDoListDto findByIdListWithoutTasks(int id) {
        ToDoList toDoLists = listRepo.findById(id);
        ToDoListDto toDoListDto = ToDoListMapper.mapEntityToDto(toDoLists, true);
        return toDoListDto;    }

    @Override
    public ToDoListDto findByIdListWithTasks(int id) {
        ToDoList toDoLists = listRepo.findById(id);
        ToDoListDto toDoListDto = ToDoListMapper.mapEntityToDto(toDoLists, false);
        return toDoListDto;
    }


    @Override
    public List<ToDoListDto> findAll() {
        List<ToDoList> toDoLists = listRepo.findAll();
        List<ToDoListDto> toDoListDtos = ToDoListMapper.mapEntityListToDtoList(toDoLists, false);
        return toDoListDtos;
    }

    @Override
    public void delete(int id) {
        listRepo.deleteById(id);
    }

    @Override
    public ToDoList edit(int id, ToDoList toDoList) {
        ToDoList tDL = listRepo.findById(id);
        tDL.setTitle(toDoList.getTitle());
        listRepo.save(tDL);
        return tDL;
    }
}
