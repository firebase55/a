package waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;


public class PrayerNextActivity extends AppCompatActivity {

    private static final String TAG = "tag";
    String url;
    String tag_json_obj = "json_obj_req";
    Toolbar toolbar;
    ProgressDialog pDialog;
    TextView mFajrTv, mDhuhrTv, mAsrTv, mMaghribTv, mIshaTv, mLocationTv, mDateTv;
    EditText mSearchEt;
    Button mSearchBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer_next);

        toolbar = findViewById(R.id.prayerToolbar);
        mFajrTv =findViewById(R.id.fajrTv);
        mDhuhrTv =findViewById(R.id.dhuhrTv);
        mAsrTv =findViewById(R.id.asrTv);
        mMaghribTv =findViewById(R.id.maghribTv);
        mIshaTv =findViewById(R.id.ishaTv);
        mLocationTv =findViewById(R.id.locationTv);
        mDateTv =findViewById(R.id.dateTv);
        mSearchEt = findViewById(R.id.searchEt);
        mSearchBtn = findViewById(R.id.searchBtn);

        toolbar.setVisibility(View.VISIBLE);


        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mLocation = mSearchEt.getText().toString().trim();

                if (mLocation.isEmpty()){
                    Toast.makeText(PrayerNextActivity.this, "Please Enter Location", Toast.LENGTH_SHORT).show();
                }else {
                    url = "https://muslimsalat.com/"+mLocation+".json?key=c02ba23da5039cf700117dc2f77a7bd1";
                    searchLocation();
                }
            }
        });

    }

    private void searchLocation() {

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String country = response.get("country").toString();
                            String state = response.get("state").toString();
                            String city = response.get("city").toString();
                            String location = country +", "+ state +", "+ city;


                            String date = response.getJSONArray("items").getJSONObject(0).get("date_for").toString();

                            String mFajr = response.getJSONArray("items").getJSONObject(0).get("fajr").toString();
                            String mDhuhr = response.getJSONArray("items").getJSONObject(0).get("dhuhr").toString();
                            String mAsr = response.getJSONArray("items").getJSONObject(0).get("asr").toString();
                            String mMaghrib = response.getJSONArray("items").getJSONObject(0).get("maghrib").toString();
                            String mIsha = response.getJSONArray("items").getJSONObject(0).get("isha").toString();

                            mFajrTv.setText(mFajr);
                            mDhuhrTv.setText(mDhuhr);
                            mAsrTv.setText(mAsr);
                            mMaghribTv.setText(mMaghrib);
                            mIshaTv.setText(mIsha);
                            mLocationTv.setText(location);
                            mDateTv.setText(date);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(PrayerNextActivity.this, "Internet connection error", Toast.LENGTH_SHORT).show();
                pDialog.hide();
            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    public void back(View view) {

        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
