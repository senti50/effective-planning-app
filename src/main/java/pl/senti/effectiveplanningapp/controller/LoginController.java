package pl.senti.effectiveplanningapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.senti.effectiveplanningapp.model.request.TaskListWriteModel;
import pl.senti.effectiveplanningapp.model.response.TaskListReadModel;
import pl.senti.effectiveplanningapp.security.UserPrincipal;
import pl.senti.effectiveplanningapp.security.CurrentUser;
import pl.senti.effectiveplanningapp.service.TaskListService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class LoginController {
    private final TaskListService taskListService;

    public LoginController(TaskListService taskListService) {
        this.taskListService = taskListService;
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

    @GetMapping("/login-error")
    public String login(HttpServletRequest request, Model model) {
        String errorMessage = request.getAttribute("errorMessage").toString();
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }
}