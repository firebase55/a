package waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih;


import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.PowerManager;
import android.text.Html;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static android.os.Build.VERSION_CODES.Q;


public class AutomaticActivity extends AppCompatActivity {

    private static final String TAG = "tag";
    String url = "https://muslimsalat.com/pakistan/daily.json?key=c02ba23da5039cf700117dc2f77a7bd1";
    String tag_json_obj = "json_obj_req";
    TextView tFajrTv, tDhuhrTv, tAsrTv, tMaghribTv, tIshaTv, locationTv1, dateTv1;
    FusedLocationProviderClient  fusedLocationProviderClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automatic);



        tFajrTv = findViewById(R.id.fajrTv1);
        tDhuhrTv = findViewById(R.id.dhuhrTv1);
        tAsrTv = findViewById(R.id.asrTv1);
        tMaghribTv = findViewById(R.id.maghribTv1);
        tIshaTv = findViewById(R.id.ishaTv1);
        locationTv1 = findViewById(R.id.locationTv1);
        dateTv1 = findViewById(R.id.dateTv1);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        searchLocation();

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {

                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {

                            @RequiresApi(api = Q)
                            @Override
                            public void run() {

                                if (ActivityCompat.checkSelfPermission(AutomaticActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                                    getLocation();
                                }else {
                                    ActivityCompat.requestPermissions(AutomaticActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                                }

                                Calendar calendar = Calendar.getInstance();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
                                String dateTime = simpleDateFormat.format(calendar.getTime());
                                dateTv1.setText(dateTime);
                            }
                        });
                    }
                } catch (Exception e) {
                }
            }

            private void getLocation() {
                fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location !=null){


                            Geocoder geocoder = new Geocoder(AutomaticActivity.this, Locale.getDefault());
                            List<Address> addresses = null;
                            try {
                                addresses = geocoder.getFromLocation(
                                        location.getLatitude(), location.getLongitude(), 1);

                                locationTv1.setText(Html.fromHtml(addresses.get(0).getLocality()
                                ));

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        };
        thread.start();

    }

    private void searchLocation() {


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            String mFajr = response.getJSONArray("items").getJSONObject(0).get("fajr").toString();
                            String mDhuhr = response.getJSONArray("items").getJSONObject(0).get("dhuhr").toString();
                            String mAsr = response.getJSONArray("items").getJSONObject(0).get("asr").toString();
                            String mMaghrib = response.getJSONArray("items").getJSONObject(0).get("maghrib").toString();
                            String mIsha = response.getJSONArray("items").getJSONObject(0).get("isha").toString();
                            tFajrTv.setText(mFajr);
                            tDhuhrTv.setText(mDhuhr);
                            tAsrTv.setText(mAsr);
                            tMaghribTv.setText(mMaghrib);
                            tIshaTv.setText(mIsha);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(AutomaticActivity.this, "Internet connection error", Toast.LENGTH_SHORT).show();

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
