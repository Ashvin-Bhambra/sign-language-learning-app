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

    private String level;


    @OneToMany(mappedBy="game")
    List<Question> questionList;

    public Game(String level){
        this.questionList = new ArrayList<>();
        this.level=level;
    }

    public Game() {
        this.level = "Easy";
        this.questionList = new ArrayList<>();
    }


    public void setQuestionList(ArrayList<Question> questions){
        this.questionList = questions;
    }
    public List<Question> getQuestionList(){
        return questionList;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getId(){
        return id;
    }
}
