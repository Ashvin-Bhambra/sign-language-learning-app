package app.service;

import app.entity.Option;
import app.entity.Question;
import app.entity.User;
import app.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private OptionService optionService;

    @Autowired
    private UserService userService;

    public void saveQuestion(Question question){
        questionRepository.save(question);
    }

    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }

    public Question findQuestionById(int id){
        return questionRepository.findById(id).get();
    }

    public boolean checkIfQuestionAnswered(User user, int questionId, int optionId) {
        Question question = findQuestionById(questionId);
        Option selectedOption = optionService.findOptionById(optionId);

        if (question.getCorrectOption().getId() == (selectedOption.getId())) {
            int currentScore = user.getCurrentGameScore();
            currentScore = currentScore + 10;
            user.setCurrentGameScore(currentScore);
            userService.save(user);

            question.setAnswered(true);
            saveQuestion(question);
            return true;

        } else {
            return false;
        }
    }

}
