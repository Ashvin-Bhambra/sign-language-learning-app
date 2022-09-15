package app.security;

import app.controller.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

private UserDetailsImplementation userDetailsService;

@Autowired
public WebSecurityConfig(UserDetailsImplementation detailsService) {
this.userDetailsService = detailsService;
}

@Bean
public PasswordEncoder passwordEncoder() {
return new BCryptPasswordEncoder();
}

@Override
protected void configure(AuthenticationManagerBuilder auth)
throws Exception {
auth
.userDetailsService(userDetailsService)
.passwordEncoder(passwordEncoder());
}

@Bean
protected AuthenticationManager getAuthenticationManager() throws Exception {
return super.authenticationManagerBean();
}


@Override
protected void configure(HttpSecurity http) throws Exception {
http
.authorizeRequests()
.antMatchers( "/","/login,","/registerUser","/showRegistration","/css/style.css").permitAll()
.anyRequest()

.authenticated()
.and()
.formLogin()
//.loginPage("/login")
.defaultSuccessUrl("/index", true)
.failureUrl("/login?error")
.usernameParameter("email")
.passwordParameter("password")
        .successHandler(createLoginSuccessHandler())
        .and().logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login")
.permitAll();
}
    @Bean
    public AuthenticationSuccessHandler createLoginSuccessHandler() {
        return new LoginSuccessHandler();
    }

@Override
public void configure(WebSecurity web) throws Exception {
super.configure(web);
}
}