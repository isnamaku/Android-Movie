package TugasAkhir.Mobile.movie;

import java.util.ArrayList;

public class DummyMovie {
    public static ArrayList<Movie> dummyMovie() {
        ArrayList<Movie> dummyMovie = new ArrayList<>();

        dummyMovie.add(
                new Movie(
                        "1",
                        "Pamit"

                )
        );
        dummyMovie.add(
                new Movie(
                        "2",
                        "Monokrom"
                )
        );
        dummyMovie.add(
                new Movie(
                        "3",
                        "Pergilah Kasih"

                )
        );


        return dummyMovie;
    }

}
