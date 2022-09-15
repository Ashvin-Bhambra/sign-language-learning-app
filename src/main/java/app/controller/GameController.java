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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
public class GameController {

    @Autowired
    QuestionService questionService;

    @Autowired
    OptionService optionService;

    @Autowired
    UserService userService;

    @Autowired
    GameService gameService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @RequestMapping("/showQuestionsForGame/{gameId}")
    public String getQuestionsForGame(@PathVariable("gameId") int gameId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User user = userService.loadUserByUsername(currentUserName);

        Game game = gameService.getGameByGameId(gameId);

        List<Question> questions = game.getQuestionList();

        for (Question question : game.getQuestionList()) {
            question.setAnswered(false);
            questionService.saveQuestion(question);
        }

        model.addAttribute("allQuestions", questions);
        model.addAttribute("currentUser", currentUserName);
        model.addAttribute("errorMsg", null);
        model.addAttribute("gameComplete", false);
        model.addAttribute("currentScore", user.getCurrentGameScore());
        model.addAttribute("streak",user.getStreaks());
        if (game.getLevel().equalsIgnoreCase("Intermediate")) {
            return "game_two";
        }
        if (game.getLevel().equalsIgnoreCase("Advanced")) {
            return "game_three";
        }

        return "index";

    }

    @RequestMapping(value = { "/home", "/index"})
    public String getHomePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User user = userService.loadUserByUsername(currentUserName);

        ArrayList<Game> games = (ArrayList<Game>) gameService.getAllGames();
        model.addAttribute("allGames", games);
        model.addAttribute("currentUser", currentUserName);
        model.addAttribute("currentScore", user.getCurrentGameScore());
        model.addAttribute("streak",user.getStreaks());


        return "games";

    }

    private void processQuestionAnswers(int questionId, int optionId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User user = userService.loadUserByUsername(currentUserName);

        boolean questionAnswered = questionService.checkIfQuestionAnswered(user, questionId, optionId);

        if(questionAnswered){
            model.addAttribute("showVideo", true);
            model.addAttribute("errorMsg", null);
        } else {
            model.addAttribute("showVideo", false);
            model.addAttribute("errorMsg", "Wrong answer, try again!");
        }

        Game game = gameService.getGameByGameId(questionService.findQuestionById(questionId).getGame().getId());
        List<Question> questions = game.getQuestionList();
        List<Question> unansweredQuestions = new ArrayList<>();

        for (Question singleQuestion : questions) {
            if (singleQuestion.isAnswered() == false) {
                unansweredQuestions.add(singleQuestion);
            }
        }

        if (unansweredQuestions.size() == 0) {
            model.addAttribute("gameComplete", true);
        } else {
            model.addAttribute("gameComplete", false);
        }

        model.addAttribute("allQuestions", unansweredQuestions);
        model.addAttribute("currentUser", currentUserName);
        model.addAttribute("currentScore", user.getCurrentGameScore());
        model.addAttribute("streak", user.getStreaks());
    }

    @RequestMapping("/resumeGame/{questionId}")
    public String resumeGame(Model model, @PathVariable("questionId") int questionId) {

        Question question = questionService.findQuestionById(questionId);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User user = userService.loadUserByUsername(currentUserName);


        Game game = gameService.getGameByGameId(question.getGame().getId());

        List<Question> questions = game.getQuestionList();
        List<Question> unansweredQuestions = new ArrayList<>();

        for (Question singleQuestion : game.getQuestionList()) {
            if (singleQuestion.isAnswered() == false) {
                unansweredQuestions.add(singleQuestion);

            }
        }

        if (unansweredQuestions.size() == 0) {
            model.addAttribute("gameComplete", true);
        } else {
            model.addAttribute("gameComplete", false);

        }


        model.addAttribute("allQuestions", unansweredQuestions);
        model.addAttribute("currentUser", currentUserName);
        model.addAttribute("currentScore", user.getCurrentGameScore());
        model.addAttribute("streak",user.getStreaks());



        return "index";

    }




    @RequestMapping("/selectAnswer/{questionId}/{optionId}")
    public String acceptAnswerOption(@PathVariable("questionId") int questionId, @PathVariable("optionId") int optionId, Model model) {
               processQuestionAnswers(questionId, optionId, model);

               if(model.containsAttribute("showVideo") && model.getAttribute("showVideo").equals(true)){
                   Question question = questionService.findQuestionById(questionId);
                   model.addAttribute("question", question);
                   return "video-page";

               }else{
                   return "index";
               }


           }
           @RequestMapping("/selectAnswerForGameTwo/{questionId}/{optionId}")
            public String selectAnswerForGameTwo(@PathVariable("questionId") int questionId,
                                                 @PathVariable("optionId") int optionId,
                                                 Model model){
            processQuestionAnswers(questionId, optionId,model);
            return "game_two";
           }

    @RequestMapping("/selectAnswerForGameThree/{questionId}/{optionId}")
    public String selectAnswerForGameThree(@PathVariable("questionId") int questionId,
                                         @PathVariable("optionId") int optionId,
                                         Model model){
        processQuestionAnswers(questionId, optionId,model);
        return "game_three";
    }

    @RequestMapping(value={"/","/showRegistration"})
    public String showRegistrationPage(){
        return "registration";
    }

    @RequestMapping("/registerUser")
    public String registerUser(@RequestParam("username") String username, @RequestParam("password") String password){
        User user = new User(username, passwordEncoder.encode(password));
        userService.save(user);
        return "redirect:login";
    }


}

