package pl.piotrdawidziuk.quizo2api.service;

import java.util.List;
import java.util.Map;

import pl.piotrdawidziuk.quizo2api.model.QuizList;
import pl.piotrdawidziuk.quizo2api.model.QuizListItem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface QuizO2Api {

    @GET("quizzes/0/100")
    Call<QuizList> getQuizes();
}
