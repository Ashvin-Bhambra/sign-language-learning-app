package app.shared;

import app.entity.Game;
import app.entity.Option;
import app.entity.Question;
import app.entity.User;
import app.service.GameService;
import app.service.OptionService;
import app.service.QuestionService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

@Configuration
public class InitialData {

    @Autowired
    QuestionService questionService;

    @Autowired
    OptionService optionService;

    @Autowired
    private UserService userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GameService gameService;

    @PostConstruct
    public void initializeDatabase() {

        Option option1 = new Option("Apple", "Pomme", "/images/apple.png");
        Option option2 = new Option("Orange", "Orange", "/images/orange.png");
        Option option3 = new Option("Banana", "Banane", "/images/banana.png");
        Option option4 = new Option("Cherry", "Cerise", "/images/cherries.png");

        HashSet<Option> optionsForQuestionOne = new HashSet<>();
        optionsForQuestionOne.add(option1);
        optionsForQuestionOne.add(option2);
        optionsForQuestionOne.add(option3);
        optionsForQuestionOne.add(option4);

        Option option5 = new Option("Horse", "Cheval", "/images/horse.png");
        Option option6 = new Option("Cat", "Chat", "/images/cat.png");
        Option option7 = new Option("Dog", "Chien", "/images/dog.png");
        Option option8 = new Option("Pig", "Cochon", "/images/pig.png");

        HashSet<Option> optionsForQuestionTwo = new HashSet<>();
        optionsForQuestionTwo.add(option5);
        optionsForQuestionTwo.add(option6);
        optionsForQuestionTwo.add(option7);
        optionsForQuestionTwo.add(option8);

        Option option9 = new Option("Car", "Auto", "/images/car.png");
        Option option10 = new Option("Bike", "Bicyclette", "/images/bicycle.png");
        Option option11 = new Option("Boat", "Bateau", "/images/canoe.png");
        Option option12 = new Option("Plane", "Avion", "/images/airplane.png");

        HashSet<Option> optionsForQuestionThree = new HashSet<>();
        optionsForQuestionThree.add(option9);
        optionsForQuestionThree.add(option10);
        optionsForQuestionThree.add(option11);
        optionsForQuestionThree.add(option12);

        saveOptionsFromHashSet(optionsForQuestionOne);
        saveOptionsFromHashSet(optionsForQuestionTwo);
        saveOptionsFromHashSet(optionsForQuestionThree);

        Game gameOne = new Game("Easy");

        Question questionOne = new Question("Please click on the apple", optionsForQuestionOne, option1, "/videos/APPLE.mov");
        Question questionTwo = new Question("Please click on the horse", optionsForQuestionTwo, option5, "/videos/HORSE.mov");
        Question questionThree = new Question("Please click on the car", optionsForQuestionThree, option9, "/videos/CAR.mov");

        ArrayList<Question> questionsForGame1 = new ArrayList<>();
        questionsForGame1.add(questionOne);
        questionsForGame1.add(questionTwo);
        questionsForGame1.add(questionThree);

        gameOne.setQuestionList(questionsForGame1);
        questionService.saveQuestion(questionOne);
        questionService.saveQuestion(questionTwo);
        questionService.saveQuestion(questionThree);

        gameService.saveGame(gameOne);

        questionOne.setGame(gameOne);
        questionTwo.setGame(gameOne);
        questionThree.setGame(gameOne);

        Game gameTwo = new Game("Intermediate");
        gameTwo.setQuestionList(questionsForGame1);
        gameService.saveGame(gameTwo);

        ////////////////////////////////////////////////////////////////////////////////////////////////

        Game gameThree = new Game("Advanced");

        HashSet<Option> optionsForGameThreeQuesitonOne = new HashSet<>();
        HashSet<Option> optionsForGameThreeQuesitonTwo = new HashSet<>();
        HashSet<Option> optionsForGameThreeQuesitonThree = new HashSet<>();



        Option option1ForGame3 = new Option("I eat an apple", "J'ai mange une pomme", "/images/apple.png");
        Option option2ForGame3 = new Option("I buy an apple", "j'achète une pomme", "/images/apple.png");
        Option option3ForGame3 = new Option("I like apples", "J'aime les pommes", "/images/apple.png");

        Option option4ForGame3 = new Option("I ride a horse", "Je monte à cheval", "/images/apple.png");
        Option option5ForGame3 = new Option("I eat a horse", "Je mange un cheval", "/images/apple.png");
        Option option6ForGame3 = new Option("I love horses", "J'aime les chevaux", "/images/apple.png");

        Option option7ForGame3 = new Option("I drive a car", "Je conduis une voiture", "/images/apple.png");
        Option option8ForGame3 = new Option("I steal a car", "je vole une voiture", "/images/apple.png");
        Option option9ForGame3 = new Option("I damage a car", "J'endommage une voiture", "/images/apple.png");



        Question questionOneGameThree = new Question("Pick a sentence based on the video", optionsForGameThreeQuesitonOne, option1ForGame3, "/videos/eat-apple.mp4");
        Question questionTwoGameThree = new Question("Pick a sentence based on the video", optionsForGameThreeQuesitonTwo, option4ForGame3, "/videos/ride-horse.mp4");
        Question questionThreeGameThree = new Question("Pick a sentence based on the video", optionsForGameThreeQuesitonThree, option7ForGame3, "/videos/drive-car.mp4");



        optionsForGameThreeQuesitonOne.add(option1ForGame3);
        optionsForGameThreeQuesitonOne.add(option2ForGame3);
        optionsForGameThreeQuesitonOne.add(option3ForGame3);
        saveOptionsFromHashSet(optionsForGameThreeQuesitonOne);

        optionsForGameThreeQuesitonTwo.add(option4ForGame3);
        optionsForGameThreeQuesitonTwo.add(option5ForGame3);
        optionsForGameThreeQuesitonTwo.add(option6ForGame3);
        saveOptionsFromHashSet(optionsForGameThreeQuesitonTwo);

        optionsForGameThreeQuesitonThree.add(option7ForGame3);
        optionsForGameThreeQuesitonThree.add(option8ForGame3);
        optionsForGameThreeQuesitonThree.add(option9ForGame3);
        saveOptionsFromHashSet(optionsForGameThreeQuesitonThree);


        ArrayList<Question> questionForGameThree = new ArrayList<>();
        questionForGameThree.add(questionOneGameThree);
        questionForGameThree.add(questionTwoGameThree);
        questionForGameThree.add(questionThreeGameThree);

        gameThree.setQuestionList(questionForGameThree);

        questionService.saveQuestion(questionOneGameThree);
        questionService.saveQuestion(questionTwoGameThree);
        questionService.saveQuestion(questionThreeGameThree);

        questionOneGameThree.setGame(gameThree);
        questionTwoGameThree.setGame(gameThree);
        questionThreeGameThree.setGame(gameThree);


        gameService.saveGame(gameTwo);
        gameService.saveGame(gameThree);

        questionService.saveQuestion(questionOneGameThree);
        questionService.saveQuestion(questionTwoGameThree);
        questionService.saveQuestion(questionThreeGameThree);


    }

    private void saveOptionsFromHashSet(HashSet<Option> options) {
        for (Option option : options) {
            optionService.saveOption(option);
        }
    }
}
