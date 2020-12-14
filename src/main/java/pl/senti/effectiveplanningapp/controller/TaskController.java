package pl.senti.effectiveplanningapp.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.senti.effectiveplanningapp.exception.TaskListServiceException;
import pl.senti.effectiveplanningapp.model.request.TaskWriteModel;
import pl.senti.effectiveplanningapp.model.response.TaskReadModel;
import pl.senti.effectiveplanningapp.service.TaskService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/{taskListId}")
    ModelAndView getAllTaskFromTaskList(@PathVariable Long taskListId, Model model){
        List<TaskReadModel> taskList = taskService.readAllTaskFromTaskList(taskListId);
        model.addAttribute("taskList",taskList);

        return new ModelAndView("redirect:/loginSuccess");

    }

    @DeleteMapping( "/{taskId}")
    ModelAndView deleteTask(@PathVariable Long taskId)  {
       taskService.deleteTask(taskId);

        return new ModelAndView("redirect:/loginSuccess");
    }
}
