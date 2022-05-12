package com.example.birdseer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterHavainnot extends RecyclerView.Adapter<MyAdapterHavainnot.MyViewHolder> {

    private Context context;
    private ArrayList<String> lajiNimiArraytList, sijaintiArrayList;
    private ArrayList<Integer> idArrayList, dayArrayList, monthArraylist, yearArrayList;
    private ArrayList<Boolean> painettu;
    private ImageView poistaLaji;


    int position;

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
       // holder.havaintoID_txt.setText(String.valueOf(idArrayList.get(position)));
        holder.havaittuLaji_txt.setText(String.valueOf(lajiNimiArraytList.get(position)));
        holder.paiva_txt.setText(String.valueOf(dayArrayList.get(position)));
        holder.kuukausi_txt.setText(String.valueOf(monthArraylist.get(position)));
        holder.vuosi_txt.setText(String.valueOf(yearArrayList.get(position)));
        holder.sijainti_txt.setText(String.valueOf(sijaintiArrayList.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (holder.poistaLaji.getVisibility() == View.INVISIBLE){
                    holder.poistaLaji.setVisibility(View.VISIBLE);

                }else {
                    holder.poistaLaji.setVisibility(View.INVISIBLE);

                }

            }
        });

        //Tämä katsoo, jos roskakorin kuvaa painetaan niin siirrytään poisto pop-up näkymään
        holder.poistaLaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                havainnonPoistoDialogi(position);

            }
        });


    }

    private int currentPosition() {
        return this.currentPosition();
    }

    private void havainnonPoistoDialogi(int position) {

        //Tässä kohdassa tulee pop-up näkymä, kun painaa roskakoria. Pop-up näkymä
        //kysyy käyttäjältä, että haluaako hän poistaa, vai ei. Jos poistetaan, tieto
        //poistuu SQL tietokannasta sekä ArrayListeistä, ja uusi listaus päivitetään
        //Jos ei poisteta, mitään ei tapahdu.

        String lajinimi = String.valueOf(lajiNimiArraytList.get(position));
        String sijainti = String.valueOf(sijaintiArrayList.get(position));
        int vuosi = yearArrayList.get(position);
        int kuukausi = monthArraylist.get(position);
        int paiva = dayArrayList.get(position);
        int id =idArrayList.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle("Havainnon poistaminen");
        String viesti = "Haluatko varmasti poistaa havainnon: " + "<br>" + "<b>" + id +
                ". " + lajinimi +
                ", " + paiva +
                "." + kuukausi +
                "." + vuosi +
                " " + sijainti +
                "?" + "</b>";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setMessage(Html.fromHtml (viesti, Html.FROM_HTML_MODE_COMPACT));
        } else {
            builder.setMessage(Html.fromHtml (viesti));
        }
        builder.setPositiveButton("Poista",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Hyväksytään poisto
                        MyDatabaseHelper myDB = new MyDatabaseHelper(context);
                        myDB.poistaHavainto(id);

                        lajiNimiArraytList.remove(position);
                        sijaintiArrayList.remove(position);
                        idArrayList.remove(position);
                        dayArrayList.remove(position);
                        monthArraylist.remove(position);
                        yearArrayList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, getItemCount());


                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Tämä on peruuta-kohta

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return idArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView havaintoID_txt, havaittuLaji_txt, paiva_txt, kuukausi_txt, vuosi_txt, sijainti_txt;
        ImageView poistaLaji;
        LinearLayout mainLayout;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //havaintoID_txt = itemView.findViewById(R.id.havaintoID_txt);
            havaittuLaji_txt = itemView.findViewById(R.id.havaittuLaji_txt);
            paiva_txt = itemView.findViewById(R.id.paiva_txt);
            kuukausi_txt = itemView.findViewById(R.id.Kuukausi_txt);
            vuosi_txt = itemView.findViewById(R.id.vuosi_txt);
            sijainti_txt = itemView.findViewById(R.id.sijainti_txt);
            poistaLaji = itemView.findViewById(R.id.roskakori);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            poistaLaji.setVisibility(View.INVISIBLE);



        }
    }
}