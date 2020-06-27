package a.m.carrental.ui.customers;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import a.m.carrental.db.Car;
import a.m.carrental.db.DbModel;
import a.m.carrental.db.SetupData;
import a.m.carrental.db.User;
import a.m.carrental.model.Constants;
import a.m.carrental.model.UserModel;
import a.m.carrental.ui.home.RecyclerViewDataAdapter;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.app.BundleCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import a.m.carrental.R;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerFragment extends Fragment {


    private DbModel dbModel;
    private CustomerViewModel customerViewModel;
    ImageView editData;
    FragmentManager fragmentManager;
    TextView textView_firstName;
    TextView textView_lastName;
    TextView textView_emailId;
    String userType;
    RecyclerViewDataAdapter dataAdapter;

    public CustomerFragment(FragmentManager fragmentManager, String userType){
        this.fragmentManager = fragmentManager;
        this.userType = userType;
    }



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dbModel = DbModel.getInstance();
        //SetupData.loadUsers();

        customerViewModel =
                ViewModelProviders.of(this).get(CustomerViewModel.class);
        View root = inflater.inflate(R.layout.recycleview_dashboard, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.dashboard_recycleview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(gridLayoutManager);
        UserModel userModel = new UserModel();
        dataAdapter = new RecyclerViewDataAdapter(userModel.getUserBasedOnType(userType),R.layout.fragment_customer, fragmentManager, userType, CustomerFragment.this);
        recyclerView.setAdapter(dataAdapter);

        View _customerListView = inflater.inflate(R.layout.fragment_customer,container,false);
        editData =  _customerListView.findViewById(R.id.image_edit_customerList);
        editData.setClickable(true);
        textView_firstName = _customerListView.findViewById(R.id.textView_fname_customerList);
        textView_lastName = _customerListView.findViewById(R.id.textView_Lname_customerList);
        textView_emailId = _customerListView.findViewById(R.id.emailid_customerList);

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == Activity.RESULT_OK){
            dataAdapter.notifyDataSetChanged();
        }
    }
}