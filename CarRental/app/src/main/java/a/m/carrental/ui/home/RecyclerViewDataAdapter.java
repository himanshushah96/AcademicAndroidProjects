package a.m.carrental.ui.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import a.m.carrental.CarDetailActivity;
import a.m.carrental.HomeActivity;
import a.m.carrental.LoginActivity;
import a.m.carrental.R;
import a.m.carrental.db.Car;
import a.m.carrental.db.User;
import a.m.carrental.model.Constants;
import a.m.carrental.model.UserModel;
import a.m.carrental.ui.customers.CustomerFragment;
import a.m.carrental.ui.customers.DialogBox;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewDataAdapter extends RecyclerView.Adapter<RecyclerViewItemHolder>
{
    private List<User> usersList;
    private List<Car> carItemList;
    private int viewHolderId;
    private RecyclerViewItemHolder _holder;
    private int itemPosition;
    private String userType;
    FragmentManager fragmentManager;
    public CustomerFragment customerFragment;
    public RecyclerViewDataAdapter(List<Car> carItemList,int viewHolderId)
    {
        this.viewHolderId=viewHolderId;
        this.carItemList=carItemList;
    }

    public RecyclerViewDataAdapter(ArrayList<User> usersList, int viewHolderId, FragmentManager fragmentManager, String userType, CustomerFragment customerFragment) {
        this.usersList= usersList;
        this.fragmentManager = fragmentManager;
        this.viewHolderId = viewHolderId;
        this.userType = userType;
        this.customerFragment = customerFragment;
    }


    @NonNull
    @Override
    public RecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(viewHolderId,parent,false);
        RecyclerViewItemHolder ret = new RecyclerViewItemHolder(itemView,viewHolderId);
        return ret;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewItemHolder holder, final int position) {
        _holder = holder;
        if (carItemList!=null)
        {
            final Car carItem = carItemList.get(position);
            if(carItem!=null)
            {
                if(viewHolderId==R.layout.activity_card_view_item) {
                    holder.getTextView_carName().setText(carItem.modelName);
                    holder.getImageView_carImage().setImageResource(carItem.image);
                    holder.getTextView_carPrice().setText("Price : $" + Double.toString(carItem.price));
                    holder.getTextView_dateAvailable().setText("Date Available : " + carItem.date);

                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Car _carItem = carItemList.get(position);
                            Intent intent = new Intent(view.getContext(), CarDetailActivity.class);
                            intent.putExtra("carId", _carItem.id);
                            //intent.putExtra("isCarBooked",true);
                            _holder.itemView.getContext().startActivity(intent);
                        }
                    });

                }
                else if(viewHolderId==R.layout.activity_card_view_order)
                {
                    holder.getTextView_carName().setText(carItem.modelName);
                    holder.getImageView_carImage().setImageResource(carItem.image);
                    holder.getTextView_carPrice().setText("Price : $" + Double.toString(carItem.price));
                    holder.getTextView_dateAvailable().setText("Order Date : " + carItem.date);
                    holder.getTextView_orderType().setText(carItem.isBooked ? "Booked" : "Reserved");


                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(view.getContext(), CarDetailActivity.class);
                            intent.putExtra("carId", carItem.id);
                            intent.putExtra("isCarBooked",carItem.isBooked);
                            intent.putExtra("isCarReserved",carItem.isReserved);
                            intent.putExtra("isFromOrder",true);
                            _holder.itemView.getContext().startActivity(intent);
                        }
                    });
                }

            }
        }

        else if (usersList!=null){

            final User userItem = usersList.get(position);
            if (usersList!=null){
                if (viewHolderId == R.layout.fragment_customer){
                    holder.getTextView_firstName().setText(userItem.firstName);
                    holder.getTextView_lastName().setText(userItem.lastName);
                    holder.getTextView_emailId().setText(userItem.email);
                    holder.getCircleImageView().setImageResource(R.drawable.ic_person_outline_black_24dp);
                    holder.getImageView_editData().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if(fragmentManager != null){
                                DialogBox dialogBox = new DialogBox(userItem.firstName, userItem.lastName, userItem.email, "Edit Customer Detail");
                                dialogBox.setTargetFragment(customerFragment,1);
                                dialogBox.show(fragmentManager  ,"Dialog box");
                            }
                        }
                    });

                    holder.getImageView_deletedata().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            final AlertDialog dialog;
                            dialog = new AlertDialog.Builder(holder.itemView.getContext())
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .setTitle("Delete")
                                    .setMessage("Are you sure you want to Delete this customer?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            UserModel.DeleteUser(holder.getTextView_emailId().getText().toString());
                                            usersList.clear();
                                            usersList.addAll(new UserModel().getUserBasedOnType(userType));
                                            notifyDataSetChanged();
                                        }
                                    })
                                    .setNegativeButton("No", null)
                                    .create();
                            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                                @Override
                                public void onShow(DialogInterface dialogInterface) {
                                    dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(holder.itemView.getContext().getResources().getColor(R.color.buttonColor));
                                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(holder.itemView.getContext().getResources().getColor(R.color.buttonColor));
                                }
                            });
                            dialog.show();

                        }

                    });

                }
            }
        }
    }

    @Override
    public int getItemCount() {
        int ret=0;
        if(carItemList!=null)
        {
            ret = carItemList.size();
        }
        else if (usersList!=null){
            ret = usersList.size();
        }
        return ret;

    }
}
