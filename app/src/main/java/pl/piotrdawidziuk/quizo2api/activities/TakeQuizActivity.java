package pl.piotrdawidziuk.quizo2api.activities;

import android.arch.core.executor.TaskExecutor;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.piotrdawidziuk.quizo2api.R;
import pl.piotrdawidziuk.quizo2api.model.Answer;
import pl.piotrdawidziuk.quizo2api.model.Question;
import pl.piotrdawidziuk.quizo2api.service.AnswersAdapter;
import pl.piotrdawidziuk.quizo2api.service.HashMapSaver;
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
    ProgressBar progressBar;
    String imageUrl;
    int pointsGained;
    int pointsMax;
    public  Map<String,Integer> mapOfPositions;
    public  Map<String,Integer> mapOfPoints;
    private String quizId;


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
                        setQuestionImage(questionNumber);
                        progressBar.setProgress(questionNumber);
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
                        setQuestionImage(questionNumber);
                        progressBar.setProgress(questionNumber);
                        mapOfPositions.put(quizId,questionNumber);
                        HashMapSaver.saveHashMap("map",mapOfPositions,TakeQuizActivity.this);
                        if (AnswersAdapter.RIGHT_ANSWER_IS_SELECTED){
                            pointsGained++;
                            mapOfPoints.put(quizId,pointsGained);
                            HashMapSaver.saveHashMap("map2",mapOfPoints,TakeQuizActivity.this);
                            AnswersAdapter.RIGHT_ANSWER_IS_SELECTED = false;
                        }

                    } else {
                        Intent intent = new Intent(TakeQuizActivity.this, QuizFinishedActivity.class);
                        if (AnswersAdapter.RIGHT_ANSWER_IS_SELECTED){
                            pointsGained++;
                            mapOfPoints.put(quizId,pointsGained);
                            HashMapSaver.saveHashMap("map2",mapOfPoints, TakeQuizActivity.this);
                            AnswersAdapter.RIGHT_ANSWER_IS_SELECTED = false;
                        }

                        intent.putExtra("point_gained",pointsGained);
                        intent.putExtra("points_max",pointsMax);
                        intent.putExtra("quiz_id",quizId);

                        startActivity(intent);
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

        quizId = intent.getStringExtra("id");

        checkHashMaps();

        mTextMessage = findViewById(R.id.message);
        questionImage = findViewById(R.id.take_quiz_question_image);
        progressBar = findViewById(R.id.simpleProgressBar);
        progressBar.setProgress(questionNumber);

        list = intent
                .getParcelableArrayListExtra("questions");
        String questionTest = "";
        imageUrl = "";


       questionTest += list.get(HashMapSaver.getHashMap("map",this).get(quizId)).getText();

       setQuestionImage(HashMapSaver.getHashMap("map",this).get(quizId));

        mTextMessage.setText(questionTest);
        progressBar.setMax(list.size());
        pointsMax = list.size();

        getAnswers(HashMapSaver.getHashMap("map",this).get(quizId));

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void checkHashMaps() {

        if (HashMapSaver.getHashMap("map",this)==null) {
            mapOfPositions = new HashMap<>();
            HashMapSaver.saveHashMap("map",mapOfPositions,this);
        } else {
            mapOfPositions = HashMapSaver.getHashMap("map",this);
        }

        if (HashMapSaver.getHashMap("map2",this)==null) {
            mapOfPoints = new HashMap<>();
            HashMapSaver.saveHashMap("map2",mapOfPoints,this);

        } else {
            mapOfPoints = HashMapSaver.getHashMap("map2",this);
        }


        if (HashMapSaver.getHashMap("map",this).get(quizId) == null){
            mapOfPositions.put(quizId,0);
            HashMapSaver.saveHashMap("map", mapOfPositions,this);
            Toast.makeText(this, "ojej null", Toast.LENGTH_SHORT).show();
        } else {
            questionNumber = HashMapSaver.getHashMap("map",this).get(quizId);
        }

        if (HashMapSaver.getHashMap("map2",this).get(quizId) == null){
            mapOfPoints.put(quizId,0);
            HashMapSaver.saveHashMap("map2", mapOfPoints,this);
        } else {
            pointsGained = HashMapSaver.getHashMap("map2",this).get(quizId);
        }
    }

    private void setQuestionImage(int questionNumber) {

        imageUrl = list.get(questionNumber).getImage().getUrl();

        Glide.with(TakeQuizActivity.this)
                .load(imageUrl).
                apply(new RequestOptions()
                        .override(ResizeImage.getWidth(),ResizeImage.getHeight()))
                .fitCenter()
                .into(questionImage);

    }

    private void getAnswers(int questionNumber) {
        recyclerView=findViewById(R.id.take_quiz_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(answersAdapter);
        answersAdapter = new AnswersAdapter (TakeQuizActivity.this, list.get(questionNumber).getAnswers());
        recyclerView.setAdapter(answersAdapter);
    }






}
