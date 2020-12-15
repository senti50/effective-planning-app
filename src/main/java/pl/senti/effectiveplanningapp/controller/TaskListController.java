package pl.senti.effectiveplanningapp.controller;



import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.senti.effectiveplanningapp.exception.TaskListServiceException;
import pl.senti.effectiveplanningapp.model.request.TaskListWriteModel;
import pl.senti.effectiveplanningapp.model.response.TaskListReadModel;
import pl.senti.effectiveplanningapp.security.CurrentUser;
import pl.senti.effectiveplanningapp.security.UserPrincipal;
import pl.senti.effectiveplanningapp.service.TaskListService;

import javax.validation.Valid;
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
    ModelAndView createTaskList(@CurrentUser UserPrincipal user, @ModelAttribute("newTaskList") @Valid TaskListWriteModel toCreate, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return new ModelAndView("redirect:/loginSuccess");
        }
        Long id = user.getId();
        taskListService.crateTaskList(toCreate,id);
        return new ModelAndView("redirect:/loginSuccess");
    }
    @GetMapping()
    @ResponseBody
    ResponseEntity<List<TaskListReadModel>> readAllUserTaskList(@CurrentUser UserPrincipal user) {
        return ResponseEntity.ok(taskListService.readAllUserTaskList(user.getId()));
    }

    @Transactional
    @DeleteMapping( "/{taskListId}")
    ModelAndView deleteUserTaskList(@PathVariable Long taskListId) throws TaskListServiceException {

        taskListService.deleteUserTaskListById(taskListId);

        return new ModelAndView("redirect:/loginSuccess");
    }


}
