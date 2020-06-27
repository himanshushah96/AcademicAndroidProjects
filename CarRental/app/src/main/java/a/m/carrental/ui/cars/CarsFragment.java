package a.m.carrental.ui.cars;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import a.m.carrental.R;

public class CarsFragment extends Fragment {

    private CarsViewModel carsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        carsViewModel =
                ViewModelProviders.of(this).get(CarsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cars, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        carsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
}
}