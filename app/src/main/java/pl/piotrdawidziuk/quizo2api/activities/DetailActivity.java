package pl.piotrdawidziuk.quizo2api.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import pl.piotrdawidziuk.quizo2api.R;

import static pl.piotrdawidziuk.quizo2api.activities.MainActivity.EXTRA_ID;
import static pl.piotrdawidziuk.quizo2api.activities.MainActivity.EXTRA_TITLE;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String quizId = intent.getStringExtra(EXTRA_ID);
        String quizTitle = intent.getStringExtra(EXTRA_TITLE);

        TextView quizIdTextView = findViewById(R.id.detail_quiz_id);
        TextView quizTitleTextView = findViewById(R.id.detail_quiz_title);

        quizIdTextView.setText(quizId);
        quizTitleTextView.setText(quizTitle);

    }
}
