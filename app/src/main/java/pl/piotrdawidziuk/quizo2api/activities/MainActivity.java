package pl.piotrdawidziuk.quizo2api.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import pl.piotrdawidziuk.quizo2api.R;
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

public class MainActivity extends AppCompatActivity implements QuizItemAdapter.ItemClickListener {

    public static final String EXTRA_ID = "id";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_URL = "url";

    private QuizO2Api quizO2Api;
    private QuizItemAdapter quizItemAdapter;
    RecyclerView recyclerView;
    ArrayList<QuizListItem> quizes;

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
                quizes = quizList.getItems();

                quizItemAdapter=new QuizItemAdapter(MainActivity.this, quizes);
                recyclerView.setAdapter(quizItemAdapter);

                quizItemAdapter.setClickListener(MainActivity.this);

            }

            @Override
            public void onFailure(Call<QuizList> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Oops! Something went wrong!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {


        Intent detailIntent = new Intent(this, DetailActivity.class);
        QuizListItem clickedItem = quizes.get(position);

        detailIntent.putExtra(EXTRA_ID, clickedItem.getId());
        detailIntent.putExtra(EXTRA_TITLE, clickedItem.getTitle());
        startActivity(detailIntent);

        Toast.makeText(this, "POSITION: " + position, Toast.LENGTH_SHORT).show();
    }
}
