package a.m.mad314_pd_1_project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import a.m.mad314_pd_1_project.responsemodel.MovieResponse;
import a.m.mad314_pd_1_project.responsemodel.ResponseModel;
import a.m.mad314_pd_1_project.service.IDataService;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    MovieAdapter adapter;
    ArrayList<MovieResponse> movies;
    View itemView;
    RecyclerView recyclerView;
    TextView emptyMessage;
    IDataService dataService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emptyMessage=view.findViewById(R.id.textView_empty_movieList_message);

        //initRecyclerView(view);

        itemView = view;
        IDataService service = RetrofitClient.getRetrofitInstance().create(IDataService.class);
        Call<ResponseModel<ArrayList<MovieResponse>>> call = service.getMovies();

        call.enqueue(new Callback<ResponseModel<ArrayList<MovieResponse>>>() {
            @Override
            public void onResponse(Call<ResponseModel<ArrayList<MovieResponse>>> call, Response<ResponseModel<ArrayList<MovieResponse>>> response) {
                ResponseModel<ArrayList<MovieResponse>> responseModel = response.body();
                //movies = new ArrayList<Movie>(movie);

                if(responseModel != null && responseModel.getError() != null) {
                    Toast.makeText(getActivity().getApplicationContext(), responseModel.getError(), Toast.LENGTH_LONG).show();
                }else if(responseModel != null && responseModel.getResponse() != null){
                    movies = responseModel.getResponse();
                    if(movies == null || movies.size() == 0){
                        emptyMessage.setVisibility(View.VISIBLE);
                    }else{
                        emptyMessage.setVisibility(View.GONE);
                    }
                    generateRecyclerView(movies, itemView);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<ArrayList<MovieResponse>>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Something Went Wrong!" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    public void generateRecyclerView(ArrayList<MovieResponse> movies, View view){
        adapter = new MovieAdapter(movies,getActivity().getApplication());
        GridLayoutManager gridLayout = new GridLayoutManager(getActivity().getApplicationContext(),2);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_movies);
        recyclerView.setLayoutManager(gridLayout);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(onItemClickMovie);
    }


    public View.OnClickListener onItemClickMovie = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            RecyclerView.ViewHolder viewHolder= (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();
            Bundle bundle = new Bundle();
            MovieResponse movie = movies.get(position);
            bundle.putString("id",movie.getMovieId().toString());
            bundle.putString("image", movie.getImage());
            bundle.putString("name", movie.getMovieName());
            //bundle.putString("cast", movie.getCast());
            bundle.putString("duration", movie.getDuration());
            bundle.putString("description", movie.getDescription());
            bundle.putString("category", movie.getCategoryName());
            bundle.putString("rentPrice", movie.getRentPrice().toString());
            Navigation.findNavController(v).navigate(R.id.movieDetailFragment, bundle);


    }
    };


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.overflow_menu,menu);
        menu.findItem(R.id.logout_menu).setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_menu:
                moveToNewActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void moveToNewActivity () {
        UserSession.getInstance().clearSession();
        Intent i = new Intent(getActivity(), LoginActivity.class);
        startActivity(i);
        ((Activity)getActivity()).finish();
        //((Activity) getActivity()).overridePendingTransition(0, 0);
    }
}
