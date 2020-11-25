package pl.senti.effectiveplanningapp.security.oauth2;

import pl.senti.effectiveplanningapp.exception.OAuth2AuthenticationProcessingException;
import pl.senti.effectiveplanningapp.security.oauth2.dto.FacebookOAuth2User;
import pl.senti.effectiveplanningapp.security.oauth2.dto.GithubOAuth2User;
import pl.senti.effectiveplanningapp.security.oauth2.dto.GoogleOAuth2User;
import pl.senti.effectiveplanningapp.security.oauth2.dto.OAuth2UserInfo;

import java.util.Map;

public class OAuth2UserFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new GoogleOAuth2User(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.facebook.toString())) {
            return new FacebookOAuth2User(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.github.toString())) {
            return new GithubOAuth2User(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Login with " + registrationId + " is not supported yet.");
        }
    }
}
