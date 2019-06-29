package com.example.app_pingui_g2.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_pingui_g2.R;
import com.example.app_pingui_g2.adapters.ItemAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemperatureActivity extends AppCompatActivity {
    private boolean isFinished = false;
    private  RecyclerView recyclerView;
    private int a =0;
    private RequestQueue mRequestQueue;
    private String token;
    private ItemAdapter mAdapter;
    private List<JSONObject> temeperaturas = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        token = getIntent().getStringExtra("token");

        mRequestQueue = Volley.newRequestQueue(this);
        final LinearLayout linearLayout = findViewById(R.id.linear);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView


        for (int i =1 ; i<21; i++){
            String url = "https://amstdb.herokuapp.com/db/registroDeFrios/"+i;

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    System.out.println(response);
                    TextView id = new TextView(TemperatureActivity.this);
                    id.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextView v = new TextView(TemperatureActivity.this);
                    v.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextView temp = new TextView(TemperatureActivity.this);
                    temp.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextView recorrido = new TextView(TemperatureActivity.this);
                    recorrido.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    try {

                        id.setText("ID: "+String.valueOf(response.getInt("id")));
                        v.setText("Fecha: "+response.getString("fecha_registro").substring(0,11));
                        temp.setText("Temperatura: "+String.valueOf(response.getDouble("temperatura")));
                        recorrido.setText("Recorrido: "+String.valueOf(response.getInt("recorrido")));
                        linearLayout.addView(id);
                        linearLayout.addView(v);
                        linearLayout.addView(temp);
                        linearLayout.addView(recorrido);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    //return super.getHeaders();
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Authorization", "JWT " + token);
                    System.out.println(token);
                    return params;
                }
            };
            mRequestQueue.add(request);

        }


        // use a linear layout manager



    }


}
