package com.pramod.autotextview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.pramod.autotextview.api.ApiClient;
import com.pramod.autotextview.api.ApiInterface;
import com.pramod.autotextview.districtRes.DistrictRes;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView text ,text4;
    ArrayList<String> RegionNameID = new ArrayList<>();
    ArrayList<String> RegionName = new ArrayList<>();

    ArrayList<String> DistricName = new ArrayList<>();
    ArrayList<String> DistricNameID = new ArrayList<>();

    public String RegionId = null;

    // MultiAutoCompleteTextView text1;
    // String[] languages={"Android ","java","IOS","SQL","JDBC","Web services"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onRegion();





        text = (AutoCompleteTextView) findViewById(R.id.text1);


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, DistricName);

        text.setAdapter(adapter);
        text.setThreshold(1);

        text.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
              //  String selection = (String) parent.getItemAtPosition(i);

                             RegionId =RegionNameID.get(i);

                       Log.d(RegionId,"RegionId");
                            onDistric(RegionId);
               // Toast.makeText(getApplicationContext(), RegionId, Toast.LENGTH_LONG).show();

            }
        });

        text4 = (AutoCompleteTextView) findViewById(R.id.text2);

        ArrayAdapter adapter21= new ArrayAdapter(this, android.R.layout.simple_list_item_1, DistricName);

        text4.setAdapter(adapter21);
        text4.setThreshold(1);


        text4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                //String selection = (String) parent.getItemAtPosition(i);

                  String s =DistricNameID.get(i);
               Toast.makeText(getApplicationContext(),""+DistricNameID, Toast.LENGTH_LONG).show();

            }
        });
    }



    private void onDistric(String RegionId) {



        ApiInterface apiService =
                ApiClient.getClient(getApplicationContext()).create(ApiInterface.class);

        Call<DistrictRes> companyResponseCall = apiService.getdistrict(RegionId);

        companyResponseCall.enqueue(new Callback<DistrictRes>() {
            @Override
            public void onResponse(Call<DistrictRes> call, Response<DistrictRes> response) {

                try {


                    DistrictRes districModel = response.body();
                    // Boolean status  = comapnyResponse.getStatus();
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    Log.e("Soceity Resp", gson.toJson(districModel));
                    DistricName.clear();
                    DistricNameID.clear();


                    DistricName.add("Select District");
                    DistricNameID.add("38");
                    if (districModel.getCheck() == 1) {
                        for (int i = 0; i < districModel.getData().size(); i++) {

                            DistricName.add(districModel.getData().get(i).getDistrict());
                            DistricNameID.add(districModel.getData().get(i).getId());

                            String sd = districModel.getData().get(i).getDistrict();
                            Log.d(sd,"Mumbai");
                            Log.e(sd,"test");
                        }

                        ArrayAdapter<String> dataAdapter12 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, DistricName);


                        // Drop down layout style - list view with radio button
                        dataAdapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        // attaching data adapter to spinner
                        text4.setAdapter(dataAdapter12);

                    } else {
                        Toast.makeText(getApplicationContext(), "Region name not available", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onFailure(Call<DistrictRes> call, Throwable t) {
            }
        });


    }


















    private void onRegion() {


        ApiInterface apiService =
                ApiClient.getClient(getApplicationContext()).create(ApiInterface.class);

        Call<RegionRes> companyResponseCall = apiService.getRegion();

        companyResponseCall.enqueue(new Callback<RegionRes>() {
            @Override
            public void onResponse(Call<RegionRes> call, Response<RegionRes> response) {

                try {


                    RegionRes districModel = response.body();
                    // Boolean status  = comapnyResponse.getStatus();
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    Log.e("Regsion Resp", gson.toJson(districModel));
                    RegionName.clear();
                    RegionNameID.clear();

                  //  RegionName.add("Select Region");
                    //RegionNameID.add("125");
                    if (districModel.getCheck() == 1) {
                        for (int i = 0; i < districModel.getData().size(); i++) {

                            RegionName.add(districModel.getData().get(i).getRegion());
                            RegionNameID.add(districModel.getData().get(i).getId());


                            String sd = districModel.getData().get(i).getRegion();
                            Log.d(sd,"Delhi");
                        }
                        ArrayAdapter<String> dataAdapter12 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, RegionName);

                        // Drop down layout style - list view with radio button
                        dataAdapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        // attaching data adapter to spinner
                        text.setAdapter(dataAdapter12);
                    } else {
                        Toast.makeText(getApplicationContext(), "Regsion name not available", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onFailure(Call<RegionRes> call, Throwable t) {
            }
        });


    }

}


