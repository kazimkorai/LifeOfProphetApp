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
import com.example.lifeofprophetapp.activities.NestedListSahiMuslimActivity;
import com.example.lifeofprophetapp.pojo.DataModel;
import com.example.lifeofprophetapp.utilz.UtilsConstants;

import java.util.ArrayList;

public class AdapterSahihMuslims extends RecyclerView.Adapter<AdapterSahihMuslims.MyViewHolder> {

    Context context;
    ArrayList<DataModel> listItems;

    public AdapterSahihMuslims(Context context, ArrayList<DataModel> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public AdapterSahihMuslims.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_sahih_muslim, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSahihMuslims.MyViewHolder holder, int position) {

        final DataModel listitem=listItems.get(position);
        holder.tvHadithName.setText(listitem.getChapterName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, NestedListSahiMuslimActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("chapterName",listitem.getChapterName()+"");
                UtilsConstants.CHAPTER_NAME=listitem.getChapterName();
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
