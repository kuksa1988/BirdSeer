package com.example.birdseer;

import static android.graphics.Color.GREEN;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyadapterLajit extends RecyclerView.Adapter<MyadapterLajit.MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    private ArrayList<String> data;
    Context context;

    public MyadapterLajit(Context ctx, ArrayList<String> lajit, RecyclerViewInterface recyclerViewInterface){
        context = ctx;
        data = lajit;  //data-taulukkoon tuodaan lajit arraylist
        this.recyclerViewInterface = recyclerViewInterface;

    }

    @NonNull
    @Override //luodaan recylcerviewin näkymä. view = my_row_lajit
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_lajit, parent, false);
        return new MyViewHolder(view, recyclerViewInterface);
    }

    @Override //teksti jota näytetään listan itemissä. Mytext = laji arraylistista
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText.setText(data.get(position));

    }

    @Override //rivien määrä recylerviewissä. saadaan lajilistan pituudesta.
    public int getItemCount() {

        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText;
        ImageView checkbox;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            myText = itemView.findViewById(R.id.my_row_lajinimi);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null){
                        int pos = getAdapterPosition(); //klikattavan itemin positio

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos); //klikatun itemin positio parametrina metodille
                        }
                    }
                }
            });
        }
    }
}
