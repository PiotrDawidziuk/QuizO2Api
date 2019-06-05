package pl.piotrdawidziuk.quizo2api.model;

import android.content.Intent;

public class QuizListItem {

    String id;
    String title;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public QuizListItem(String id, String title) {
        this.id = id;
        this.title = title;
    }
}
