package a.m.carrental.ui.home;


import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import a.m.carrental.CarDetailActivity;

import a.m.carrental.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewItemHolder extends RecyclerView.ViewHolder {

    private TextView textView_carName = null;
    private ImageView imageView_carImage = null;
    private TextView textView_carPrice = null;
    private TextView textView_dateAvailable= null;
    private TextView textView_orderType = null;
    private Intent intent;
    private Context context;


    private TextView textView_firstName = null;
    private TextView textView_lastName = null;
    private TextView textView_emailId = null;
    private CircleImageView circleImageView = null;
    private ImageView imageView_editData = null;
    private ImageView imageView_deletedata = null;




    public RecyclerViewItemHolder(@NonNull View itemView, int viewHolderId) {
        super(itemView);
        context = itemView.getContext();
        itemView.setClickable(true);

        if (itemView != null) {
            if (viewHolderId == R.layout.activity_card_view_item) {
                textView_carName = itemView.findViewById(R.id.card_view_carName);
                imageView_carImage = itemView.findViewById(R.id.card_view_image);
                textView_carPrice = itemView.findViewById(R.id.card_view_carPrice);
                textView_dateAvailable = itemView.findViewById(R.id.card_view_date);
            } else if (viewHolderId == R.layout.activity_card_view_order) {
                textView_carName=itemView.findViewById(R.id.card_view_carNameOrder);
                imageView_carImage=itemView.findViewById(R.id.card_view_imageOrder);
                textView_carPrice=itemView.findViewById(R.id.card_view_carPriceOrder);
                textView_dateAvailable=itemView.findViewById(R.id.card_view_date_order);
                textView_orderType = itemView.findViewById(R.id.textView_orderType);
            }
            else if (viewHolderId == R.layout.fragment_customer) {
                textView_firstName = itemView.findViewById(R.id.textView_fname_customerList);
                textView_lastName = itemView.findViewById(R.id.textView_Lname_customerList);
                textView_emailId = itemView.findViewById(R.id.emailid_customerList);
                circleImageView = itemView.findViewById(R.id.image_customerList);
                imageView_editData = itemView.findViewById(R.id.image_edit_customerList);
                imageView_deletedata = itemView.findViewById(R.id.image_delete_customerList);
            }
        }
    }

    public TextView getTextView_orderType() { return textView_orderType; }

    public TextView getTextView_carName()
    {
        return textView_carName;
    }

    public ImageView getImageView_editData() { return imageView_editData; }

    public ImageView getImageView_deletedata() { return imageView_deletedata; }

    public ImageView getImageView_carImage()
    {
        return imageView_carImage;
    }

    public TextView getTextView_carPrice() {
        return textView_carPrice;
    }

    public TextView getTextView_dateAvailable() {
        return textView_dateAvailable;
    }
    public TextView getTextView_firstName(){
        return textView_firstName;
    }

    public TextView getTextView_lastName() {
        return textView_lastName;
    }

    public TextView getTextView_emailId() {
        return textView_emailId;
    }
    public CircleImageView getCircleImageView(){
        return circleImageView;
    }



}
