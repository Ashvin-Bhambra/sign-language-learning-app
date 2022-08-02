package app.service;

import app.entity.Question;
import app.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public void saveQuestion(Question question){
        questionRepository.save(question);
    }

    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }

    public Question findQuestionById(int id){
        return questionRepository.findById(id).get();
    }

}
