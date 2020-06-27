package com.example.cinemate;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
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
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class DashboardFragment extends Fragment {


    CineAdapter cineAdapter;
    ArrayList<FriendPojo> friendPojoArrayList;
    View layoutView;
    RecyclerView recyclerView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutView = view;
        DataService service = RetrofitClient.getRetrofitInstance().create(DataService.class);
        Call<ArrayList<FriendPojo>> call = service.getFriends("1", "10");

        call.enqueue(new Callback<ArrayList<FriendPojo>>() {
            @Override
            public void onResponse(Call<ArrayList<FriendPojo>> call, Response<ArrayList<FriendPojo>> response) {
                ArrayList<FriendPojo> friendPojo = response.body();

                friendPojoArrayList = new ArrayList<>(friendPojo);

                cineAdapter = new CineAdapter(friendPojoArrayList, getActivity().getApplicationContext());
                GridLayoutManager manager = new GridLayoutManager(getActivity().getApplicationContext(), 1);
                recyclerView = layoutView.findViewById(R.id.recycleview_dashboard);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(cineAdapter);
                cineAdapter.setOnItemClickListener(onItemClickFriend);
            }

            @Override
            public void onFailure(Call<ArrayList<FriendPojo>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Something Went Wrong!" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public View.OnClickListener onItemClickFriend = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();
            FriendPojo friendPojo = friendPojoArrayList.get(position);
            friendPojo.getId();
            Bundle bundle = new Bundle();
            bundle.putString("matename", friendPojo.getName());


            Navigation.findNavController(v).navigate(R.id.moviesFragment, bundle);

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

    public DashboardFragment() {
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
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }
}

