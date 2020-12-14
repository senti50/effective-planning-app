package pl.senti.effectiveplanningapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.senti.effectiveplanningapp.model.entities.Priority;
import pl.senti.effectiveplanningapp.model.request.TaskListWriteModel;
import pl.senti.effectiveplanningapp.model.request.TaskWriteModel;
import pl.senti.effectiveplanningapp.model.response.TaskListReadModel;
import pl.senti.effectiveplanningapp.model.response.TaskReadModel;
import pl.senti.effectiveplanningapp.security.UserPrincipal;
import pl.senti.effectiveplanningapp.security.CurrentUser;
import pl.senti.effectiveplanningapp.service.TaskListService;
import pl.senti.effectiveplanningapp.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
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

        addUserInfoAndTaskListToModel( userPrincipal.getName(),userPrincipal.getImage(),userPrincipal.getId(),model);

        return "loginSuccess";
    }

    private void addUserInfoAndTaskListToModel(String userName, String userImageUrl, Long userId,Model model) {
        model.addAttribute("name", userName);
        model.addAttribute("src", userImageUrl);
        List<TaskListReadModel> list = taskListService.readAllUserTaskList(userId);
        model.addAttribute("tasksList",list);
        model.addAttribute("newTaskList",new TaskListWriteModel());
        List<Priority> priorities=new ArrayList<>(Arrays.asList(Priority.values()));
        model.addAttribute("priorityOptions",priorities);
        model.addAttribute("newTask",new TaskWriteModel());
    }


    @GetMapping("/home/task/{taskListId}")
    public String getTasksFromTaskList(@PathVariable Long taskListId, Model model,@CurrentUser UserPrincipal userPrincipal) {
        addUserInfoAndTaskListToModel(userPrincipal.getName(),userPrincipal.getImage(),userPrincipal.getId(),model);
        List<TaskReadModel> taskList = taskService.readAllTaskFromTaskList(taskListId);
        model.addAttribute("tasks",taskList);

        //model.addAttribute("newTask",new TaskWriteModel());
        return "loginSuccess";
    }
    @PostMapping("/home/addTask")
    String addTask(
            @ModelAttribute("newTask") @Valid TaskWriteModel current,
            BindingResult bindingResult,
            @CurrentUser UserPrincipal userPrincipal,
            Model model) {
        addUserInfoAndTaskListToModel(userPrincipal.getName(),userPrincipal.getImage(),userPrincipal.getId(),model);
        if (bindingResult.hasErrors()) {
            return "loginSuccess";
        }
       taskService.crateTask(current);

        model.addAttribute("message", "Task has been added to the group");
        return "loginSuccess";
    }


    @GetMapping("/login-error")
    public String login(HttpServletRequest request, Model model) {
        String errorMessage = request.getAttribute("errorMessage").toString();
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }
}