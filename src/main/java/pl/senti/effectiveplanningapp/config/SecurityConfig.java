package pl.senti.effectiveplanningapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import pl.senti.effectiveplanningapp.security.oauth2.CustomOAuth2UserService;
import pl.senti.effectiveplanningapp.security.oauth2.OAuth2AuthenticationFailureHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

private final CustomOAuth2UserService customOAuth2UserService;
private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService, OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.oAuth2AuthenticationFailureHandler = oAuth2AuthenticationFailureHandler;
    }

    @Bean
    public AuthorizationRequestRepository customAuthorizationRequestRepository() {
        return new HttpSessionOAuth2AuthorizationRequestRepository();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http


                .authorizeRequests()
                .antMatchers("/oauth_login","/loginFailure","/","/css/*","/js/*","/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .authorizationEndpoint()
                .authorizationRequestRepository(customAuthorizationRequestRepository())
                .and()
                .userInfoEndpoint()
                .oidcUserService(customOAuth2UserService)
                .and()
                .loginPage("/oauth_login")
                .defaultSuccessUrl("/loginSuccess")
                .failureHandler(oAuth2AuthenticationFailureHandler)



        ;
//        http.authorizeRequests()
//                .antMatchers("/oauth_login")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .oauth2Login()
//                .defaultSuccessUrl("/loginSuccess");
//                .failureUrl("/loginFailure")
//                .loginPage("/oauth_login")
//                .authorizationEndpoint()
//                .baseUri("/oauth2/authorize-client")
//                .authorizationRequestRepository(authorizationRequestRepository());
    }



}