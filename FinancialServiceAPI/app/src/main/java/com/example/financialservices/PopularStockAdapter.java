package com.example.financialservices;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PopularStockAdapter extends RecyclerView.Adapter<PopularStockAdapter.ViewHolder> {

    private View.OnClickListener listener;
    private Context context;
    private ArrayList<MostGainerStock> mostGainerStocks;
    private ArrayList<MostLoserStock> mostLoserStocks;
    int test;
    int size = 0;
    boolean isGainer = false;

    public PopularStockAdapter(ArrayList<MostGainerStock> mostGainerStocks, Context context ) {
        this.context = context;
        isGainer = true;
        this.mostGainerStocks=mostGainerStocks;
        size = mostGainerStocks.size();
    }

    public PopularStockAdapter(ArrayList<MostLoserStock> mostLoserStocks,Context context,int test)
    {
        isGainer = false;
        this.context=context;
        this.mostLoserStocks=mostLoserStocks;
        this.test=test;
        size = mostLoserStocks.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(isGainer){
            holder.companyName.setText(this.mostGainerStocks.get(position).getCompanyName());
            holder.tickerName.setText(this.mostGainerStocks.get(position).getTicker());
            holder.stockPrice.setText("$" + this.mostGainerStocks.get(position).getPrice());
            holder.priceChanges.setText(this.mostGainerStocks.get(position).getChanges().toString() + this.mostGainerStocks.get(position).getChangesPercentage());
        }else{
            holder.companyName.setText(this.mostLoserStocks.get(position).getCompanyName());
            holder.tickerName.setText(this.mostLoserStocks.get(position).getTicker());
            holder.stockPrice.setText("$" + this.mostLoserStocks.get(position).getPrice());
            holder.priceChanges.setText(this.mostLoserStocks.get(position).getChanges().toString() + this.mostLoserStocks.get(position).getChangesPercentage());
        }
    }

    @Override
    public int getItemCount() {
        return size;
    }

    public void setOnItemClickListner(View.OnClickListener itemClickListner)
    {
        listener = itemClickListner;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView companyName;
        TextView tickerName;
        TextView priceChanges;
        TextView stockPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            companyName = itemView.findViewById(R.id.MG_Companyname);
            tickerName = itemView.findViewById(R.id.MG_ticker);
            priceChanges = itemView.findViewById(R.id.MG_Changes);
            stockPrice = itemView.findViewById(R.id.MG_Stockprices);
            itemView.setTag(this);
            itemView.setOnClickListener(listener);
        }
    }
}
