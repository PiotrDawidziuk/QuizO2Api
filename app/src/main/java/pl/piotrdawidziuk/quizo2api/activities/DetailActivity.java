package pl.piotrdawidziuk.quizo2api.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import pl.piotrdawidziuk.quizo2api.R;
import pl.piotrdawidziuk.quizo2api.model.Quiz;
import pl.piotrdawidziuk.quizo2api.service.QuizO2Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static pl.piotrdawidziuk.quizo2api.activities.MainActivity.EXTRA_ID;
import static pl.piotrdawidziuk.quizo2api.activities.MainActivity.EXTRA_TITLE;
import static pl.piotrdawidziuk.quizo2api.activities.MainActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    private QuizO2Api quizO2Api;
    TextView quizDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String quizId = intent.getStringExtra(EXTRA_ID);
        String quizTitle = intent.getStringExtra(EXTRA_TITLE);
        String imageUrl = intent.getStringExtra(EXTRA_URL);

        ImageView imageView = findViewById(R.id.detail_quiz_image_view);

        TextView quizTitleTextView = findViewById(R.id.detail_quiz_title);
        quizDescription = findViewById(R.id.detail_quiz_id_from_retrofit);

        quizTitleTextView.setText(imageUrl);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://quiz.o2.pl/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        quizO2Api = retrofit.create(QuizO2Api.class);

        getQuizById(quizId);


    }

    public void getQuizById(String id){
        Call<Quiz> call = quizO2Api.getQuizById(id);
        call.enqueue(new Callback<Quiz>() {
            @Override
            public void onResponse(Call<Quiz> call, Response<Quiz> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(DetailActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }

                Quiz quiz = response.body();
                quizDescription.setText(quiz.getContent());

            }

            @Override
            public void onFailure(Call<Quiz> call, Throwable t) {
                Toast.makeText(DetailActivity.this,"Oops! Something went wrong!",Toast.LENGTH_SHORT).show();

            }
        });
    }

}
