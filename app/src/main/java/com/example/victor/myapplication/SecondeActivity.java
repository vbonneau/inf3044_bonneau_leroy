package com.example.victor.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.example.victor.myapplication.MainActivity.BIERS_UPDATE;

public class SecondeActivity extends AppCompatActivity {

    RecyclerView v;

    public JSONArray getBiersFromFile(){
        try{
            InputStream is = new FileInputStream(getCacheDir()+"/"+"bieres.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new JSONArray(new String(buffer,"UTF8"));
        }catch (IOException e){
            e.printStackTrace();
            return new JSONArray();
        }catch (JSONException e){
            e.printStackTrace();
            return new JSONArray();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconde);

        IntentFilter filter = new IntentFilter(BIERS_UPDATE);
        Toast.makeText(getApplicationContext(),getString(R.string.dl_text),Toast.LENGTH_LONG).show();
        LocalBroadcastManager.getInstance(this).registerReceiver(new BierUpdate(),filter);
        GetBiersServices.startActionBiers(this);
        v=(RecyclerView) findViewById(R.id.rv_biere);
        v.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        BiersAdapter adapter = new BiersAdapter(getBiersFromFile());
        v.setAdapter(adapter);

    }

    public class BierUpdate extends BroadcastReceiver {

        public static final String BIERS_UPDATE = "com.optip.cours.inf4042_11.BIERS_UPDATE";

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context ,context.getString(R.string.dl_end),Toast.LENGTH_LONG).show();
            BiersAdapter adapt = (BiersAdapter) v.getAdapter();
            adapt.setNewBiere(getBiersFromFile());
        }
    }

}
