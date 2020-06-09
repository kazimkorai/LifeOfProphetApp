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
import com.example.lifeofprophetapp.activities.Names99ListActivity;
import com.example.lifeofprophetapp.activities.NamesDetailzActivity;
import com.example.lifeofprophetapp.pojo.NamesDataModel;

import java.util.ArrayList;

public class Adapter99Names extends RecyclerView.Adapter<Adapter99Names.MyViewHolder> {

    Context context;
    ArrayList<NamesDataModel> listItems;

    public Adapter99Names(Context context, ArrayList<NamesDataModel> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public Adapter99Names.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_nameslist,parent,false);

        MyViewHolder viewHolder=new MyViewHolder(view);

        return  viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull Adapter99Names.MyViewHolder holder, final int position) {

        final NamesDataModel list=listItems.get(position);
        holder.tvEnglishName.setText(list.getNameEnglish());
        holder.tvArabicName.setText(list.getArabicName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(context, NamesDetailzActivity.class);
                intent.putExtra("id",list.getId()+"");
                intent.putExtra("pos",position+"");
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
        TextView tvArabicName,tvEnglishName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvArabicName=(TextView)itemView.findViewById(R.id.tvArabicName);
            tvEnglishName=(TextView)itemView.findViewById(R.id.tvEnglishName);
        }
    }
}
