package pl.senti.effectiveplanningapp.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.senti.effectiveplanningapp.model.entities.TaskList;
import pl.senti.effectiveplanningapp.model.request.TaskListWriteModel;
import pl.senti.effectiveplanningapp.model.response.TaskListReadModel;
import pl.senti.effectiveplanningapp.repository.TasksListRepository;
import pl.senti.effectiveplanningapp.security.CurrentUser;
import pl.senti.effectiveplanningapp.security.UserPrincipal;
import pl.senti.effectiveplanningapp.service.TaskListService;

import java.net.URI;

@Controller
@RequestMapping("/taskList")
public class TaskListController {
    private final TaskListService taskListService;
    private final TasksListRepository tasksListRepository;

    public TaskListController(TaskListService taskListService, TasksListRepository tasksListRepository) {
        this.taskListService = taskListService;
        this.tasksListRepository = tasksListRepository;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<TaskListReadModel> createTaskList(@RequestBody TaskListWriteModel toCreate) {
        TaskListReadModel result= taskListService.crateTaskList(toCreate);
        return ResponseEntity.created(URI.create("/"+result.getId())).body(result);
    }
    @GetMapping()
    @ResponseBody
    @Transactional
    ResponseEntity<TaskList> createGroup(@CurrentUser UserPrincipal user) {
        TaskList taskList=new TaskList();
        taskList.setName("springgg");
        taskList.setUserId(user.getId());
        TaskList result=tasksListRepository.save(taskList);
        return ResponseEntity.created(URI.create("/"+result.getId())).body(result);
    }


}
