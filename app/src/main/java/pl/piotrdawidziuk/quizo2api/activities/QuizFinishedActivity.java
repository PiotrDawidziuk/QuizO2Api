package pl.piotrdawidziuk.quizo2api.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pl.piotrdawidziuk.quizo2api.R;

public class QuizFinishedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_finished);
    }
}
