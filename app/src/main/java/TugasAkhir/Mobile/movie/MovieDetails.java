package TugasAkhir.Mobile.movie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MovieDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        int id = getIntent().getIntExtra("id",0);


    }
}