package a.m.mad314_pd_1_project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import a.m.mad314_pd_1_project.requestmodel.RentRequestModel;
import a.m.mad314_pd_1_project.responsemodel.LoginResponseModel;
import a.m.mad314_pd_1_project.responsemodel.MovieResponse;
import a.m.mad314_pd_1_project.responsemodel.RentedListResponse;
import a.m.mad314_pd_1_project.responsemodel.ResponseModel;
import a.m.mad314_pd_1_project.service.IDataService;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class RentedMovieListFragment extends Fragment {

    MovieAdapter adapter;
    ArrayList<RentedListResponse> movies;
    View itemView;
    RecyclerView recyclerView;
    TextView emptyMessage;
    IDataService dataService;


    public RentedMovieListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rented_movie_list, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emptyMessage=view.findViewById(R.id.textView_empty_movieList_message);
        UserSession session=UserSession.getInstance();
        String userId=session.getUserId() ;

        RentRequestModel rentRequestModel=new RentRequestModel();
        rentRequestModel.rentedBy=userId;
        itemView = view;
        IDataService service = RetrofitClient.getRetrofitInstance().create(IDataService.class);
        Call<ResponseModel<ArrayList<RentedListResponse>>> call = service.getRentedMovies(userId);

        call.enqueue(new Callback<ResponseModel<ArrayList<RentedListResponse>>>() {
            @Override
            public void onResponse(Call<ResponseModel<ArrayList<RentedListResponse>>> call, Response<ResponseModel<ArrayList<RentedListResponse>>> response) {
                ResponseModel<ArrayList<RentedListResponse>> responseModel = response.body();
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
            public void onFailure(Call<ResponseModel<ArrayList<RentedListResponse>>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Something Went Wrong!" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    public void generateRecyclerView(ArrayList<RentedListResponse> movies, View view){
        adapter = new MovieAdapter(movies,getActivity().getApplication(),0);
        GridLayoutManager gridLayout = new GridLayoutManager(getActivity().getApplicationContext(),1);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_rentedMovies);
        recyclerView.setLayoutManager(gridLayout);
        recyclerView.setAdapter(adapter);
        //adapter.setOnItemClickListener();

    }

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

        Intent i = new Intent(getActivity(), LoginActivity.class);
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0, 0);

    }
}
