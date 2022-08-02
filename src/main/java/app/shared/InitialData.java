package app.shared;

import app.entity.Option;
import app.entity.Question;
import app.entity.User;
import app.service.OptionService;
import app.service.QuestionService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.annotation.PostConstruct;
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






    @PostConstruct
    public void initializeDatabase(){

        Option option1 = new Option("Apple", "Pomme", "images/apple.png");
        Option option2 = new Option("Orange", "Orange", "images/orange.png");
        Option option3 = new Option("Banana", "Banane", "images/banana.png");
        Option option4 = new Option("Cherry", "Cerise", "images/cherries.png");

        HashSet<Option> optionsForQuestionOne = new HashSet<>();
        optionsForQuestionOne.add(option1);
        optionsForQuestionOne.add(option2);
        optionsForQuestionOne.add(option3);
        optionsForQuestionOne.add(option4);

        Option option5 = new Option("Horse", "Cheval", "images/horse.png");
        Option option6 = new Option("Cat", "Chat", "images/cat.png");
        Option option7 = new Option("Dog", "Chien", "images/dog.png");
        Option option8 = new Option("Pig", "Cochon", "images/pig.png");

        HashSet<Option> optionsForQuestionTwo = new HashSet<>();
        optionsForQuestionTwo.add(option5);
        optionsForQuestionTwo.add(option6);
        optionsForQuestionTwo.add(option7);
        optionsForQuestionTwo.add(option8);

        Option option9 = new Option("Car", "Auto", "images/car.png");
        Option option10 = new Option("Bike", "Bicyclette", "images/bicycle.png");
        Option option11 = new Option("Boat", "Bateau", "images/canoe.png");
        Option option12 = new Option("Plane", "Avion", "images/airplane.png");

        HashSet<Option> optionsForQuestionThree = new HashSet<>();
        optionsForQuestionThree.add(option9);
        optionsForQuestionThree.add(option10);
        optionsForQuestionThree.add(option11);
        optionsForQuestionThree.add(option12);

        saveOptionsFromHashSet(optionsForQuestionOne);
        saveOptionsFromHashSet(optionsForQuestionTwo);
        saveOptionsFromHashSet(optionsForQuestionThree);

        Question questionOne  = new Question("Please click on the apple", optionsForQuestionOne, option1, "");
        Question questionTwo = new Question("Please click on the horse", optionsForQuestionTwo, option5, "");
        Question questionThree = new Question("Please click on the car", optionsForQuestionThree, option9, "");

        questionService.saveQuestion(questionOne);
        questionService.saveQuestion(questionTwo);
        questionService.saveQuestion(questionThree);



    User user = new User("test@gmail.com", passwordEncoder.encode("password"));

    try{
        userRepository.save(user);
    }
    catch(Exception ex){

    }
}
    private void saveOptionsFromHashSet(HashSet<Option> options){
        for(Option option : options){
            optionService.saveOption(option);
        }
    }
}
