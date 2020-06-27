package a.m.carrental.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

import a.m.carrental.db.Car;
import a.m.carrental.db.DbModel;
import a.m.carrental.db.SetupData;
import a.m.carrental.ui.customers.CarDialogBox;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import a.m.carrental.R;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class HomeFragment extends Fragment  {


    FragmentManager fragmentManager;

    public HomeFragment(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
    private DbModel db;
    AdapterView<?> adapterView;
    int adapterViewPosition;
    private HomeViewModel homeViewModel;

    public Date convertToDate(String car_date){
        try {
            Date date = simpleDateFormat.parse(String.valueOf(car_date));
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        db = DbModel.getInstance();
        //carItemList = new ArrayList<RecyclerViewItem>();

        List<String> sortBy = new ArrayList<>();
        sortBy.add(0, "Sort By");
        sortBy.add(1, "Name");
        sortBy.add(2, "Low Price");
        sortBy.add(3, "High Price");
        sortBy.add(4,"New Arrival");

        List<String> filterBy = new ArrayList<>();
        filterBy.add(0,"Filter");
        //filterBy.add(1,"Brand");
        //filterBy.add(2,"Type");
        //filterBy.add(3,"Color");
        //Add items to list

        ArrayAdapter<String> sortAdapter,filterAdapter;
        sortAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, sortBy);
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterAdapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,filterBy);
        //filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        //Recycler view
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView_home);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        final RecyclerViewDataAdapter dataAdapter = new RecyclerViewDataAdapter(db.getCars(),R.layout.activity_card_view_item);
        recyclerView.setAdapter(dataAdapter);

        Spinner spinner_sort = root.findViewById(R.id.spinner_sort);
        Button button_date_filter = root.findViewById(R.id.button_filter_date);
        spinner_sort.setAdapter(sortAdapter);


        //spinner_filter.setAdapter(filterAdapter);


        spinner_sort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, final View view, int position, long id) {

                adapterView = parent;
                adapterViewPosition = position;

                Collections.sort(db.getCars(), new Comparator<Car>() {
                    @Override
                    public int compare(Car o1, Car o2) {
                        if(adapterView.getItemAtPosition(adapterViewPosition).equals("Name")) {
                            return o1.modelName.compareToIgnoreCase(o2.modelName);
                        }else if(adapterView.getItemAtPosition(adapterViewPosition).equals("Low Price") || adapterView.getItemAtPosition(adapterViewPosition).equals("High Price")){
                            final int priority = (adapterView.getItemAtPosition(adapterViewPosition).equals("Low Price")) ? 1 : -1;
                            return o1.price>o2.price? priority :(o1.price<o2.price)? -priority  :0;
                        }else if(adapterView.getItemAtPosition(adapterViewPosition).equals("New Arrival")){
                            return convertToDate(o1.date).compareTo(convertToDate(o2.date)) > 0 ? -1 : 0;
                        }else{
                            return 0;
                        }
                    }
                });
                dataAdapter.notifyDataSetChanged();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });




        button_date_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragmentManager!=null) {
                    DateDialogBox dateDialogBox = new DateDialogBox("Select From Date","Select To Date",false, true,"Filter Date", dataAdapter, "");
                    dateDialogBox.setTargetFragment(HomeFragment.this,0);
                    dateDialogBox.show(fragmentManager,"Date Dialog box");
                }
            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == Activity.RESULT_OK){
            Bundle bundle = data.getExtras();
            String fromDate = bundle.getString("fromDate", "");
            String toDate = bundle.getString("toDate","");
            String  modelName = bundle.getString("modelName","");
            String brandName = bundle.getString("brandName", "");
            String price = bundle.getString("price","");
            String dateAvailable = bundle.getString("dateAvailable","");

        }
    }
}