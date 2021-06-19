package TugasAkhir.Mobile.movie;

public class Movie {
    private String id;
    private String judul;


    private int gambar;

    public Movie(String id, String judul) {
        this.id = id;
        this.judul = judul;
    }


    public String getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

}
