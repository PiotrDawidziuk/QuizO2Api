package pl.piotrdawidziuk.quizo2api.model;

import java.util.ArrayList;

class Question {
    String text;
    ArrayList<Answer> answers;

    public Question(String text, ArrayList<Answer> answers) {
        this.text = text;
        this.answers = answers;
    }

    public String getText() {
        return text;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }
}
