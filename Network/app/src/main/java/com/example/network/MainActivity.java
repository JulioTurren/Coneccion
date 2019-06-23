package com.example.network;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button verificar = findViewById(R.id.button);

        verificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Conexión del dispositivo")
                        .setMessage(checkConnectivity())
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
        });
    }

    public String checkConnectivity () {
        String message = "";

        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        // boolean isConnected = activeNetwork.isConnected();
        message = "Is connected";

        Log.d("PELLODEBUG", message);

        if(activeNetwork != null && activeNetwork.isConnected()){
            switch (activeNetwork.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                    message += "WIFI connection";
                    break;
                case ConnectivityManager.TYPE_BLUETOOTH:
                    message += "BLUETOOTH connection";
                    break;
                case ConnectivityManager.TYPE_ETHERNET:
                    message += "ETHERNET connection";
                    break;
                case ConnectivityManager.TYPE_WIMAX:
                    message += "WIMAX connection";
                    break;
                case ConnectivityManager.TYPE_DUMMY:
                    message += "DUMMY connection";
                    break;
                case ConnectivityManager.TYPE_MOBILE:
                    message += "MOBILE connection";
                    break;
                case ConnectivityManager.TYPE_MOBILE_DUN:
                    message += "MOBILE DUN connection";
                    break;
                default:
                    message += "Unknown connection";
                    break;
            }

            message += "\nTipo: " + activeNetwork.getTypeName();
            message += "\nNombre de Red: " + activeNetwork.getExtraInfo();
            message += "\nEstado: " + activeNetwork.getDetailedState().toString();
        }else {
            message = "Disconnected";
            message += "\nTipo: NULL";
            message += "\nNombre de Red: NULL";
            message += "\nEstado: " + NetworkInfo.State.DISCONNECTED;
        }


        return message;

    }
}
