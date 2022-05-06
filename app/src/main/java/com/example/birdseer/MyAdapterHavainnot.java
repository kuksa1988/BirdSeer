package com.example.birdseer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterHavainnot extends RecyclerView.Adapter<MyAdapterHavainnot.MyViewHolder> {

    private Context context;
    private ArrayList<String> lajiNimiArraytList, sijaintiArrayList;
    private ArrayList<Integer> idArrayList, dayArrayList, monthArraylist, yearArrayList;

    public MyAdapterHavainnot(Context context,
                              ArrayList<String> lajiNimiArraytList,
                              ArrayList<String> sijaintiArrayList,
                              ArrayList<Integer> idArrayList,
                              ArrayList<Integer> dayArrayList,
                              ArrayList<Integer> monthArraylist,
                              ArrayList<Integer> yearArrayList) {

        this.context = context;
        this.lajiNimiArraytList = lajiNimiArraytList;
        this.sijaintiArrayList = sijaintiArrayList;
        this.idArrayList = idArrayList;
        this.dayArrayList = dayArrayList;
        this.monthArraylist = monthArraylist;
        this.yearArrayList = yearArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_havainnot, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.havaintoID_txt.setText(String.valueOf(idArrayList.get(position)));
        holder.havaittuLaji_txt.setText(String.valueOf(lajiNimiArraytList.get(position)));
        holder.paiva_txt.setText(String.valueOf(dayArrayList.get(position)));
        holder.kuukausi_txt.setText(String.valueOf(monthArraylist.get(position)));
        holder.vuosi_txt.setText(String.valueOf(yearArrayList.get(position)));
        holder.sijainti_txt.setText(String.valueOf(sijaintiArrayList.get(position)));


    }

    @Override
    public int getItemCount() {
        return idArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView havaintoID_txt, havaittuLaji_txt, paiva_txt, kuukausi_txt, vuosi_txt, sijainti_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            havaintoID_txt = itemView.findViewById(R.id.havaintoID_txt);
            havaittuLaji_txt = itemView.findViewById(R.id.havaittuLaji_txt);
            paiva_txt = itemView.findViewById(R.id.paiva_txt);
            kuukausi_txt = itemView.findViewById(R.id.Kuukausi_txt);
            vuosi_txt = itemView.findViewById(R.id.vuosi_txt);
            sijainti_txt = itemView.findViewById(R.id.sijainti_txt);
        }
    }
}
