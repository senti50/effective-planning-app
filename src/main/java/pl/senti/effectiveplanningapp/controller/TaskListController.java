package pl.senti.effectiveplanningapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.senti.effectiveplanningapp.exception.TaskListServiceException;
import pl.senti.effectiveplanningapp.service.TaskListService;

@Controller
@RequestMapping("/taskList")
public class TaskListController {
    private final TaskListService taskListService;

    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @Transactional
    @DeleteMapping("/{taskListId}")
    ModelAndView deleteUserTaskList(@PathVariable Long taskListId) throws TaskListServiceException {
        taskListService.deleteUserTaskListById(taskListId);
        return new ModelAndView("redirect:/home");
    }
}
