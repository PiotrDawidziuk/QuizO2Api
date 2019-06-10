package pl.piotrdawidziuk.quizo2api.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.piotrdawidziuk.quizo2api.R;
import pl.piotrdawidziuk.quizo2api.model.Answer;
import pl.piotrdawidziuk.quizo2api.model.Question;
import pl.piotrdawidziuk.quizo2api.service.AnswersAdapter;
import pl.piotrdawidziuk.quizo2api.service.QuizItemAdapter;
import pl.piotrdawidziuk.quizo2api.service.ResizeImage;

public class TakeQuizActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private ImageView questionImage;
    //private List<Question> questions;
    private RecyclerView recyclerView;
    private AnswersAdapter answersAdapter;
    ArrayList<Question> list;
    int questionNumber;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (questionNumber>0) {
                        questionNumber--;
                        mTextMessage.setText(list.get(questionNumber).getText());
                        getAnswers(questionNumber);
                    }
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    if (questionNumber<list.size()-1) {
                        questionNumber++;
                        mTextMessage.setText(list.get(questionNumber).getText());
                        getAnswers(questionNumber);
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        Intent intent = getIntent();


        mTextMessage = findViewById(R.id.message);
        questionImage = findViewById(R.id.take_quiz_question_image);
        questionNumber = 0;

        list = intent
                .getParcelableArrayListExtra("questions");
        String questionTest = "";

        String imageUrl = "";

       questionTest += list.get(0).getText();
       imageUrl = list.get(0).getImage().getUrl();

        Glide.with(TakeQuizActivity.this)
                .load(imageUrl).
                apply(new RequestOptions()
                .override(ResizeImage.getWidth(),ResizeImage.getHeight()))
                .fitCenter()
                .into(questionImage);
        mTextMessage.setText(questionTest);

        getAnswers(0);



//        mTextMessage.setText(questions.get(0).getText());
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void getAnswers(int questionNumber) {
        recyclerView=findViewById(R.id.take_quiz_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(answersAdapter);
        answersAdapter = new AnswersAdapter (TakeQuizActivity.this, list.get(questionNumber).getAnswers());
        recyclerView.setAdapter(answersAdapter);
    }


}
