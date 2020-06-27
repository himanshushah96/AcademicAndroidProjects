package a.m.carrental.ui.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import a.m.carrental.R;
import a.m.carrental.db.Car;
import a.m.carrental.db.DbModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

public class DateDialogBox extends DialogFragment  {
    EditText editText_fromDate;
    EditText editText_toDate;
    ImageView imageView_DatePicker1;
    ImageView imageView_DatePicker2;
    int year,day,month;
    String hintFromDate;
    String hintToDate;
    boolean isFromDateReadOnly;
    boolean isForFilter;
    String dialogTitle;
    RecyclerView.Adapter  adapter;
    DatePickerDialog fromDatePickerDialog;
    DatePickerDialog toDatePickerDialog;
    String availableDate;

    private OnCompleteListener mListener;

    public interface OnCompleteListener {
        public void onFromToDateSet(String fromDate, String toDate, boolean isForBooking);
    }

    public DateDialogBox(String hintFromDate, String hintToDate, boolean isFromDateReadOnly, boolean isForFilter, String dialogTitle, RecyclerView.Adapter adapter, String availableDate){
        this.hintFromDate=hintFromDate;
        this.hintToDate=hintToDate;
        this.isFromDateReadOnly=isFromDateReadOnly;
        this.dialogTitle=dialogTitle;
        this.adapter = adapter;
        this.isForFilter = isForFilter;
        this.availableDate = availableDate;
    }


    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogTheme);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_date_filter, null);
        builder.setView(view)
                .setTitle(dialogTitle)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getDialog().dismiss();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(!editText_fromDate.getText().toString().equals("") && !editText_toDate.getText().toString().equals(""))
                        {
                            if(isForFilter){
                                Intent intent = new Intent()
                                        .putExtra("fromDate", editText_fromDate.getText().toString())
                                        .putExtra("toDate", editText_toDate.getText().toString());
                                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                                dismiss();

                            }else{
                                OnCompleteListener listener = (OnCompleteListener)getActivity();
                                listener.onFromToDateSet(editText_fromDate.getText().toString(),editText_toDate.getText().toString(),isFromDateReadOnly);
                                dismiss();
                            }
                        }

                        else
                        {
                            Toast.makeText(getContext(),"Please select a date",Toast.LENGTH_LONG).show();
                        }
                    }
                });
        editText_fromDate = view.findViewById(R.id.editText_fromDate);
        editText_toDate = view.findViewById(R.id.editText_toDate);
        imageView_DatePicker1 = view.findViewById(R.id.imageView_datePicker1);
        imageView_DatePicker2 = view.findViewById(R.id.imageView_datePicker2);

        toDatePickerDialog=new DatePickerDialog(getContext(),R.style.AlertDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                editText_toDate.setText(dayOfMonth+ "/" + (month+1)+"/"+year);

            }
        },year,month,day);

        fromDatePickerDialog=new DatePickerDialog(getContext(), R.style.AlertDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                editText_fromDate.setText(dayOfMonth+ "/" + (month+1)+"/"+year);
                editText_toDate.setText("");
                Calendar c=Calendar.getInstance();
                c.set(year,month,dayOfMonth);
                toDatePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
            }
        },year,month,day);

        if(isFromDateReadOnly)
        {
            final Calendar c=Calendar.getInstance();
            year=c.get(Calendar.YEAR);
            month=c.get(Calendar.MONTH);
            day=c.get(Calendar.DAY_OF_MONTH);

            String[] date = availableDate.split("/");
            Calendar minDate = Calendar.getInstance();
            minDate.set(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
            if(minDate.getTime().after(c.getTime())){
                editText_fromDate.setText(availableDate);
            }else{
                editText_fromDate.setText(day+"/"+(month+1)+"/"+year);
            }
            imageView_DatePicker1.setOnClickListener(null);
        }
        else
        {
            imageView_DatePicker1.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {
                    Calendar c=Calendar.getInstance();
                    year=c.get(Calendar.YEAR);
                    month=c.get(Calendar.MONTH);
                    day=c.get(Calendar.DAY_OF_MONTH);
                    SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
                    if(!availableDate.equals("")){

                        String[] date = availableDate.split("/");
                        Calendar minDate = Calendar.getInstance();
                        minDate.set(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));

                        if(minDate.getTime().after(c.getTime())){
                            fromDatePickerDialog.getDatePicker().setMinDate(minDate.getTimeInMillis());
                        }else{
                            fromDatePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                        }
                    }

                    fromDatePickerDialog.show();
                }
            });

        }

        editText_fromDate.setEnabled(false);
        editText_toDate.setEnabled(false);
        editText_fromDate.setHint(hintFromDate);
        editText_toDate.setHint(hintToDate);

        imageView_DatePicker2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                year=c.get(Calendar.YEAR);
                month=c.get(Calendar.MONTH);
                day=c.get(Calendar.DAY_OF_MONTH);

                if(!editText_fromDate.getText().toString().equals("")){
                    if(!editText_fromDate.getText().toString().equals("")){
                        String[] date = editText_fromDate.getText().toString().split("/");
                        c.set(Integer.parseInt(date[2]),Integer.parseInt(date[1]) -1 ,Integer.parseInt(date[0]));
                        toDatePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                    }
                    toDatePickerDialog.show();
                }else{
                    new AlertDialog.Builder(getContext(), R.style.AlertDialogTheme)
                            .setTitle("Validation!")
                            .setMessage("Please select from date first!")
                            .setPositiveButton(android.R.string.yes, null)
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }
        });
        return builder.create();

    }
}