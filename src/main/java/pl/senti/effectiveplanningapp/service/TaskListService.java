package pl.senti.effectiveplanningapp.service;

import org.springframework.stereotype.Service;
import pl.senti.effectiveplanningapp.exception.TaskListServiceException;
import pl.senti.effectiveplanningapp.model.entities.TaskList;
import pl.senti.effectiveplanningapp.model.request.TaskListWriteModel;
import pl.senti.effectiveplanningapp.model.response.TaskListReadModel;
import pl.senti.effectiveplanningapp.repository.TasksListRepository;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskListService {
    private final TasksListRepository tasksListRepository;

    public TaskListService(TasksListRepository tasksListRepository) {
        this.tasksListRepository = tasksListRepository;
    }

    public TaskListReadModel crateTaskList(TaskListWriteModel source, Long id) {
        TaskList taskEntity = new TaskList();
        taskEntity.setName(source.getName());
        taskEntity.setUserId(id);
        TaskList result = tasksListRepository.save(taskEntity);
        return new TaskListReadModel(result);
    }

    public List<TaskListReadModel> readAllUserTaskList(Long id) {
        return tasksListRepository.findAllByUserId(id).stream()
                .map(TaskListReadModel::new)
                .collect(Collectors.toList());
    }



    public void deleteUserTaskListById(Long taskListId) throws TaskListServiceException {
        Optional<TaskList> taskList = tasksListRepository.findById(taskListId);

        if (taskList.isEmpty()) {
            throw new TaskListServiceException("Record/s not found");
        }

       tasksListRepository.delete(taskList.get());
    }
}
