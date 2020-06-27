package com.example.cinemate;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class MoviesFragment extends Fragment {

    CineAdapter cineAdapter;
    ArrayList<Movie> movieArrayList;
    View layoutView;
    RecyclerView recyclerView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutView = view;
        DataService service = RetrofitClient.getRetrofitInstance().create(DataService.class);
        Call<ArrayList<Movie>> call = service.getMovies();

        call.enqueue(new Callback<ArrayList<Movie>>() {
            @Override
            public void onResponse(Call<ArrayList<Movie>> call, Response<ArrayList<Movie>> response) {
                ArrayList<Movie> movie = response.body();
                movieArrayList = new ArrayList<>(movie);

                cineAdapter = new CineAdapter(movie, getActivity().getApplicationContext(), 1);
                GridLayoutManager manager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
                recyclerView = layoutView.findViewById(R.id.recycleview_movies);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(cineAdapter);
                cineAdapter.setOnItemClickListener(onItemClickMovies);
            }

            @Override
            public void onFailure(Call<ArrayList<Movie>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Something Went Wrong!" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    public View.OnClickListener onItemClickMovies = new View.OnClickListener() {

        @Override
        public void onClick(final View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();
            Movie movie = movieArrayList.get(position);
            movie.getMovieId();

            Bundle bundle1 = getArguments();


            Bundle bundle = new Bundle();
            bundle.putString("name", movie.getMovieName());
            bundle.putString("runtime", movie.getRunTime());
            bundle.putString("releaseYear", movie.getReleaseYear().toString());
            bundle.putString("desc", movie.getMovieDesc());
            bundle.putString("ratings", movie.getRatings().toString());
            bundle.putString("logo", movie.getMovieImage());
            bundle.putString("matename", bundle1.getString("matename"));

            Navigation.findNavController(v).navigate(R.id.movieDetailFragment, bundle);


        }
    };


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.overflow_menu, menu);
        menu.findItem(R.id.logout_menu).setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_menu:
                FirebaseAuth.getInstance().signOut();
                NavController navController = Navigation.findNavController(getActivity(), R.id.hostFragment);
                navController.navigate(R.id.loginFragment);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


}
