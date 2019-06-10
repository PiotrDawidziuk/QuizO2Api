package pl.piotrdawidziuk.quizo2api.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
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
import pl.piotrdawidziuk.quizo2api.service.ResizeImage;

public class TakeQuizActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private ImageView questionImage;
    //private List<Question> questions;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
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

        //mTextMessage.setText(intent.getStringExtra("test"));
        String questionsString ="a";

        ArrayList<Question> list = intent
                .getParcelableArrayListExtra("questions");

//        Toast.makeText(this, list.get(1).getText(), Toast.LENGTH_LONG).show();
        String questionTest = "";

        String imageUrl = "";

       questionTest += list.get(0).getAnswers().get(0).getText();
       imageUrl = list.get(0).getImage().getUrl();

        Glide.with(TakeQuizActivity.this)
                .load(imageUrl).
                apply(new RequestOptions()
                .override(ResizeImage.getWidth(),ResizeImage.getHeight()))
                .fitCenter()
                .into(questionImage);
        mTextMessage.setText(questionTest);

//        mTextMessage.setText(questions.get(0).getText());
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
