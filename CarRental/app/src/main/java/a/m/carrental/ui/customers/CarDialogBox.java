package a.m.carrental.ui.customers;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import a.m.carrental.R;
import a.m.carrental.db.Car;
import a.m.carrental.db.DbModel;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CarDialogBox extends DialogFragment {

    EditText dialog_modelName;
    EditText dialog_brandName;
    EditText dialog_price;
    EditText dialog_dateAvailable;

    boolean isForAddCar;
    String userType = "";

    String title;
    String modelName ="";
    String brandName="";
    double price;
    String dateAvailable="";

    public CarDialogBox(String title){
        this.title = title;
        this.isForAddCar=true;

    }

    public CarDialogBox(String modelName, String brandName,double price, String dateAvailable,String title){
        this.modelName = modelName;
        this.brandName = brandName;
        this.price = price;
        this.dateAvailable = dateAvailable;
        this.title = title;
        this.isForAddCar = false;

    }

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogTheme);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_cardialogbox,null);

        dialog_modelName = view.findViewById(R.id.dialog_modelName);
        dialog_brandName = view.findViewById(R.id.dialog_brandName);
        dialog_price = view.findViewById(R.id.dialog_price);
        dialog_dateAvailable = view.findViewById(R.id.dialog_dateAvailable);

        builder.setView(view)
                .setTitle("Add Car")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getDialog().dismiss();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String modelName = dialog_modelName.getText().toString();
                        String brandName = dialog_brandName.getText().toString();
                        double price = Double.parseDouble(dialog_price.getText().toString());
                        String dateAvailable = dialog_dateAvailable.getText().toString();

                        if (!modelName.equals("") &&!brandName.equals("")&&!dateAvailable.equals("")){
                            DbModel dbModel = DbModel.getInstance();

                            if (isForAddCar){
                                Car car = new Car();
                                car.modelName = modelName;
                                car.brand = brandName;
                                car.price = price;
                                car.date = dateAvailable;
                                car.image = R.drawable.tesla_model_s;
                                dbModel.add(car);
                            }
                            else {
                                Intent _intent = new Intent()
                                        .putExtra("modelName",modelName)
                                        .putExtra("brandName",brandName)
                                        .putExtra("price",price)
                                        .putExtra("date",dateAvailable);
                                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK,_intent);
                                dismiss();
                            }

                        }
                    }
                });

        dialog_modelName.setText(modelName);
        dialog_brandName.setText(brandName);
        dialog_price.setText(Double.toString(price));
        dialog_dateAvailable.setText(dateAvailable);

        return builder.create();
    }
}
