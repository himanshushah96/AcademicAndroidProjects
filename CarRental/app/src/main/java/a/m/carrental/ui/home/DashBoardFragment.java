package a.m.carrental.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

import a.m.carrental.model.Constants;
import a.m.carrental.ui.customers.CarDialogBox;
import a.m.carrental.ui.customers.CustomerFragment;
import a.m.carrental.ui.customers.CustomerViewModel;
import a.m.carrental.ui.customers.DialogBox;
import a.m.carrental.ui.orders.OrdersFragment;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import a.m.carrental.R;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

public class DashBoardFragment extends  Fragment {

    FragmentManager fragmentManager;

    public DashBoardFragment(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        CardView listOfCustomer = root.findViewById(R.id.cardView_listofcustomer);
        CardView listOfEmployee = root.findViewById(R.id.cardView_listofemployee);
        CardView listOfCars = root.findViewById(R.id.cardView_listofcars);
        CardView todayReturn = root.findViewById(R.id.cardView_todaysReturn);
        ImageView addCustomer = root.findViewById(R.id.button_listofcustomer);
        ImageView addEmployee = root.findViewById(R.id.button_listofemployee);
        ImageView addCars = root.findViewById(R.id.button_listofcars);
        ImageView addTransaction = root.findViewById(R.id.button_transaction);


        addCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragmentManager!=null) {
                    DialogBox dialogBox = new DialogBox("Add Customer", Constants.USER_TYPE_CUSTOMER);
                    dialogBox.show(fragmentManager, "Dialog box");
                }
            }
        });

        addEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragmentManager!=null) {
                    DialogBox dialogBox = new DialogBox("Add Employee", Constants.USER_TYPE_EMPLOYEE);
                    dialogBox.show(fragmentManager, "Dialog box");
                }
            }
        });

        addCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragmentManager!=null) {
                    CarDialogBox carDialogBox = new CarDialogBox("Add Car");
                    carDialogBox.show(fragmentManager,"Car Dialog box");
                }
            }
        });


        final Intent listIntent = new Intent(getActivity(),CustomerFragment.class);

        listOfCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, new CustomerFragment(fragmentManager, Constants.USER_TYPE_CUSTOMER))
                        .addToBackStack(null)
                        .commit();
            }
        });

        listOfEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, new CustomerFragment(fragmentManager, Constants.USER_TYPE_EMPLOYEE))
                        .addToBackStack(null)
                        .commit();
            }
        });
        listOfCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, new HomeFragment(fragmentManager))
                        .addToBackStack(null)
                        .commit();

            }
        });

        todayReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, new OrdersFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        return root;
    }
}
