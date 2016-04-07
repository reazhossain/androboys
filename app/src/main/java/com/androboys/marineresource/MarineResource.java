package com.androboys.marineresource;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import model.WeatherDetails;

public class MarineResource extends AppCompatActivity {

    Button btn_JoloJatra,btn_FishCultivation;
    public String URL = "http://api.openweathermap.org/data/2.5/weather?q=Dhaka&units=metric&APPID=d10dc496004ac285fa0712b87f9f7cc6";
    private ProgressDialog pDialog;
    public WeatherDetails wDetails = new WeatherDetails();

    TextView cityNameTV;
    TextView tempTV;
    TextView wDescriptionTV;
    TextView humidityTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marine_resource);

        initializeAll();
        btn_JoloJatra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_JoloJatra = new Intent(MarineResource.this, JoloJatra.class);
                startActivity(intent_JoloJatra);
            }
        });

        btn_FishCultivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_FishCultivation=new Intent(MarineResource.this,FishCultivation.class);
                startActivity(intent_FishCultivation);
            }
        });

        cityNameTV = (TextView) findViewById(R.id.city_field);
        tempTV = (TextView) findViewById(R.id.weather_icon);
        humidityTV = (TextView) findViewById(R.id.details_field);
        wDescriptionTV = (TextView) findViewById(R.id.current_temperature_field);
        new LoadWeatherDetailsFromResource().execute();
    }



    private void initializeAll() {
        btn_JoloJatra = (Button) findViewById(R.id.btn_JoloJatra);
        btn_FishCultivation = (Button) findViewById(R.id.btn_FishCultivation);
    }
        class LoadWeatherDetailsFromResource extends AsyncTask<String, String, String> {
            // Show Progress bar before downloading Music
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // Shows Progress Bar Dialog and then call doInBackground method
                pDialog = new ProgressDialog(MarineResource.this);
                // Showing progress dialog before making http request
                pDialog.setMessage("Loading Weather Details.. ");
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCanceledOnTouchOutside(false);
                pDialog.setMax(30000);
                pDialog.show();
            }

            // Download Music File from Internet

            @Override
            protected String doInBackground(String... f_url) {
                try {
                    //Toast.makeText(CountryListActivity.this, "lol", Toast.LENGTH_SHORT).show();
                    JsonObjectRequest mainRequest = new JsonObjectRequest(URL, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject object) {
                            //    hidePDialog();
                            try {
                                // Toast.makeText(CountryListActivity.this, countryGlobalCode, Toast.LENGTH_SHORT).show();
//                            assert object != null;
                                JSONObject weatherObj = object.getJSONArray("weather").getJSONObject(0);

                                wDetails.setDescription(weatherObj.getString("description"));
                                wDetails.setLatitude(object.getJSONObject("coord").getString("lat"));
                                wDetails.setLongititute(object.getJSONObject("coord").getString("lon"));
                                wDetails.setTemperature(object.getJSONObject("main").getString("temp"));
                                wDetails.setHumidity(object.getJSONObject("main").getString("humidity"));
                                wDetails.setMinTemp(object.getJSONObject("main").getString("temp_min"));
                                wDetails.setMaxTemp(object.getJSONObject("main").getString("temp_max"));
                                wDetails.setWindSpeed(object.getJSONObject("wind").getString("speed"));
                                wDetails.setWindDegree(object.getJSONObject("wind").getString("deg"));
                                wDetails.setCityName(object.getString("name"));

                                cityNameTV.setText(wDetails.getCityName());
                                humidityTV.setText(wDetails.getHumidity());
                                tempTV.setText(wDetails.getTemperature());
                                wDescriptionTV.setText(wDetails.getDescription());



//                            for (int i = 0; i < jsonArray.length(); i++) {
//
//                                JSONObject obj = jsonArray.getJSONObject(i);
//
//                                CountryListModel model = new CountryListModel();
//
//                                model.setCountryName(obj.getString("NAME"));
//                                model.setCountryCode(obj.getString("CODE"));
//
//
//
//                            }
                                //countryListAdapter.notifyDataSetChanged();
                                //Log.i(">>>URL<<<", customURL);
                                //Log.i(">>>pageCounter<<<", String.valueOf( pageCount));
                                //Log.i(">>>Total Page<<<", String.valueOf(  totalpage));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {



                        }
                    });
                    int socketTimeout = 30000;//30 seconds - change to what you want
                    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                    mainRequest.setRetryPolicy(policy);
                    // Adding request to request queue
                   // AppController.getInstance().addToRequestQueue(mainRequest);
                    RequestQueue requestQueue = Volley.newRequestQueue(MarineResource.this);
                    requestQueue.add(mainRequest);


                } catch (Exception e) {
                    Log.i("<<>>>>", e.getMessage().toString());
                }



                return null;

            }
// While Downloading Music File

            protected void onProgressUpdate(String... progress) {
                // Set progress percentage
                pDialog.setProgress(Integer.parseInt(progress[0]));
            }
// Once Music File is downloaded

            @Override
            protected void onPostExecute(String file_url) {

                hidePDialog();
            }


        }// Async Task Class END

    private void hidePDialog() {

        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }

    }


}
