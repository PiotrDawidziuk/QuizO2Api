package pl.piotrdawidziuk.quizo2api.model;

import java.util.ArrayList;

public class QuizList {

    private Integer count;
    private ArrayList<QuizListItem> items;

    public QuizList(Integer count, ArrayList<QuizListItem> items) {
        this.count = count;
        this.items = items;
    }

    public Integer getCount() {
        return count;
    }

    public ArrayList<QuizListItem> getItems() {
        return items;
    }
}
