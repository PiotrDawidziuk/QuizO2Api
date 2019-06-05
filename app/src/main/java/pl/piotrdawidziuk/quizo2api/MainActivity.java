package pl.piotrdawidziuk.quizo2api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import pl.piotrdawidziuk.quizo2api.model.QuizList;
import pl.piotrdawidziuk.quizo2api.model.QuizListItem;
import pl.piotrdawidziuk.quizo2api.service.QuizO2Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private QuizO2Api quizO2Api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://quiz.o2.pl/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        quizO2Api = retrofit.create(QuizO2Api.class);

        getQuizes();

    }

    private void getQuizes() {
        Call<QuizList> call = quizO2Api.getQuizes();
        call.enqueue(new Callback<QuizList>() {
            @Override
            public void onResponse(Call<QuizList> call, Response<QuizList> response) {
                if(!response.isSuccessful()){
                    textView.setText("Code: "+response);
                }

                QuizList quizList = response.body();

                ArrayList<QuizListItem> quizes = quizList.getItems();

                for (QuizListItem quiz : quizes) {
                    String content = "";
                    content += "ID: " + quiz.getId()+"\n";
                    content += "Title: " + quiz.getTitle()+"\n";
                    content += "-------------------------\n";

                    textView.append(content);
                }

            }

            @Override
            public void onFailure(Call<QuizList> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }
}
