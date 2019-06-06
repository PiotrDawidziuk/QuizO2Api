package pl.piotrdawidziuk.quizo2api.service;

import pl.piotrdawidziuk.quizo2api.model.QuizList;
import pl.piotrdawidziuk.quizo2api.model.QuizListItem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface QuizO2Api {

    @GET("quizzes/0/100")
    Call<QuizList> getQuizes();

    @GET("quiz/{id}/0")
    Call<QuizListItem> getQuizById(@Path("id") String id);
}
