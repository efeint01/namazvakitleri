package com.taflord.namazvakitleri.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.taflord.namazvakitleri.Adapters.IlceAdapter;
import com.taflord.namazvakitleri.Models.IlceItem;
import com.taflord.namazvakitleri.Models.SehirItem;
import com.taflord.namazvakitleri.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class IlceActivity extends AppCompatActivity implements IlceAdapter.OnIlceListener {

    Context context = this;

    ArrayList<IlceItem> arrayList = new ArrayList<>();
    RecyclerView recyclerView;
    IlceAdapter adapter = new IlceAdapter(context,arrayList,this) ;
    RequestQueue requestQueue;


    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilce);

        Intent intent = getIntent();

        String position = intent.getStringExtra("position");


        System.out.println("POSITION: "+position);

        url = "https://ezanvakti.herokuapp.com/ilceler/"+position;



        requestQueue = Volley.newRequestQueue(context);
        recyclerView = findViewById(R.id.ilceRecylerView);
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
                    String ilce_adi = jsonObject.getString("IlceAdi");
                    String ilce_id = jsonObject.getString("IlceID");
                    arrayList.add(new IlceItem(ilce_adi,ilce_id));
                    adapter.setData(arrayList);
                }


            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }



        }, volleyError -> System.out.println(volleyError.getMessage()));

        requestQueue.add(request);
    }

    @Override
    public void OnIlceClick(int position) {

    }
}