package com.taflord.namazvakitleri.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.taflord.namazvakitleri.Adapters.CountryAdapter;
import com.taflord.namazvakitleri.Adapters.SehirAdapter;
import com.taflord.namazvakitleri.Models.CountryItem;
import com.taflord.namazvakitleri.Models.SehirItem;
import com.taflord.namazvakitleri.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SehirActivity extends AppCompatActivity implements SehirAdapter.OnSehirListener {


    Context context = this;
    RecyclerView recyclerView;
    ArrayList<SehirItem> arrayList = new ArrayList<>();
    SehirAdapter adapter = new SehirAdapter(context,arrayList,this);

    RequestQueue requestQueue;

    String url;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sehir);

        Intent intent = getIntent();

        String position = intent.getStringExtra("position");

        System.out.println("POSITION: "+position);

        url = "https://ezanvakti.herokuapp.com/sehirler/"+position;

     //   url.concat(position);

        requestQueue = Volley.newRequestQueue(context);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);

        recyclerView = findViewById(R.id.sehirRecylerView);
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
                    String sehir = jsonObject.getString("SehirAdi");
                    String sehir_id = jsonObject.getString("SehirID");
                    arrayList.add(new SehirItem(sehir,sehir_id));
                    adapter.setData(arrayList);
                }


            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }



        }, volleyError -> System.out.println(volleyError.getMessage()));

        requestQueue.add(request);
    }

    @Override
    public void OnSehirClick(int position) {

    }
}