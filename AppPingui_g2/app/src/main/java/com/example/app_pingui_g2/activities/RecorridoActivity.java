package com.example.app_pingui_g2.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RecorridoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RequestQueue mRequestQueue;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorrido);
        token = getIntent().getStringExtra("token");
        final LinearLayout linearLayout = findViewById(R.id.linear2);

        mRequestQueue = Volley.newRequestQueue(this);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        //recyclerView.setHasFixedSize(true);

        for(int i=1;i<21;i++){
            String url = "https://amstdb.herokuapp.com/db/recorrido/"+i;

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    System.out.println(response);
                    TextView camion = new TextView(RecorridoActivity.this);
                    camion.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextView begin = new TextView(RecorridoActivity.this);
                    begin.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextView end = new TextView(RecorridoActivity.this);
                    end.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextView origen = new TextView(RecorridoActivity.this);
                    origen.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextView destino = new TextView(RecorridoActivity.this);
                    destino.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    try {
                        camion.setText("Camion: "+String.valueOf(response.getInt("camion")));
                        begin.setText("Fecha de inicio: "+response.getString("fecha_inicio").substring(0,10));
                        end.setText("Fecha de Fin: "+response.getString("fecha_fin").substring(0,10));
                        origen.setText("Origen: "+response.getString("origen"));
                        destino.setText("Destino: "+response.getString("destino"));
                        linearLayout.addView(camion);
                        linearLayout.addView(begin);
                        linearLayout.addView(end);
                        linearLayout.addView(origen);
                        linearLayout.addView(destino);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    //return super.getHeaders();
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Authorization", "JWT " + token); System.out.println(token);
                    return params;
                }
            };
            mRequestQueue.add(request);
        }


        // use a linear layout manager


        // specify an adapter (see also next example)



        //mAdapter = new ItemAdapter(myDataset);
        //recyclerView.setAdapter(mAdapter);
    }
}
