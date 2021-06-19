package TugasAkhir.Mobile.movie;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import TugasAkhir.Mobile.movie.MovieResult;

public class Respons {
	@SerializedName("page")
	int page;

	@SerializedName("results")
	private final List<MovieResult> results = new ArrayList<>();

	@SerializedName("total_results")
	int totalResults;

	@SerializedName("total_pages")
	int totalPages;

	public int getPage(){
		return page;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public List<MovieResult> getResults(){
		return results;
	}

	public int getTotalResults(){
		return totalResults;
	}
}