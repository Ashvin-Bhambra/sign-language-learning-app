package app;


import app.entity.Question;
import app.entity.User;
import app.service.QuestionService;
import app.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
    @SpringBootTest
    public class LanguageAppTest {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    private User user;

    @Before
    public void createTestUser() {
        user = new User("SampleUser@gmail.com", "password");
        userService.save(user);
    }

    @After
    public void removeTestUser() {
        userService.removeUser(user);
    }


    @Test
    public void testRegistration() {
        User testUser = userService.loadUserByUsername("SampleUser@gmail.com");
        assertEquals(user, testUser);
    }

    @Test
    public void testQuestionsExist() {
        assertNotNull(questionService.getAllQuestions());
    }

    @Test
    public void testUserStreaks() {
        User testUser = userService.loadUserByUsername("SampleUser@gmail.com");
        assertEquals(0, testUser.getStreaks());
        LocalDateTime lastLogin = LocalDateTime.now().minusHours(27);
        testUser.setLastLogin(lastLogin);
        userService.setUserStreaks(testUser);
        testUser = userService.loadUserByUsername("SampleUser@gmail.com");
        assertEquals(1, testUser.getStreaks());
    }

    @Test
    public void testQuestionAnswered() {
        User testUser = userService.loadUserByUsername("SampleUser@gmail.com");

        for(Question question : questionService.getAllQuestions())
        {
            System.out.println(question.getId()+" "+question.getQuestionText()+" "+question.getCorrectOption().getId());
        }
        boolean questionAnswered = questionService.checkIfQuestionAnswered(testUser, 13, 3);
        assertTrue(questionAnswered);
        testUser = userService.loadUserByUsername("SampleUser@gmail.com");
        assertEquals(10, testUser.getCurrentGameScore());
    }
}

