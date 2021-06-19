package TugasAkhir.Mobile.movie;

import java.util.List;

public class MovieResult {
	private String overview;
	private String originalLanguage;
	private String originalTitle;
	private boolean video;
	private String title;
	private List<Integer> genreIds;
	private String poster_path;
	private String backdropPath;
	private String releaseDate;
	private String popularity;
	private double vote_average;
	private int id;
	private boolean adult;
	private int vote_count;

	public String getOverview(){
		return overview;
	}

	public String getOriginalLanguage(){
		return originalLanguage;
	}

	public String getOriginalTitle(){
		return originalTitle;
	}

	public boolean isVideo(){
		return video;
	}

	public String getTitle(){
		return title;
	}

	public List<Integer> getGenreIds(){
		return genreIds;
	}

	public String getPosterPath(){
		return poster_path;
	}

	public String getBackdropPath(){
		return backdropPath;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public String  getPopularity(){
		return popularity;
	}

	public double getVoteAverage(){
		return vote_average;
	}

	public int getId(){
		return id;
	}

	public boolean isAdult(){
		return adult;
	}

	public int getVoteCount(){
		return vote_count;
	}
}