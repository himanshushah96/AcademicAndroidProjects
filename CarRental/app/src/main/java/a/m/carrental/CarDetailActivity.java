package a.m.carrental;

import a.m.carrental.model.Session;
import a.m.carrental.ui.home.DateDialogBox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import a.m.carrental.db.Car;
import a.m.carrental.model.CarModel;

public class CarDetailActivity extends AppCompatActivity implements DateDialogBox.OnCompleteListener {

    private TextView textView_modelName;
    private TextView textView_description;
    private TextView textView_brand;
    private TextView textView_carType;
    private TextView textView_price;
    private TextView textView_date;
    private ImageView imageView_car;
    private Button button_book;
    private Button button_reserve;
    private Button button_cancel;
    private LinearLayout linearLayout_button;
    private int carId;
    private Boolean isFromOrder;
    private Boolean isCarBooked;
    private Boolean isCarReserved;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);

        textView_modelName = findViewById(R.id.textView_modelName_value);
        textView_description = findViewById(R.id.textView_description_value);
        textView_brand = findViewById(R.id.textView_brandName_value);
        textView_carType = findViewById(R.id.textView_CarType_value);
        textView_price = findViewById(R.id.textView_price_value);
        textView_date = findViewById(R.id.textView_date_value);
        imageView_car = findViewById(R.id.imageView_car);
        button_book=findViewById(R.id.button_book);
        button_reserve=findViewById(R.id.button_Reserve);
        button_cancel=findViewById(R.id.button_cancel);
        linearLayout_button=findViewById(R.id.linearlayout_buttonCarDetail);

        Intent intent = getIntent();
        carId = intent.getIntExtra("carId",-1);
        isFromOrder =intent.getBooleanExtra("isFromOrder",false);
        isCarBooked =intent.getBooleanExtra("isCarBooked",false);
        isCarReserved=intent.getBooleanExtra("isCarReserved",false);


        if(carId == -1){
            onBackPressed();
        }else{
            CarModel carModel = new CarModel();
            Car car = carModel.getCar(carId);
            if(car == null){
                onBackPressed();
            }else{
                textView_modelName.setText(car.modelName);
                textView_description.setText(car.description);
                textView_brand.setText(car.brand);
                textView_carType.setText(car.carType);
                textView_price.setText("$" + Double.toString(car.price));
                textView_date.setText(car.date);
                imageView_car.setImageResource(car.image);

                if(getSupportActionBar() != null){
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setTitle(car.brand + " - " + car.modelName);
                    getSupportActionBar().show();
                }
            }
            if(isFromOrder ==true)
            {
                button_reserve.setVisibility(View.INVISIBLE);
                button_book.setVisibility(View.INVISIBLE);
                if(isCarBooked){
                    button_cancel.setText("Return");
                }
                if(isCarReserved){
                    button_cancel.setText("Cancel");
                }
                button_cancel.setVisibility(View.VISIBLE);
                linearLayout_button.setVisibility(View.INVISIBLE);
            }
            else {
                button_cancel.setVisibility(View.INVISIBLE);
                linearLayout_button.setVisibility(View.VISIBLE);
            }
        }


        button_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialogBox dateDialogBox = new DateDialogBox("Select From Date","Select Due Date",true, false,"Book Date",null, textView_date.getText().toString());
                dateDialogBox.show(getSupportFragmentManager(),"Date Dialog box");
            }
        });

        button_reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialogBox dateDialogBox = new DateDialogBox("Select From Date","Select To Date",false, false,"Reserve Date", null, textView_date.getText().toString());
                dateDialogBox.show(getSupportFragmentManager(),"Date Dialog box");
            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarModel carModel=new CarModel();
                if(isCarBooked==true)
                {

                    carModel.cancelBooking(carId);
                    onBackPressed();

                }
                if(isCarReserved==true)
                {
                    carModel.cancelReservation(carId);
                    onBackPressed();
                }


            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onFromToDateSet(String fromDate, String toDate, boolean isForBooking) {
        CarModel carModel = new CarModel();
        Session session = Session.getInstance();
        if(carModel.bookCar(carId,session.getUserEmail(),fromDate,toDate, isForBooking)){
            new AlertDialog.Builder(CarDetailActivity.this, R.style.AlertDialogTheme)
                    .setTitle("Success!")
                    .setMessage("Your cars has been successfully booked!")
                    .setPositiveButton(android.R.string.yes, null)
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(R.drawable.ic_check_circle_black_24dp)
                    .show();
        }else{
            new AlertDialog.Builder(CarDetailActivity.this, R.style.AlertDialogTheme)
                    .setTitle("Sorry!")
                    .setMessage("Car has been already booked for your selected date!")
                    .setPositiveButton(android.R.string.yes, null)
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        System.out.println(fromDate);
    }
}
