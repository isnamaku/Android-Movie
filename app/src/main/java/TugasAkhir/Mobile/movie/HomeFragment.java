package TugasAkhir.Mobile.movie;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {


    ProgressDialog loading;
    private List<MovieResult> moviePopularResult = new ArrayList<>();
    BaseApiService mApiService;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    public static final String API_KEY = "e6bb64e1c930a688fb64df291debff39";
    public static final String LANGUAGE = "en-US";
    private int currentPageMoviePopular = 1;
    private int totalPagesMoviePopular = 1;
    LinearLayoutManager linearLayoutManager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
                Retrofit retrofit = ApiClient.getClient();
        mApiService = retrofit.create(BaseApiService.class);
        recyclerView = view.findViewById(R.id.rvMovie);
        linearLayoutManager = new LinearLayoutManager(getContext());

        setPopularMovies();

    }

    private void setPopularMovies() {
        recyclerView = recyclerView.findViewById(R.id.rvMovie);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        Toast toast = Toast.makeText(getContext(), "Loading all popular movies", Toast.LENGTH_SHORT);
        toast.show();
        getPopularMovies();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(2)) {
                    if (dy > 0) {
                        if (currentPageMoviePopular <= 10) {
                            currentPageMoviePopular += 1;
                        }
                    } else if(dy<0){

                        currentPageMoviePopular -= 1;
                        linearLayoutManager.getReverseLayout();

                    }
                    getPopularMovies();
                }
            }

        });

    }

    private void getPopularMovies() {
        Call<Respons> call = mApiService.getPopularMovies(API_KEY, LANGUAGE, currentPageMoviePopular);
        call.enqueue(new Callback<Respons>() {
            @Override
            public void onResponse(@NonNull Call<Respons> call, @NonNull Response<Respons> response) {
                if (response.body() != null) {
                    totalPagesMoviePopular = response.body().getTotalPages();
                    if (response.body().getResults() != null) {
                        int oldCount = moviePopularResult.size();
                        moviePopularResult.addAll(response.body().getResults());
                        Log.d("Retrofit Get", "Number of movies : " +
                                String.valueOf(moviePopularResult.size()));
                        List<MovieResult> movies = response.body().getResults();
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(new MovieAdapter(movies, R.layout.row, getContext()));


                    }
                }
            }
            @Override
            public void onFailure(Call<Respons> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

}










