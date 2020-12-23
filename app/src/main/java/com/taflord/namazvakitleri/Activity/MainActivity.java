package com.taflord.namazvakitleri.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.taflord.namazvakitleri.Adapters.CountryAdapter;
import com.taflord.namazvakitleri.Models.CountryItem;
import com.taflord.namazvakitleri.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CountryAdapter.OnCountryListener {



    RequestQueue requestQueue;

    RecyclerView recyclerView;
    Context context = this;

    ArrayList<CountryItem> arrayList = new ArrayList<>();

    CountryAdapter adapter = new CountryAdapter(this,arrayList,this);


    String url = "https://ezanvakti.herokuapp.com/ulkeler";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(context);
        recyclerView = findViewById(R.id.countryRecylerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


        sendAndRequestResponse();

    }


    private void sendAndRequestResponse() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            try {
                for (int i = 0; i <response.length() ; i++) {
                    JSONObject jsonObject = response.getJSONObject(i);
                    String country = jsonObject.getString("UlkeAdi");
                    String country_id = jsonObject.getString("UlkeID");
                    arrayList.add(new CountryItem(country,country_id));
                    adapter.setData(arrayList);
                }


            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }



        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println(volleyError.getMessage());
            }
        });

        requestQueue.add(request);
    }

    @Override
    public void OnCountryClick(int position) {

    }
}