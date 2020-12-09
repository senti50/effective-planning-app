package pl.senti.effectiveplanningapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.senti.effectiveplanningapp.security.UserPrincipal;
import pl.senti.effectiveplanningapp.security.CurrentUser;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginController {

    @GetMapping({"/",""})
    public String home() {
        return "login";
    }


    @GetMapping("/loginSuccess")
    public String successfulLogin(Model model, @CurrentUser UserPrincipal userPrincipal) {
        model.addAttribute("name", userPrincipal.getName());
        model.addAttribute("src", userPrincipal.getImage());
        return "loginSuccess";
    }

    @GetMapping("/login-error")
    public String login(HttpServletRequest request, Model model) {
        String errorMessage = request.getAttribute("errorMessage").toString();
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }
}