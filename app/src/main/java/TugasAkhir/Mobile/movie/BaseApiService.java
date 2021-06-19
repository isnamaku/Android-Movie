package TugasAkhir.Mobile.movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


    public interface BaseApiService {

        @GET("movie/popular?")
        Call<Respons> getPopularMovies(@Query("api_key") String apiKey, @Query("language") String language,
                                                                    @Query("page") int page);

        //Permintaan untuk menampilkan Now Playing Movies
        @GET("movie/now_playing")
        Call<Respons> getNowPlayingMovies(@Query("api_key") String apiKey, @Query("language") String language,
                                           @Query("page") int page);

        //Permintaan untuk menampilkan Upcoming Movies
        @GET("movie/upcoming")
        Call<Respons> getUpcomingMovies(@Query("api_key") String apiKey, @Query("language") String language,
                                         @Query("page") int page);

        //Permintaan untuk menampilkan Top Rated Movies
        @GET("movie/top_rated")
        Call<Respons> getTopRatedMovies(@Query("api_key") String apiKey, @Query("language") String language,
                                         @Query("page") int page);

        //Permintaan untuk pencarian Movie
        @GET("search/movie")
        Call<Respons> searchMovie(@Query("api_key") String apiKey, @Query("language") String language,
                                   @Query("query") String query, @Query("page") int page);

        //Permintaan untuk menampilkan rincian / details movie
        @GET("movie/{movie_id}")
        Call<MovieResult> getMovieDetails(@Path("movie_id") String id, @Query("api_key") String apiKey);



}
