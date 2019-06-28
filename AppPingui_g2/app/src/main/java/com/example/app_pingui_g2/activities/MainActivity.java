package com.example.app_pingui_g2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_pingui_g2.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button mLogIn;
    private EditText mUsername;
    private EditText mPassword;
    private RequestQueue mRequestQueue;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRequestQueue = Volley.newRequestQueue(this);


        mLogIn = findViewById(R.id.logIn);

        mLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUsername = findViewById(R.id.username);
                String username = mUsername.getText().toString();

                mPassword = findViewById(R.id.password);
                String password = mPassword.getText().toString();

                String url = "https://amstdb.herokuapp.com/db/nuevo-jwt";
                Map<String,String> params = new HashMap<>();
                params.put("username",username);
                params.put("password",password);
                JSONObject parametros = new JSONObject(params);

                JsonObjectRequest request;
                request = new JsonObjectRequest(Request.Method.POST, url, parametros, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        try{
                            token = response.getString("token");
                            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                            intent.putExtra("token",token);
                            startActivity(intent);



                        } catch (Exception e ){}
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"Clave Incorrexta",
                                Toast.LENGTH_LONG).show();
                    }
                });

            mRequestQueue.add(request);

            }
        });





    }
}
