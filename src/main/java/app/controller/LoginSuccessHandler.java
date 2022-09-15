package app.controller;

import app.entity.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public final class LoginSuccessHandler implements AuthenticationSuccessHandler
{
@Autowired
private UserService userService;

@Override
public void onAuthenticationSuccess(HttpServletRequest request,
HttpServletResponse response,
Authentication authentication) throws IOException
{

String currentUserName = authentication.getName();
User user = userService.loadUserByUsername(currentUserName);
user.setCurrentGameScore(0);
userService.setUserStreaks(user);
response.sendRedirect("/index");

}
}