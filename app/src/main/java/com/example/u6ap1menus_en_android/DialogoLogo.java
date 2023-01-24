package com.example.u6ap1menus_en_android;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.TextView;

public class DialogoLogo{
    Context context;
    Dialog dialogo;

    public DialogoLogo(Context context){
        this.context = context;
    }

    public void mostrarDialogo(String titulo){
        dialogo = new Dialog(context);
        dialogo.setContentView(R.layout.dialogo);
        dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView texto = dialogo.findViewById(R.id.idVistaTexto);
        texto.setText(titulo);
        dialogo.setCancelable(false);
        dialogo.create();
        dialogo.show();
    }

    public void cerrarDialogo(){
        dialogo.dismiss();
    }
}
