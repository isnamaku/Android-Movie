package TugasAkhir.Mobile.movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("movie/popular?")
    Call<Respons> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<Respons> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

}
