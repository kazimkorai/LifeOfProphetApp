package com.example.lifeofprophetapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeofprophetapp.R;
import com.example.lifeofprophetapp.activities.NestedListSahiBukhariActivity;
import com.example.lifeofprophetapp.activities.SahihBukhariDetailzActivity;
import com.example.lifeofprophetapp.pojo.DataModel;
import com.example.lifeofprophetapp.utilz.UtilsConstants;

import java.util.ArrayList;

public class AdapterNestedSahihBukhari extends RecyclerView.Adapter<AdapterNestedSahihBukhari.MyViewHolder> {

    Context context;
    ArrayList<DataModel> listItems;

    public AdapterNestedSahihBukhari(Context context, ArrayList<DataModel> listItems) {

        this.context = context;
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public AdapterNestedSahihBukhari.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_nested_sahibukhari, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNestedSahihBukhari.MyViewHolder holder, int position) {

        final DataModel listitem=listItems.get(position);
        int hadithNo=position+1;
        holder.tvHadithName.setText("Hadith No."+hadithNo+"");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SahihBukhariDetailzActivity.class);
                intent.putExtra("id",listitem.getId()+"");
                UtilsConstants.CHAPTER_NAME=listitem.getChapterName();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    public void filterList(ArrayList<DataModel> filterdNames) {
        this.listItems = filterdNames;
        notifyDataSetChanged();

    }
    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvHadithName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHadithName=itemView.findViewById(R.id.tvHadithName);
        }
    }
}
