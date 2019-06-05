package pl.piotrdawidziuk.quizo2api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import pl.piotrdawidziuk.quizo2api.model.QuizList;
import pl.piotrdawidziuk.quizo2api.model.QuizListItem;
import pl.piotrdawidziuk.quizo2api.service.QuizItemAdapter;
import pl.piotrdawidziuk.quizo2api.service.QuizO2Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//notes: https://www.freshbytelabs.com/2018/12/android-recyclerview-with-cardview.html

public class MainActivity extends AppCompatActivity {

    private QuizO2Api quizO2Api;
    private QuizItemAdapter quizItemAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://quiz.o2.pl/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        quizO2Api = retrofit.create(QuizO2Api.class);

        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getQuizes();

    }

    private void getQuizes() {
        Call<QuizList> call = quizO2Api.getQuizes();
        call.enqueue(new Callback<QuizList>() {
            @Override
            public void onResponse(Call<QuizList> call, Response<QuizList> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Oops! Something went wrong!",Toast.LENGTH_SHORT).show();
                }
                QuizList quizList = response.body();
                ArrayList<QuizListItem> quizes = quizList.getItems();

                quizItemAdapter=new QuizItemAdapter(MainActivity.this, quizes);
                recyclerView.setAdapter(quizItemAdapter);

            }

            @Override
            public void onFailure(Call<QuizList> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Oops! Something went wrong!",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
