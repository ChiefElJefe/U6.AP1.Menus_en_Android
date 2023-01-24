package com.example.u6ap1menus_en_android;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> implements View.OnClickListener {

    ArrayList<MangaVo> mangavo;
    private View.OnClickListener escucha;

    public AdapterDatos(ArrayList<MangaVo> mangavo) {
        this.mangavo = mangavo;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, null, false);

        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.titulo.setText(mangavo.get(position).getTitulo());
        holder.portada.setImageDrawable(Drawable.createFromPath(mangavo.get(position).getPortada()));
    }

    @Override
    public int getItemCount() {
        return mangavo.size();
    }

    public  void setOnClickListener(View.OnClickListener escucha){
        this.escucha = escucha;
    }

    @Override
    public void onClick(View view) {
        if (escucha != null){
            escucha.onClick(view);
        }
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView titulo;
        ImageView portada;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.idtxtNombre);
            portada = (ImageView) itemView.findViewById(R.id.idivcsm);
        }


    }
}
