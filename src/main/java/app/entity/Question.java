package app.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToMany
    private Set<Option> answerOptions;

    @ManyToOne
    private Option correctOption;

    private String videoPath;

    private String questionText;

    private boolean answered;

    @ManyToOne
    @JoinColumn(name ="game_id", nullable=false)
    private Game game;
    @ManyToMany(mappedBy = "questionList", cascade = CascadeType.ALL)
    private List<Game> gameList;

    public Question(String questionText, HashSet<Option> answerOptions, Option correctOption, String videoPath) {
        this.questionText = questionText;
        this.answerOptions = answerOptions;
        this.correctOption = correctOption;
        this.videoPath = videoPath;
        this.answered = false;
    }

    public Question(){
        this.answered = true;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Option> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(HashSet<Option> answerOptions) {
        this.answerOptions = answerOptions;
    }

    public Option getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(Option correctOption) {
        this.correctOption = correctOption;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Game getGame(){
        return game;
    }
    public void setGame(Game game){
        this.game = game;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }
    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", answerOptions=" + answerOptions +
                ", correctOption=" + correctOption +
                ", videoPath='" + videoPath + '\'' +
                ", questionText='" + questionText + '\'' +
                '}';
    }
}
