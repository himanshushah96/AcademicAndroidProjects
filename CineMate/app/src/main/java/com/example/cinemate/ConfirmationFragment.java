package com.example.cinemate;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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


public class ConfirmationFragment extends Fragment implements View.OnClickListener {
    TextView movieNameConfirm, mateNameConfirm;
    ImageView movieImageConfirm;
    Button bookAnother;

    public ConfirmationFragment() {

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
        return inflater.inflate(R.layout.fragment_confirmation, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        Toast.makeText(getActivity().getApplicationContext(),"Your Booking has been Confirmed",Toast.LENGTH_LONG).show();

        movieNameConfirm = view.findViewById(R.id.textView_movieNameConfirm);
        movieImageConfirm = view.findViewById(R.id.imageView_movieImageConfirm);
        mateNameConfirm = view.findViewById(R.id.textView_mateNameConfirm);
        bookAnother=view.findViewById(R.id.button_bookAnother);
        bookAnother.setOnClickListener(this);


        String movieName = bundle.getString("nameconfirm");
        String mateName = bundle.getString("matename");
        String logo = bundle.getString("logoconfirm");


        movieNameConfirm.setText("Movie : " + movieName);
        mateNameConfirm.setText("Name of Mate : " + mateName);
        Picasso.get().load(logo).into(movieImageConfirm);
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
        if (id == R.id.button_bookAnother) {

            NavController navController = Navigation.findNavController(getActivity(), R.id.hostFragment);
            navController.navigate(R.id.moviesFragment);
        }
    }
}