package pl.piotrdawidziuk.quizo2api.service;

import pl.piotrdawidziuk.quizo2api.model.QuizList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface QuizO2Api {

    @GET("quizzes/0/100")
    Call<QuizList> getQuizes();
}
