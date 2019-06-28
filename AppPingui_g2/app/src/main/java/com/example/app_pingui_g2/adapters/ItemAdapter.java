package com.example.app_pingui_g2.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app_pingui_g2.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
    private List<JSONObject> mDataset;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView fecha;
        public TextView temp;
        public TextView recorrido;

        public MyViewHolder(TextView v) {
            super(v);
            temp = v;
            fecha = v;
            recorrido =v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ItemAdapter(List<JSONObject> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        try {
            holder.fecha.setText(mDataset.get(position).getString("fecha_registro"));
            holder.temp.setText(String.valueOf(mDataset.get(position).getDouble("temperatura")));
            holder.recorrido.setText(mDataset.get(position).getInt("fecha_registro"));

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
