package com.example.cinemate;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class SplashFragment extends Fragment {

    ImageView imageView;
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    public SplashFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Environment.apiHost = getActivity().getApplicationContext().getString(R.string.host);
        Environment.baseUrl = getActivity().getApplicationContext().getString(R.string.api_base_url);


        //imageView = view.findViewById(R.id.imageView_splashLogo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //  NavController navController = Navigation.findNavController(getActivity(),R.id.splashScreenFragment);
                // navController.navigate(R.id.splashScreenFragment_to_LoginFragement);
                NavOptions.Builder navBuilder = new NavOptions.Builder();
                NavOptions navOptions = navBuilder.setPopUpTo(R.id.splashFragment, false).build();
                Navigation.findNavController(getActivity(), R.id.hostFragment).navigate(R.id.splashFragment_to_LoginFragement, null, navOptions);

            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_splash, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


}
