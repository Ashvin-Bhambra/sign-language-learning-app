package app.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Table(name="app_users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;

    private String username;
    private String password;

    private int currentGameScore;

    private int maxScore;

    private LocalDateTime lastLogin;

    private int streaks;


    @Transient
    private boolean accountNonExpired;
    @Transient
    private boolean accountNonLocked;
    @Transient
    private boolean credentialsNonExpired;
    @Transient
    private boolean isEnabled;

    public enum Role {
        USER, ADMIN
    }

    public User(){
        this.username="";
        this.password = "";
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.isEnabled = true;
        this.currentGameScore = 0;
        this.maxScore = 0;
        this.lastLogin = LocalDateTime.now();
        this.streaks = 0;

    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.isEnabled = true;
        this.currentGameScore = 0;
        this.maxScore = 0;
        this.lastLogin = LocalDateTime.now();
        this.streaks = 0;


    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(Role.USER.toString()));
    }

    public LocalDateTime getLastLogin(){
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin){
        this.lastLogin = lastLogin;
    }

    public int getStreaks(){
        return streaks;
    }

    public void setStreaks(int streaks){
        this.streaks = streaks;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public int getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getCurrentGameScore() {
        return currentGameScore;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setCurrentGameScore(int currentGameScore) {
        this.currentGameScore = currentGameScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

}
