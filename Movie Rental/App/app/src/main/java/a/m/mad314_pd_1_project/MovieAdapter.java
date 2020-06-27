package a.m.mad314_pd_1_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import a.m.mad314_pd_1_project.responsemodel.MovieResponse;
import a.m.mad314_pd_1_project.responsemodel.RentedListResponse;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private ArrayList<MovieResponse> movies;
    private ArrayList<RentedListResponse> rentedMovies;
    private View.OnClickListener movieListener;
    int test=0;
    boolean isMovie=false;
    boolean isRented=false;
    int size=0;



    public MovieAdapter(ArrayList<MovieResponse> movies, Context c) {
        this.movies = movies;
        isMovie=true;
        size=this.movies.size();
    }

    public MovieAdapter(ArrayList<RentedListResponse> rentedMovies,Context c,int test)
    {
        this.rentedMovies=rentedMovies;
        this.test=test;
        isRented=true;
        size=this.rentedMovies.size();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(isMovie==true) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_item, parent, false);
            return new ViewHolder(view);
        }
        else if(isRented==true)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_rentedmovie, parent, false);
            return new ViewHolder(view);
        }
        return null;
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListner)
    {

        movieListener = itemClickListner;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(isMovie) {
            if(movies.get(position).getImage() != null && !movies.get(position).getImage().equals("")){
                Picasso.get().load(movies.get(position).getImage()).into(holder.movie_image);
            }
          //  holder.movie_name.setText(movies.get(position).getMovieName());
          //  holder.movie_duration.setText(movies.get(position).getDuration());
          //  holder.movie_category.setText(movies.get(position).getCategoryName());
        }

        else if(isRented)
        {
            Picasso.get().load(rentedMovies.get(position).getImage()).into(holder.movie_imageRL);
            holder.movie_nameRL.setText(rentedMovies.get(position).getMovieName());
            holder.movie_rentPriceRL.setText("Price : "+rentedMovies.get(position).getRentPrice().toString());
            holder.movie_rentDateRL.setText("Rented On : "+rentedMovies.get(position).getRentedDate().substring(0,10));
            holder.movie_dueDateRL.setText("DueDate : "+rentedMovies.get(position).getDueDate().substring(0,10));

        }
    }


    @Override
    public int getItemCount() {
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView movie_image,movie_imageRL;
        TextView movie_name,movie_nameRL,movie_rentPriceRL,movie_rentDateRL,movie_dueDateRL;
        TextView movie_category;
        TextView movie_duration;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            movie_imageRL=itemView.findViewById(R.id.movie_image_RL);
            movie_nameRL=itemView.findViewById(R.id.movie_name_RL);
            movie_rentPriceRL=itemView.findViewById(R.id.movie_rentPrice_RL);
            movie_rentDateRL=itemView.findViewById(R.id.rentedDate_RL);
            movie_dueDateRL=itemView.findViewById(R.id.dueDate_RL);

            movie_image = itemView.findViewById(R.id.imageView_movieIcon);
         //   movie_name = itemView.findViewById(R.id.movie_name);
         //   movie_duration = itemView.findViewById(R.id.movie_duration);
         //   movie_category = itemView.findViewById(R.id.movie_category);

            itemView.setTag(this);
            itemView.setOnClickListener(movieListener);



        }
    }

}
