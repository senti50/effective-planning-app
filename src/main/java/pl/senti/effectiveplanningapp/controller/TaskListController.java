package pl.senti.effectiveplanningapp.controller;



import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.senti.effectiveplanningapp.exception.TaskListServiceException;
import pl.senti.effectiveplanningapp.model.request.TaskListWriteModel;
import pl.senti.effectiveplanningapp.model.response.TaskListReadModel;
import pl.senti.effectiveplanningapp.repository.TasksListRepository;
import pl.senti.effectiveplanningapp.security.CurrentUser;
import pl.senti.effectiveplanningapp.security.UserPrincipal;
import pl.senti.effectiveplanningapp.service.TaskListService;

import java.net.URI;
import java.util.List;
@Controller
@RequestMapping("/taskList")
public class TaskListController {
    private final TaskListService taskListService;

    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Transactional
    ResponseEntity<TaskListReadModel> createTaskList(@RequestBody TaskListWriteModel toCreate,@CurrentUser UserPrincipal user) {
        Long id = user.getId();
        TaskListReadModel result= taskListService.crateTaskList(toCreate,id);
        return ResponseEntity.created(URI.create("/"+result.getId())).body(result);
    }
    @GetMapping()
    @ResponseBody
    ResponseEntity<List<TaskListReadModel>> readAllUserTaskList(@CurrentUser UserPrincipal user) {
        return ResponseEntity.ok(taskListService.readAllUserTaskList(user.getId()));
    }

    @DeleteMapping("/{taskListId}")
    ResponseEntity<Void> deleteUserTaskList(@PathVariable Long taskListId) throws TaskListServiceException {
        taskListService.deleteUserTaskListById(taskListId);

        return ResponseEntity.noContent().build();
    }


}
