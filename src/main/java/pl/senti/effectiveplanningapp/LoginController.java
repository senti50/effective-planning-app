package pl.senti.effectiveplanningapp;

import org.springframework.core.ResolvableType;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.senti.effectiveplanningapp.security.UserPrincipal;
import pl.senti.effectiveplanningapp.security.oauth2.CurrentUser;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginController {

    @GetMapping("/")
    public String home() {
        return "home";
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
        return "home";
    }
}