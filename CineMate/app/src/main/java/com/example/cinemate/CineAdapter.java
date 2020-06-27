package com.example.cinemate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CineAdapter extends RecyclerView.Adapter<CineAdapter.ViewHolder> {

    private View.OnClickListener listener;
    Context context;
    private ArrayList<FriendPojo> friendPojoArrayList;
    private ArrayList<Movie> movieArrayList;

    int test;
    int size = 0;
    boolean isFriend = false;
    boolean isMovie = false;


    public CineAdapter(ArrayList<Movie> movieArrayList, Context context, int test) {
        this.context = context;
        this.movieArrayList = movieArrayList;
        this.test = test;
        isFriend = false;
        isMovie = true;
        size = this.movieArrayList.size();
    }

    public CineAdapter(ArrayList<FriendPojo> friendPojoArrayList, Context context) {
        this.context = context;
        this.friendPojoArrayList = friendPojoArrayList;
        isFriend = true;
        isMovie = false;
        size = this.friendPojoArrayList.size();
    }

    @NonNull
    @Override
    public CineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (isFriend == true) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_friend_item, parent, false);
            return new ViewHolder(view);
        } else if (isMovie == true) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_movie_item, parent, false);
            return new ViewHolder(view);
        }

        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull CineAdapter.ViewHolder holder, int position) {


        if (isFriend) {
            holder.friend_name.setText(this.friendPojoArrayList.get(position).getName());
            holder.friend_phone.setText("Phone : " + this.friendPojoArrayList.get(position).getPhone().substring(0, 10));
            holder.friend_gender.setText(this.friendPojoArrayList.get(position).getGender());
            String date = this.friendPojoArrayList.get(position).getDOB();
            Picasso.get().load(this.friendPojoArrayList.get(position).getImage()).into(holder.friend_image);
            String year = date.substring(0, 4);
            int age = (Calendar.getInstance().get(Calendar.YEAR)) - Integer.parseInt(year);
            String ages = String.valueOf(age);

            holder.friend_age.setText(ages);

        } else if (isMovie) {
            Picasso.get().load(this.movieArrayList.get(position).getMovieImage()).into(holder.movieImage);
            //holder.movieName.setText(this.movieArrayList.get(position).getMovieName());
            //holder.movieReleaseYear.setText(this.movieArrayList.get(position).getReleaseYear().toString());
        }

    }

    @Override
    public int getItemCount() {
        return size;
    }


    public void setOnItemClickListener(View.OnClickListener itemClickListner) {
        listener = itemClickListner;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView friend_image, movieImage;
        TextView friend_name, friend_age, friend_gender, friend_phone;
        TextView movieName, movieReleaseYear, movieDesc, movieRatings, movieRuntime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            friend_image = itemView.findViewById(R.id.imageView_friendImage);
            friend_name = itemView.findViewById(R.id.textView_friendName);
            friend_age = itemView.findViewById(R.id.textView_friendAge);
            friend_gender = itemView.findViewById(R.id.textView_friendGender);
            friend_phone = itemView.findViewById(R.id.textView_friendPhone);

            //movieName=itemView.findViewById(R.id.textView_movieName);
            //movieReleaseYear=itemView.findViewById(R.id.textView_movieReleaseYear);
            movieImage = itemView.findViewById(R.id.imageView_movieImage);


            itemView.setTag(this);
            itemView.setOnClickListener(listener);


        }
    }
}
