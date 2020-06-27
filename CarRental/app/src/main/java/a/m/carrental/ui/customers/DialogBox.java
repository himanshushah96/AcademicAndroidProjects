package a.m.carrental.ui.customers;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.UiAutomation;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import a.m.carrental.R;
import a.m.carrental.db.DbModel;
import a.m.carrental.db.User;
import a.m.carrental.model.Constants;
import a.m.carrental.model.UserModel;
import a.m.carrental.ui.home.RecyclerViewDataAdapter;
import a.m.carrental.ui.home.RecyclerViewItemHolder;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogBox extends DialogFragment {

    EditText dialog_fName;
    EditText dialog_lName;
    EditText dialog_emailId;

    String firstname;
    String lastName;
    String email;
    String title;
    boolean isForAdd;
    String userType = "";


    public DialogBox(String title, String userType){
        this.title = title;
        this.isForAdd = true;
        this.userType = userType;
    }

    public DialogBox(String firstName, String lastName, String email, String title){
        this.email = email;
        this.firstname = firstName;
        this.lastName = lastName;
        this.title = title;
        this.isForAdd = false;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.AlertDialogTheme);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);


            builder.setView(view)
                    .setTitle(title)
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            getDialog().dismiss();
                        }
                    })
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String firstName = dialog_fName.getText().toString();
                            String lastName = dialog_lName.getText().toString();
                            String emailId = dialog_emailId.getText().toString();
                            if (!firstName.equals("") && !lastName.equals("") && !emailId.equals("")) {

                                DbModel dbModel = DbModel.getInstance();
                                if(isForAdd){
                                    User user = new User();
                                    user.userType = userType;
                                    user.lastName = dialog_lName.getText().toString();
                                    user.firstName = dialog_fName.getText().toString();
                                    user.password = Constants.DEFAULT_PASSWORD;
                                    user.email = dialog_emailId.getText().toString();
                                    dbModel.add(user);
                                }else{
                                    User user = new User();
                                    user.firstName = dialog_fName.getText().toString();
                                    user.lastName = dialog_lName.getText().toString();
                                    user.email = dialog_emailId.getText().toString();

                                    UserModel userModel = new UserModel();
                                    userModel.updateUser(user);
                                    Intent intent = new Intent();
                                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                                    dismiss();

                                }
                            }
                        }
                    });




            dialog_fName = view.findViewById(R.id.dialog_fName);
            dialog_lName = view.findViewById(R.id.dialog_lName);
            dialog_emailId = view.findViewById(R.id.dialog_emailId);
            if(!isForAdd){
                dialog_emailId.setEnabled(false);
            }
            dialog_fName.setText(firstname);
            dialog_lName.setText(lastName);
            dialog_emailId.setText(email);

        return builder.create();


    }
}
