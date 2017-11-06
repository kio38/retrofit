package course.labs.retrofitlab.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import course.labs.retrofitlab.R;
import course.labs.retrofitlab.adapter.MoviesAdapter;
import course.labs.retrofitlab.model.Movie;
import course.labs.retrofitlab.model.MoviesResponse;
import course.labs.retrofitlab.rest.ApiClient;
import course.labs.retrofitlab.rest.ServiceGenerator;
import course.labs.retrofitlab.rest.TMDBInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static course.labs.retrofitlab.rest.ApiClient.BASE_URL;

/**
 * Created by Александр on 06.11.2017.
 */

public class SecondActivity extends AppCompatActivity {
    private final static String API_KEY = "7e8f60e325cd06e164799af1e317d7a7";
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_second);
            String id = getIntent().getExtras().getString("id");
            final TextView infoTextView = (TextView)findViewById(R.id.textView2);
            TMDBInterface apiService = ServiceGenerator.createService(TMDBInterface.class, BASE_URL);
            ApiClient.getClient().create(TMDBInterface.class);
            Call<MoviesResponse> call = apiService.getMovieDetails(Integer.parseInt(id),API_KEY);
        //Обрабатываем ответ от сервера на запрос
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                //код ответа сервера (200 - ОК), в данном случае далее не используется
                int statusCode = response.code();
                //получаем список фильмов, произведя парсинг JSON ответа с помощью библиотеки Retrofi
                //Movie movie = response.body().getResults().get(0);
                String test = response.body().toString();
               // String test = response.raw().toString();
                Log.i("test",test);
               // infoTextView.setText(movie.getTitle());

            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                //onFailure вызывается, когда проблема при отправке запроса. Например, сервер не отвечает или нет сети.
                //Заносим сведения об ошибке в журнал методом Log.e(TAG, MESSAGE)
                //Данный метод используется для журнализации ошибок (e = error)
            }
        });
        }


}
