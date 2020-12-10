package pl.senti.effectiveplanningapp.controller;



import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.senti.effectiveplanningapp.exception.TaskListServiceException;
import pl.senti.effectiveplanningapp.model.request.TaskListWriteModel;
import pl.senti.effectiveplanningapp.model.response.TaskListReadModel;
import pl.senti.effectiveplanningapp.security.CurrentUser;
import pl.senti.effectiveplanningapp.security.UserPrincipal;
import pl.senti.effectiveplanningapp.service.TaskListService;

import java.util.List;
@Controller
@RequestMapping("/taskList")
public class TaskListController {
    private final TaskListService taskListService;

    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

//    @GetMapping(name = "/loginSuccess",produces = MediaType.TEXT_HTML_VALUE)
//    String showTaskList(Model model){
//        model.addAttribute("taskItem", new TaskListWriteModel());
//        return "tasksList";
//    }

    @PostMapping()
    @Transactional
    ModelAndView createTaskList(@CurrentUser UserPrincipal user,@ModelAttribute("newTaskList") TaskListWriteModel toCreate ) {
        Long id = user.getId();
        taskListService.crateTaskList(toCreate,id);
        return new ModelAndView("redirect:/loginSuccess");
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
