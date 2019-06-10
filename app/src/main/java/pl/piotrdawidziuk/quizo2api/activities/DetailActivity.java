package pl.piotrdawidziuk.quizo2api.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pl.piotrdawidziuk.quizo2api.R;
import pl.piotrdawidziuk.quizo2api.model.Question;
import pl.piotrdawidziuk.quizo2api.model.Quiz;
import pl.piotrdawidziuk.quizo2api.service.HashMapSaver;
import pl.piotrdawidziuk.quizo2api.service.QuizO2Api;
import pl.piotrdawidziuk.quizo2api.service.ResizeImage;
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
    TextView quizDescriptionTextView;
    Button takeQuizButton;
    String quizDescription;
    ArrayList<Question> questionArrayList;

    Map<String,Quiz> mapOfQuizes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        final String quizId = intent.getStringExtra(EXTRA_ID);
        final String quizTitle = intent.getStringExtra(EXTRA_TITLE);
        String imageUrl = intent.getStringExtra(EXTRA_URL);

        questionArrayList = new ArrayList<>();

        ImageView imageView = findViewById(R.id.detail_quiz_image_view);

        TextView quizTitleTextView = findViewById(R.id.detail_quiz_title);
        quizDescriptionTextView = findViewById(R.id.detail_quiz_description);
        takeQuizButton = findViewById(R.id.detail_take_quiz_button);

        quizTitleTextView.setText(quizTitle);

        Glide.with(DetailActivity.this)
                .load(imageUrl)
                .apply(new RequestOptions()
                        .override(ResizeImage.getWidth(),ResizeImage.getHeight()))
                .fitCenter()
                .into(imageView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://quiz.o2.pl/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        quizO2Api = retrofit.create(QuizO2Api.class);

        getQuizById(quizId);

        takeQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(DetailActivity.this, TakeQuizActivity.class);
                    intent.putExtra("test", quizDescription);

                    intent.putExtra("questions", questionArrayList);
                    intent.putExtra("id", quizId);

                    startActivity(intent);

            }
        });

    }

    public void getQuizById(final String id) {

        if (HashMapSaver.getQuizListHashMap("quizes", DetailActivity.this) == null) {

            mapOfQuizes = new HashMap<>();
            HashMapSaver.saveHashMap("quizes", mapOfQuizes, DetailActivity.this);

        } else {

            if (HashMapSaver.getQuizHashMap("quizes", this).get(id) == null) {
                Call<Quiz> call = quizO2Api.getQuizById(id);
                call.enqueue(new Callback<Quiz>() {
                    @Override
                    public void onResponse(Call<Quiz> call, Response<Quiz> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(DetailActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }

                        mapOfQuizes = HashMapSaver.getQuizHashMap("quizes",DetailActivity.this);
                        Quiz quiz = response.body();

                        mapOfQuizes.put(id, quiz);
                        HashMapSaver.saveHashMap("quizes", mapOfQuizes, DetailActivity.this);


                        quizDescription = quiz.getContent();
                        questionArrayList = quiz.getQuestions();
                        quizDescriptionTextView.setText(quizDescription);

                    }

                    @Override
                    public void onFailure(Call<Quiz> call, Throwable t) {
                        Toast.makeText(DetailActivity.this, "Oops! Something went wrong!", Toast.LENGTH_SHORT).show();

                    }
                });
            }else {
                mapOfQuizes = HashMapSaver.getQuizHashMap("quizes", DetailActivity.this);

                Quiz quiz = HashMapSaver.getQuizHashMap("quizes", DetailActivity.this).get(id);



                quizDescription = quiz.getContent();
                questionArrayList = quiz.getQuestions();
                quizDescriptionTextView.setText(quizDescription);
            }
        }

    }
}
