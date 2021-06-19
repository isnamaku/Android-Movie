package TugasAkhir.Mobile.movie;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<MovieResult> results;
    private final Context context;
    private int rowLayout;


    public MovieAdapter(List<MovieResult>  results, int rowLayout, Context context){
        this.results = results;
       this.context = context;
       this.rowLayout = rowLayout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
//        holder.bindItem(results.get(position));
        holder.tvJudul.setText(results.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvJudul;
        LinearLayout moviesLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJudul = itemView.findViewById(R.id.MovieTitle);

        }


        public void bindItem(MovieResult movieResult) {
                tvJudul.setText(movieResult.getOriginalTitle());
        }

        }
    }

