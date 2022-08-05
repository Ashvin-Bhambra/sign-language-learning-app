package app.controller;

import app.entity.Game;
import app.entity.Option;
import app.entity.Question;
import app.entity.User;
import app.service.GameService;
import app.service.OptionService;
import app.service.QuestionService;
import app.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class HomePageController {

    @Autowired
    QuestionService questionService;

    @Autowired
    OptionService optionService;

    @Autowired
    UserService userService;

    @Autowired
    GameService gameService;


    @RequestMapping("/showQuestionsForGame/{gameId}")
    public String getQuestionsForGame(@PathVariable("gameId") int gameId, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        Game game = gameService.getGameByGameId(gameId);

        List<Question> questions =  game.getQuestionList();

        System.out.println("----- QUESTIONS: ------");
        System.out.println(questions);
        model.addAttribute("allQuestions", questions);
        model.addAttribute("currentUser", currentUserName);
        return "index";

    }


    @RequestMapping(value = {"/", "/home", "/index"})
    public String getHomePage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        ArrayList<Game> games = (ArrayList<Game>) gameService.getAllGames();
        model.addAttribute("allGames", games);
        model.addAttribute("currentUser", currentUserName);
        return "games";

    }





    @RequestMapping("/selectAnswer/{questionId}/{optionId}")
    public String acceptAnswerOption(@PathVariable("questionId") int questionId, @PathVariable("optionId") int optionId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        User user = userService.loadUserByUsername(currentUserName);

            Question question = questionService .findQuestionById(questionId);
           Option selectedOption = optionService.findOptionById(optionId);

           if(question.getCorrectOption().equals(selectedOption)){
               int currentScore = user.getCurrentGameScore();
               currentScore = currentScore +1;
               user.setCurrentGameScore(currentScore);
               userService.save(user);
           }

        return "index";




    }


}
