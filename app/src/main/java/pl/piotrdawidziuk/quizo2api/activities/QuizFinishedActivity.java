package pl.piotrdawidziuk.quizo2api.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import pl.piotrdawidziuk.quizo2api.R;

public class QuizFinishedActivity extends AppCompatActivity {

    TextView pointsMaxTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_finished);

        pointsMaxTextView = findViewById(R.id.quiz_finished_points_max);

        Intent intent = getIntent();
        int pointsMax = 0;
        pointsMax = intent.getIntExtra("points_max",0);

        pointsMaxTextView.setText(""+pointsMax);


    }
}
