package pl.senti.effectiveplanningapp.service;

import org.springframework.stereotype.Service;
import pl.senti.effectiveplanningapp.model.entities.TaskList;
import pl.senti.effectiveplanningapp.model.request.TaskListWriteModel;
import pl.senti.effectiveplanningapp.model.response.TaskListReadModel;
import pl.senti.effectiveplanningapp.repository.TasksListRepository;

@Service
public class TaskListService {
    private final TasksListRepository tasksListRepository;

    public TaskListService(TasksListRepository tasksListRepository) {
        this.tasksListRepository = tasksListRepository;
    }

    public TaskListReadModel crateTaskList(TaskListWriteModel source) {
        TaskList taskEntity=new TaskList();
        taskEntity.setName(source.getName());
        TaskList result=tasksListRepository.save(taskEntity);
        return new TaskListReadModel(result.getId(), result);
    }


}
