package com.example.victor.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by victor on 15/11/2016.
 */

class BiersAdapter extends RecyclerView.Adapter<BiersAdapter.BierHolder> {
    private JSONArray biers;

    public BiersAdapter(JSONArray biers) {
        this.biers = biers;
    }

    @Override
    public BierHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_bier_element, parent, false);
        return new BierHolder(v);
    }

    @Override
    public void onBindViewHolder(BierHolder holder, int position) {
        try {
            JSONObject biersObject = biers.getJSONObject(position);
            String name = biersObject.getString("name");
            holder.name.setText(name);
        } catch (JSONException e) {

        }
    }

    public void setNewBiere(JSONArray tab){
        this.biers = tab;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return biers.length();
    }

    public class BierHolder extends RecyclerView.ViewHolder {

        public TextView name;

        public BierHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.rv_bier_element_name);

        }

    }
}

