package com.example.cinemate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

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

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MovieDetailFragment extends Fragment implements View.OnClickListener {

    TextView movieName, movieDesc, movieRatings, movieYear, movieRuntime;
    ImageView movieImage;
    ArrayList<FriendPojo> friendPojoArrayList;
    ArrayList<Movie> movieArrayList;
    Button bookButton;


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
        movieYear = view.findViewById(R.id.textView_movieReleaseYearDetail);
        movieDesc = view.findViewById(R.id.textView_movieDescription);
        movieDesc.setMovementMethod(new ScrollingMovementMethod());
        movieImage = view.findViewById(R.id.imageView_movieImageDetail);
        movieRatings = view.findViewById(R.id.textView_movieRatings);
        movieRuntime = view.findViewById(R.id.textView_movieRuntime);
        bookButton = view.findViewById(R.id.button_bookmovie);
        movieDesc.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);

        bookButton.setOnClickListener(this);
        Bundle bundle = getArguments();
        String name = bundle.getString("name");
        String runtime = bundle.getString("runtime");
        String releaseYear = bundle.getString("releaseYear");
        String desc = bundle.getString("desc");
        String ratings = bundle.getString("ratings");
        String logo = bundle.getString("logo");
        movieName.setText("Name : " + name);
        movieYear.setText("Year of Release : " + releaseYear);
        movieRuntime.setText("Runtime : " + runtime);
        movieRatings.setText("Ratings : " + ratings);
        movieDesc.setText(desc);
        Picasso.get().load(logo).into(movieImage);

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
                FirebaseAuth.getInstance().signOut();
                NavController navController = Navigation.findNavController(getActivity(), R.id.hostFragment);
                navController.navigate(R.id.loginFragment);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button_bookmovie) {
            Bundle bundle = getArguments();
            Bundle bundle1 = new Bundle();
            bundle1.putString("matename", bundle.getString("matename"));
            bundle1.putString("nameconfirm", bundle.getString("name"));
            bundle1.putString("logoconfirm", bundle.getString("logo"));
            NavController navController = Navigation.findNavController(getActivity(), R.id.hostFragment);
            navController.navigate(R.id.confirmationFragment, bundle1);
        }
    }
}
