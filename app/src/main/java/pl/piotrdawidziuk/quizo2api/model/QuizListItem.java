package pl.piotrdawidziuk.quizo2api.model;

import android.content.Intent;

public class QuizListItem {

    String id;
    String title;
    MainPhoto mainPhoto;
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public MainPhoto getUrl() {
        return mainPhoto;
    }

    public MainPhoto getMainPhoto() {
        return mainPhoto;
    }

    public QuizListItem(String id, String title, MainPhoto mainPhoto) {
        this.id = id;
        this.title = title;
        this.mainPhoto = mainPhoto;
    }
}
