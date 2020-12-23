package com.taflord.namazvakitleri.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.taflord.namazvakitleri.Models.IlceItem;
import com.taflord.namazvakitleri.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class VakitActivity extends AppCompatActivity {


    TextView tarihTw,imsakTw,gunesTw,ogleTw,ikindiTw,aksamTw,yatsiTw;
    TextView sonrakiNamazTw;
    Toolbar toolbar;
    String url;

    RequestQueue requestQueue;

    Calendar c = Calendar.getInstance();

    SimpleDateFormat df = new SimpleDateFormat("HH:mm");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vakit);

        tarihTw = findViewById(R.id.vakitTarihTw);
        imsakTw = findViewById(R.id.vakitImsakTw);
        gunesTw = findViewById(R.id.vakitGunesTw);
        ogleTw = findViewById(R.id.vakitOgleTw);
        ikindiTw = findViewById(R.id.vakitIkindiTw);
        aksamTw = findViewById(R.id.vakitAksamTw);
        yatsiTw = findViewById(R.id.vakitYatsiTw);
        sonrakiNamazTw = findViewById(R.id.vakitSonrakiNamaz);

        toolbar = findViewById(R.id.vakitToolbar);

        requestQueue = Volley.newRequestQueue(this);

        Intent intent = getIntent();

        String position = intent.getStringExtra("position");
        String name = intent.getStringExtra("name");

        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle(name);

        System.out.println("POSITION: "+position);

        url = "https://ezanvakti.herokuapp.com/vakitler/"+position;



        sendAndRequestResponse();
    }

    private void sendAndRequestResponse() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            try {
                    JSONObject jsonObject = response.getJSONObject(0);
                    String tarih = jsonObject.getString("MiladiTarihUzun");

                    String imsak = jsonObject.getString("Imsak");
                    String gunes = jsonObject.getString("Gunes");
                    String ogle = jsonObject.getString("Ogle");
                    String ikindi = jsonObject.getString("Ikindi");
                    String aksam = jsonObject.getString("Aksam");
                    String yatsi = jsonObject.getString("Yatsi");

                    String formattedDate = (df.format(c.getTime()));

                    System.out.println("date"+formattedDate);

//                    int imsak_int = Integer.parseInt(imsak);
//                    int gunes_int = Integer.parseInt(gunes);
//                    int ogle_int = Integer.parseInt(ogle);
//                    int ikindi_int = Integer.parseInt(ikindi);
//                    int aksam_int = Integer.parseInt(aksam);
//                    int yatsi_int = Integer.parseInt(yatsi);

                    tarihTw.setText(tarih);

                    imsakTw.setText(imsak);
                    gunesTw.setText(gunes);
                    ogleTw.setText(ogle);

                    ikindiTw.setText(ikindi);
                    aksamTw.setText(aksam);
                    yatsiTw.setText(yatsi);


            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }



        }, volleyError -> System.out.println(volleyError.getMessage()));

        requestQueue.add(request);
    }
}