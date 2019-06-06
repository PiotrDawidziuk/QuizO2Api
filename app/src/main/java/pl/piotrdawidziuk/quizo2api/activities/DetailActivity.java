package pl.piotrdawidziuk.quizo2api.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import pl.piotrdawidziuk.quizo2api.R;
import pl.piotrdawidziuk.quizo2api.model.QuizListItem;
import pl.piotrdawidziuk.quizo2api.service.QuizO2Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static pl.piotrdawidziuk.quizo2api.activities.MainActivity.EXTRA_ID;
import static pl.piotrdawidziuk.quizo2api.activities.MainActivity.EXTRA_TITLE;

public class DetailActivity extends AppCompatActivity {

    private QuizO2Api quizO2Api;
    TextView quizIdFromRetrofit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String quizId = intent.getStringExtra(EXTRA_ID);
        String quizTitle = intent.getStringExtra(EXTRA_TITLE);

        TextView quizIdTextView = findViewById(R.id.detail_quiz_id);
        TextView quizTitleTextView = findViewById(R.id.detail_quiz_title);
        quizIdFromRetrofit = findViewById(R.id.detail_quiz_id_from_retrofit);

        quizIdTextView.setText(quizId);
        quizTitleTextView.setText(quizTitle);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://quiz.o2.pl/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        quizO2Api = retrofit.create(QuizO2Api.class);

        getQuizById(quizId);


    }

    public void getQuizById(String id){
        Call<QuizListItem> call = quizO2Api.getQuizById(id);
        call.enqueue(new Callback<QuizListItem>() {
            @Override
            public void onResponse(Call<QuizListItem> call, Response<QuizListItem> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(DetailActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }

                QuizListItem quiz = response.body();
                quizIdFromRetrofit.setText(quiz.getId());

            }

            @Override
            public void onFailure(Call<QuizListItem> call, Throwable t) {
                Toast.makeText(DetailActivity.this,"Oops! Something went wrong!",Toast.LENGTH_SHORT).show();

            }
        });
    }

}
