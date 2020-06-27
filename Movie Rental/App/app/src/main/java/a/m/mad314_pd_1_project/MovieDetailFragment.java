package a.m.mad314_pd_1_project;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import a.m.mad314_pd_1_project.requestmodel.LoginRequestModel;
import a.m.mad314_pd_1_project.requestmodel.RentRequestModel;
import a.m.mad314_pd_1_project.responsemodel.LoginResponseModel;
import a.m.mad314_pd_1_project.responsemodel.MovieResponse;
import a.m.mad314_pd_1_project.responsemodel.ResponseModel;
import a.m.mad314_pd_1_project.service.IDataService;
import a.m.mad314_pd_1_project.service.IUserService;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MovieDetailFragment extends Fragment implements View.OnClickListener {


    TextView movieName, movieDesc, movieRatings, movieDuration,movieCategory,movieRentPrice;
    ImageView movieImage,anotherImage;
    ArrayList<MovieResponse> movieArrayList;
    Button rentMovie;
    int movieId;


    public MovieDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @SuppressLint("NewApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        movieName = view.findViewById(R.id.textView_movieNameDetail);
        movieDesc = view.findViewById(R.id.textView_movieDescriptionDetail);
        movieDesc.setMovementMethod(new ScrollingMovementMethod());
        movieImage = view.findViewById(R.id.imageView_movieImageDetail);
        movieDuration = view.findViewById(R.id.textView_movieDurationDetail);
        movieCategory=view.findViewById(R.id.textView_movieCategoryDetail);
       // movieRentPrice=view.findViewById(R.id.textView_movieRentPriceDetail);
        anotherImage= view.findViewById(R.id.imageview_anotherImage);
        rentMovie = view.findViewById(R.id.button_rentMovie);
        movieDesc.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);

        rentMovie.setOnClickListener(this);
        Bundle bundle = getArguments();
        movieId= Integer.parseInt(bundle.getString("id"));
        String name = bundle.getString("name");
        String duration = bundle.getString("duration");
        String desc = bundle.getString("description");
        String logo = bundle.getString("image");
        String category=bundle.getString("category");
        String rentPrice=bundle.getString("rentPrice");
        movieName.setText("Name : " + name);
        movieDuration.setText("Duration : " + duration);
        rentMovie.setText("$" + rentPrice + " Rent");
        movieDesc.setText(desc);
        if(logo != null && !logo.equals("")){
            Picasso.get().load(logo).into(movieImage);
            Picasso.get().load(logo).into(anotherImage);
        }
        movieCategory.setText("Category : "+ category);
        //movieRentPrice.setText("RentPrice : "+ rentPrice);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.overflow_menu, menu);
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button_rentMovie) {
            UserSession session=UserSession.getInstance();
            String userId=session.getUserId() ;
            rentMovie(movieId,userId);
        }
    }
    public void rentMovie(int movieId,String userId)
    {
        final IDataService service = RetrofitClient.getRetrofitInstance().create(IDataService.class);

        RentRequestModel request = new RentRequestModel();
        request.movieId = movieId;
        request.rentedBy = userId;

        Call<ResponseModel<String>> call = service.movieRent(request);

        call.enqueue(new Callback<ResponseModel<String>>() {
            @Override
            public void onResponse(Call<ResponseModel<String>> call, Response<ResponseModel<String>> response) {
                ResponseModel<String> responseModel = response.body();

                if(responseModel != null && responseModel.getError() != null && !responseModel.getError().equals("")){
                    Toast.makeText(getActivity().getApplicationContext(), responseModel.getError(), Toast.LENGTH_LONG).show();
                }else if(responseModel.getResponse()!=null){
                    Toast.makeText(getActivity().getApplicationContext(),responseModel.getResponse(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<String>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });
    }
}
