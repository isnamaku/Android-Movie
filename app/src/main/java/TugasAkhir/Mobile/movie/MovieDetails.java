package TugasAkhir.Mobile.movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetails extends AppCompatActivity {

    BaseApiService mApiService;
    int id;
    double realStar;
    String tipe;
    ImageView ivPoster,ivBg;
    RatingBar ratingBar;
    TextView tvTitle, tvRating, tvRelease, tvPopularity, tvStory;
    FloatingActionButton btnSearch;
    List<MovieResult> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);


        mApiService = ApiClient.getClient().create(BaseApiService.class);
        id = getIntent().getIntExtra("id",0);
        tipe = getIntent().getStringExtra("tipe");

        tvTitle = findViewById(R.id.tvName);
        tvPopularity = findViewById(R.id.tvPopularity);
        tvRating = findViewById(R.id.tvRating);
        tvRelease = findViewById(R.id.tvRelease);
        tvStory = findViewById(R.id.tvOverview);
        btnSearch = findViewById(R.id.search);
        ivPoster = findViewById(R.id.imgPhoto);
        ivBg = findViewById(R.id.imgCover);
        ratingBar = findViewById(R.id.ratingBar);


        setMovieDetails();
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void setMovieDetails() {
        Call<MovieResult> call = mApiService.getMovieDetails(String.valueOf(id), HomeFragment.API_KEY);
        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, final Response<MovieResult> response) {
                if(response.body() != null){
                    tvTitle.setText(response.body().getTitle());
                    Picasso.get()
                            .load("https://image.tmdb.org/t/p/w500"+response.body().getPosterPath())
                            .placeholder(R.mipmap.iconmovie)
                            .error(R.mipmap.iconmovie)
                            .into(ivPoster);
                    Picasso.get()
                            .load("https://image.tmdb.org/t/p/original"+response.body().getBackdropPath())
                            .placeholder(R.mipmap.iconmovie)
                            .error(R.mipmap.iconmovie)
                            .into(ivBg);
                    tvPopularity.setText(response.body().getPopularity());
                    tvRating.setText(response.body().getVoteAverage()+" / 10");
                    tvStory.setText(response.body().getOverview());
                    tvRelease.setText(response.body().getReleaseDate());
                    realStar = response.body().getVoteAverage()/2;
                    ratingBar.setRating((float) realStar);

                    final Intent intentTrailer = new Intent(Intent.ACTION_VIEW);
                    btnSearch.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            intentTrailer.setData(Uri.parse("https://www.youtube.com/results?search_query=trailer"+response.body().getTitle()));
                            startActivity(intentTrailer);
                        }
                    });


                }
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

}