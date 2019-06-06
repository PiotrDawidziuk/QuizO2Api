package pl.piotrdawidziuk.quizo2api.model;

import java.util.ArrayList;

public class Quiz {

    String id;
    MainPhoto mainPhoto;
    ArrayList<Question> questions;
    String content;

    public Quiz(String id, MainPhoto mainPhoto, ArrayList<Question> questions, String content) {
        this.id = id;
        this.mainPhoto = mainPhoto;
        this.questions = questions;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public MainPhoto getMainPhoto() {
        return mainPhoto;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public String getContent() {
        return content;
    }
}
