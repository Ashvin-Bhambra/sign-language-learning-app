package app.entity;

import javax.persistence.*;

@Entity
@Table(name="options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String wordEnglish;
    private String wordFrench;
    private String imagePath;


    public Option(String wordEnglish, String wordFrench, String imagePath) {
        this.wordEnglish = wordEnglish;
        this.wordFrench = wordFrench;
        this.imagePath = imagePath;
    }

    public Option(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWordEnglish() {
        return wordEnglish;
    }

    public void setWordEnglish(String wordEnglish) {
        this.wordEnglish = wordEnglish;
    }

    public String getWordFrench() {
        return wordFrench;
    }

    public void setWordFrench(String wordFrench) {
        this.wordFrench = wordFrench;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
