package com.example.lifeofprophetapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeofprophetapp.R;
import com.example.lifeofprophetapp.activities.NestedListOfLifeOfMuhammadActivity;
import com.example.lifeofprophetapp.pojo.DataModel;
import com.example.lifeofprophetapp.utilz.UtilsConstants;

import java.util.ArrayList;

public class AdapterLifeOfMuhammad extends RecyclerView.Adapter<AdapterLifeOfMuhammad.MyViewHolder> {

    Context context;
    ArrayList<String> listItems;

    public AdapterLifeOfMuhammad(Context context, ArrayList<String> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public AdapterLifeOfMuhammad.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_life_of_muhammad, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLifeOfMuhammad.MyViewHolder holder, final int position) {

        final String name=listItems.get(position);
        holder.tvName.setText(name);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, NestedListOfLifeOfMuhammadActivity.class);
                intent.putExtra("name",name);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName);
        }
    }
}
