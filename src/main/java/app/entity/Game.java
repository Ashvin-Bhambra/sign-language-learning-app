package app.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "game_id")
    private int id;


    @OneToMany(mappedBy="game")
    List<Question> questionList;

    public Game(){
        this.questionList = new ArrayList<>();
    }

    public void setQuestionList(ArrayList<Question> questions){
        this.questionList = questions;
    }
    public List<Question> getQuestionList(){
        return questionList;
    }

}
