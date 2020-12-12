package pl.senti.effectiveplanningapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.senti.effectiveplanningapp.model.request.TaskListWriteModel;
import pl.senti.effectiveplanningapp.model.response.TaskListReadModel;
import pl.senti.effectiveplanningapp.model.response.TaskReadModel;
import pl.senti.effectiveplanningapp.security.UserPrincipal;
import pl.senti.effectiveplanningapp.security.CurrentUser;
import pl.senti.effectiveplanningapp.service.TaskListService;
import pl.senti.effectiveplanningapp.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class LoginController {
    private final TaskListService taskListService;
    private final TaskService taskService;

    public LoginController(TaskListService taskListService, TaskService taskService) {
        this.taskListService = taskListService;
        this.taskService = taskService;
    }

    @GetMapping({"/",""})
    public String home() {
        return "login";
    }


    @GetMapping("/loginSuccess")
    public String successfulLogin(Model model, @CurrentUser UserPrincipal userPrincipal) {
        model.addAttribute("name", userPrincipal.getName());
        model.addAttribute("src", userPrincipal.getImage());
        List<TaskListReadModel> list = taskListService.readAllUserTaskList(userPrincipal.getId());
        model.addAttribute("tasksList",list);
        model.addAttribute("newTaskList",new TaskListWriteModel());
        return "loginSuccess";
    }


    @GetMapping("/home/task/{taskListId}")
    public String getTasksFromTaskList(@PathVariable Long taskListId, Model model) {
        List<TaskReadModel> taskList = taskService.readAllTaskFromTaskList(taskListId);
        model.addAttribute("tasks",taskList);
        model.addAttribute("newTaskList",new TaskListWriteModel());
        //model.addAttribute("newTask",new TaskWriteModel());
        return "loginSuccess";
    }

    @GetMapping("/login-error")
    public String login(HttpServletRequest request, Model model) {
        String errorMessage = request.getAttribute("errorMessage").toString();
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }
}