package pl.piotrdawidziuk.quizo2api.model;

import android.content.Intent;

public class QuizListItem {

    String id;
    String title;
    String url;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public QuizListItem(String id, String title,String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }
}
