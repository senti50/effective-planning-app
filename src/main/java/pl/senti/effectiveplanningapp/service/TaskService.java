package pl.senti.effectiveplanningapp.service;

import org.springframework.stereotype.Service;
import pl.senti.effectiveplanningapp.model.request.TaskWriteModel;
import pl.senti.effectiveplanningapp.model.response.TaskReadModel;
import pl.senti.effectiveplanningapp.repository.TaskRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public List<TaskReadModel> readAllTaskFromTaskList(Long taskListId) {

        return taskRepository.findAllByTaskListId(taskListId)
                .stream()
                .map(TaskReadModel::new)
                .collect(Collectors.toList());

    }

    public void crateTask(TaskWriteModel current) {
        taskRepository.save(current.toTaskEntity());
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
