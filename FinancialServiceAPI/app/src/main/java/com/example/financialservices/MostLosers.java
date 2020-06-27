package com.example.financialservices;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


public class MostLosers extends Fragment {

    PopularStockAdapter popularStockAdapter;
    ArrayList<MostLoserStock> mostLoserStocks;
    View layoutView;

    public View.OnClickListener onItemClickpoke = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            RecyclerView.ViewHolder viewHolder= (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();

            MostLoserStock stock =  mostLoserStocks.get(position);
            getCompanyDetails(stock.getTicker(), v);
        }
    };


    public void getCompanyDetails(String companyName, final View view){
        iDataService service = RetrofitClient.getRetrofitInstance().create(iDataService.class);

        Call<CompanyProfile> call = service.getCompanyProfile(companyName);

        call.enqueue(new Callback<CompanyProfile>() {
            @Override
            public void onResponse(Call<CompanyProfile> call, Response<CompanyProfile> response) {

                CompanyProfile companyProfile = response.body();

                try {
                    Bundle bundle = new Bundle();
                    bundle.putString("logo", companyProfile.getProfile().getImage());
                    bundle.putString("name", companyProfile.getProfile().getCompanyName());
                    bundle.putString("ceo", companyProfile.getProfile().getCeo());
                    bundle.putString("description", companyProfile.getProfile().getDescription());
                    bundle.putString("sector", companyProfile.getProfile().getSector());

                    Navigation.findNavController(view).navigate(R.id.companyInfoFragment, bundle);

                }catch (NullPointerException exception){

                }

            }

            @Override
            public void onFailure(Call<CompanyProfile> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Something Went Wrong!" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layoutView=view;

        iDataService service = RetrofitClient.getRetrofitInstance().create(iDataService.class);

        Call<MostLoser> call = service.getMostLoser();

        call.enqueue(new Callback<MostLoser>() {
            @Override
            public void onResponse(Call<MostLoser> call, Response<MostLoser> response) {

                MostLoser mostLoser=response.body();

                try {
                    mostLoserStocks=new ArrayList<>(mostLoser.getMostLoserStock());
                    generateRecyclerView(layoutView,mostLoserStocks);
                }catch (NullPointerException exception){

                }

            }

            @Override
            public void onFailure(Call<MostLoser> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Something Went Wrong!" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    public void generateRecyclerView(View view,ArrayList<MostLoserStock> mostLoserStocks){
        popularStockAdapter = new PopularStockAdapter(mostLoserStocks, getActivity().getApplicationContext(),0);

        GridLayoutManager manager = new GridLayoutManager(getActivity().getApplicationContext(),2);
        //LinearLayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycle_most_losers);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(popularStockAdapter);
        popularStockAdapter.setOnItemClickListner(onItemClickpoke);
    }

    public MostLosers() {
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
        return inflater.inflate(R.layout.fragment_most_losers, container, false);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


}
