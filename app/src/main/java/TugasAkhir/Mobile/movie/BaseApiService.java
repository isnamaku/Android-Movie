package TugasAkhir.Mobile.movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


    public interface BaseApiService {

        @GET("movie/popular?")
        Call<Respons> getPopularMovies(@Query("api_key") String apiKey, @Query("language") String language,
                                                                    @Query("page") int page);

        //Details movie
        @GET("movie/{movie_id}")
        Call<MovieResult> getMovieDetails(@Path("movie_id") String id, @Query("api_key") String apiKey);

        @GET("movie/upcoming?")
        Call<Respons> getUpComingMovies(@Query("api_key") String apiKey, @Query("language") String language,
                                       @Query("page") int page);


}
