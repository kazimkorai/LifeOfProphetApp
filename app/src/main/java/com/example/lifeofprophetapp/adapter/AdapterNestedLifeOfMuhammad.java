package com.example.lifeofprophetapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeofprophetapp.R;
import com.example.lifeofprophetapp.activities.DetailzLifeOfProphetActivity;
import com.example.lifeofprophetapp.activities.NestedListOfLifeOfMuhammadActivity;
import com.example.lifeofprophetapp.pojo.DataModelForLifeOf;
import com.example.lifeofprophetapp.utilz.UtilsConstants;

import java.util.ArrayList;

public class AdapterNestedLifeOfMuhammad extends RecyclerView.Adapter<AdapterNestedLifeOfMuhammad.MyViewHolder> {

    Context context;
    ArrayList<DataModelForLifeOf> listItems;

    public AdapterNestedLifeOfMuhammad(Context context, ArrayList<DataModelForLifeOf> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public AdapterNestedLifeOfMuhammad.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_nested_life_of_muhammad, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNestedLifeOfMuhammad.MyViewHolder holder, int position) {

        final DataModelForLifeOf listitem=listItems.get(position);
        holder.tvName.setText(listitem.getChapterSubName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, DetailzLifeOfProphetActivity.class);
                intent.putExtra("id",listitem.getId());
                UtilsConstants.CHAPTER_NAME=listitem.getChapterName();
                UtilsConstants.CHAPTER_IMAGE=listitem.getChapterSubName();
                Log.d("**chapter",listitem.getChapterSubName()+"");
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
