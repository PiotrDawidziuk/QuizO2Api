package pl.piotrdawidziuk.quizo2api.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import pl.piotrdawidziuk.quizo2api.R;
import pl.piotrdawidziuk.quizo2api.service.HashMapSaver;

public class QuizFinishedActivity extends AppCompatActivity {

    TextView pointsMaxTextView;
    TextView pointsGainedTextView;
    TextView percetage;
    Button backToMainMenuButton;
    Button restartResultButton;
    public Map<String,Integer> mapOfPoints;
    public  Map<String,Integer> mapOfPositions;
    Integer questionNumber;
    String quizId;
    Integer pointsGained;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_finished);

        pointsMaxTextView = findViewById(R.id.quiz_finished_points_max);
        pointsGainedTextView = findViewById(R.id.quiz_finished_points_gained);
        percetage = findViewById(R.id.quiz_finished_percentage);
        backToMainMenuButton = findViewById(R.id.quiz_finished_back_to_main_menu);
        restartResultButton = findViewById(R.id.quiz_finished_restart_points);

        Intent intent = getIntent();
        int pointsMax = 0;
        int pointsGained =0;
        quizId ="";
        pointsMax = intent.getIntExtra("points_max",0);
        pointsGained = intent.getIntExtra("point_gained",0);
        pointsMaxTextView.setText("Max points: "+pointsMax);
        pointsGainedTextView.setText("You got: "+pointsGained);
        percetage.setText(pointsGained*100/pointsMax+"%");
        quizId = intent.getStringExtra("quiz_id");

        checkHashmaps();

        restartResultButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mapOfPoints.put(quizId,0);
                    mapOfPositions.put(quizId,0);
                    HashMapSaver.saveHashMap("map",mapOfPositions, QuizFinishedActivity.this);
                    HashMapSaver.saveHashMap("map2",mapOfPoints, QuizFinishedActivity.this);
                    Intent intent = new Intent(QuizFinishedActivity.this, MainActivity.class);
                    startActivity(intent);
                }
        });

        backToMainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizFinishedActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void checkHashmaps() {
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
}
