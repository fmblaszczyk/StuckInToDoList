package stuckinjava.todolist.general.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stuckinjava.todolist.general.dto.ToDoTaskDto;
import stuckinjava.todolist.general.mapper.ToDoTaskMapper;
import stuckinjava.todolist.general.model.ToDoTask;
import stuckinjava.todolist.general.repository.ToDoListRepository;
import stuckinjava.todolist.general.repository.ToDoTaskRepository;
import stuckinjava.todolist.general.service.ToDoTaskService;

import java.util.List;

@Service
public class ToDoTaskServiceJpa implements ToDoTaskService {

    private ToDoTaskRepository taskRepo;
    private ToDoListRepository listRepo;

    @Autowired
    public ToDoTaskServiceJpa(ToDoTaskRepository taskRepo, ToDoListRepository listRepo) {
        this.taskRepo = taskRepo;
        this.listRepo = listRepo;
    }

    @Override
    public ToDoTask create(ToDoTask toDoTask, int toDoListId) {
        toDoTask.setToDoList(listRepo.findById(toDoListId));
        return taskRepo.save(toDoTask);
    }

    @Override
    public void delete(int id) {
        taskRepo.deleteById(id);
    }

    @Override
    public ToDoTask edit(ToDoTask toDoTask, int id) {
        ToDoTask tDT = taskRepo.findById(id);
        tDT.setDescription(toDoTask.getDescription());
        tDT.setHeading(toDoTask.getHeading());
        tDT.setDone(toDoTask.isDone());
        taskRepo.save(tDT);
        return tDT;
    }

    @Override
    public List<ToDoTaskDto> getListsTask(int toDoListId) {
        List<ToDoTask> toDoTasks = taskRepo.findAllByToDoListId(toDoListId);
        List<ToDoTaskDto> toDoTaskDtos = ToDoTaskMapper.mapEntityListToDtoList(toDoTasks);
        return toDoTaskDtos;
    }

    @Override
    public ToDoTaskDto find(int toDoTaskId, int toDoListId) {
        ToDoTask toDoTask = taskRepo.findToDoTaskByIdAndToDoListId(toDoTaskId,toDoListId);
        ToDoTaskDto toDoTaskDto = ToDoTaskMapper.mapEntityToDto(toDoTask);
        return toDoTaskDto;
    }

    @Override
    public List<ToDoTaskDto> findAll() {
        List<ToDoTask> toDoTask = taskRepo.findAll();
        List<ToDoTaskDto> toDoTaskDtos = ToDoTaskMapper.mapEntityListToDtoList(toDoTask);
        return toDoTaskDtos;
    }
}
