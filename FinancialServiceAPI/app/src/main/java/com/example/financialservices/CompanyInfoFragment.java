package com.example.financialservices;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class CompanyInfoFragment extends Fragment {

    TextView textView_ceo, textView_name, textView_description, textView_sector;
    ImageView logo;

    public CompanyInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        textView_ceo = view.findViewById(R.id.ceo);
        textView_description = view.findViewById(R.id.description);
        textView_name = view.findViewById(R.id.companyName);
        textView_sector = view.findViewById(R.id.sectorText);
        logo = view.findViewById(R.id.imageCompany);
        String name = bundle.getString("name");
        String logoString = bundle.getString("logo");
        String description = bundle.getString("description");
        String sector = bundle.getString("sector");
        String ceo = bundle.getString("ceo");

        textView_sector.setText(sector);
        textView_name.setText(name);
        textView_description.setText(description);
        textView_ceo.setText(ceo);
        Picasso.get().load(logoString).into(logo);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_company_info, container, false);
    }
}
