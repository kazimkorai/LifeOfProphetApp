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
import com.example.lifeofprophetapp.activities.SahihBukhariDetailzActivity;
import com.example.lifeofprophetapp.activities.SahihMuslimsDetailzActivity;
import com.example.lifeofprophetapp.pojo.DataModel;
import com.example.lifeofprophetapp.utilz.UtilsConstants;

import java.util.ArrayList;

public class AdapterNestedSahihMuslim extends RecyclerView.Adapter<AdapterNestedSahihMuslim.MyViewHolder> {

    Context context;
    ArrayList<DataModel> listItems;

    public AdapterNestedSahihMuslim(Context context, ArrayList<DataModel> listItems) {

        this.context = context;
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public AdapterNestedSahihMuslim.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_nested_sahimuslim, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNestedSahihMuslim.MyViewHolder holder, int position) {

        final DataModel listitem=listItems.get(position);
        int hadithNo=position+1;
        Log.d("*size",position+"");
        holder.tvHadithName.setText("Hadith No."+hadithNo+"");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SahihMuslimsDetailzActivity.class);
                intent.putExtra("id",listitem.getId()+"");
                UtilsConstants.CHAPTER_NAME=listitem.getChapterName();
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

        TextView tvHadithName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHadithName=itemView.findViewById(R.id.tvHadithName);
        }
    }
}
