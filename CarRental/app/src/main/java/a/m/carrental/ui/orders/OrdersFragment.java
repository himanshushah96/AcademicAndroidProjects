package a.m.carrental.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

import a.m.carrental.R;
import a.m.carrental.db.Car;
import a.m.carrental.db.DbModel;
import a.m.carrental.model.CarModel;
import a.m.carrental.model.Constants;
import a.m.carrental.model.Session;
import a.m.carrental.ui.home.RecyclerViewDataAdapter;

public class OrdersFragment extends Fragment {

    private OrdersViewModel galleryViewModel;
    private DbModel db;
//    private void loadOrders()
//    {
//        DbModel db = DbModel.getInstance();
//
//    }

    RecyclerViewDataAdapter dataAdapter;
    RecyclerView recyclerView;
    ArrayList<Car> booking;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        db=DbModel.getInstance();
        CarModel carModel = new CarModel();
        booking = carModel.getUserBookedCar();
        //loadOrders();
        View root = inflater.inflate(R.layout.fragment_order, container, false);


        recyclerView=root.findViewById(R.id.recyclerView_order);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(gridLayoutManager);
        dataAdapter = new RecyclerViewDataAdapter(booking,R.layout.activity_card_view_order);
        recyclerView.setAdapter(dataAdapter);

        dataAdapter.notifyDataSetChanged();
        return root;
    }



    @Override
    public void onResume() {
        booking.clear();
        booking.addAll(new CarModel().getUserBookedCar());
        dataAdapter.notifyDataSetChanged();
        super.onResume();
    }
}