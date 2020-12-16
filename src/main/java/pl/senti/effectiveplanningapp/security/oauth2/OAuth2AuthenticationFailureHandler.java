package pl.senti.effectiveplanningapp.security.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import pl.senti.effectiveplanningapp.exception.OAuth2AuthenticationProcessingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class OAuth2AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception)
            throws IOException, ServletException {
        if (exception instanceof OAuth2AuthenticationProcessingException) {
            String exceptionMessage = exception.getMessage();
            request.setAttribute("errorMessage", exceptionMessage);
            request.getRequestDispatcher("/login-error")
                    .forward(request, response);
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }

    }


}