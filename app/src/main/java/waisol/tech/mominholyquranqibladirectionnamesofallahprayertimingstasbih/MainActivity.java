package waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.HijrahDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih.Database.ReminderDatabase;
import waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih.Model.DifferenceModel;
import waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih.Model.NamazModel;
import waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih.Model.Reminder;
import waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih.Notification.AlarmReceiver;

import static android.os.Build.VERSION_CODES.O;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    private static final String TAG = "tag";
    String url = "https://muslimsalat.com/pakistan/daily.json?key=c02ba23da5039cf700117dc2f77a7bd1";
    String tag_json_obj = "json_obj_req";
    TextView tFajrTv, tDhuhrTv, tAsrTv, tMaghribTv, tIshaTv, tLocationTv, tDateTv, tHijriTv, tLocationTv1;
    ArrayList<NamazModel> namazModelArrayList;

    ArrayList<DifferenceModel> differenceModels;
    ArrayList<Long> integerArrayList;


    ReminderDatabase rb;
    private Calendar mCalendar;
    //    private Calendar mCalendar;
    private int mYear, mMonth, mHour, mMinute, mDay;
    private String mTitle;
    private String mTime;
    private String mDate;
    private String mActive;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.myToolbar);
        toolbar.setVisibility(View.VISIBLE);

        tHijriTv = findViewById(R.id.tHijriTv);
        tFajrTv = findViewById(R.id.myfajrTv);
        tDhuhrTv = findViewById(R.id.mydhuhrTv);
        tAsrTv = findViewById(R.id.myasrTv);
        tMaghribTv = findViewById(R.id.mymaghribTv);
        tIshaTv = findViewById(R.id.myishaTv);
        tLocationTv = findViewById(R.id.mylocationTv);
        tLocationTv1 = findViewById(R.id.mylocationTv1);
        tDateTv = findViewById(R.id.mydateTv);


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);



        searchLocation();


        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {

                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {

                            @RequiresApi(api = O)
                            @Override
                            public void run() {

                                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                                    getLocation();
                                }else {
                                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                                }


                                Intent intent = new Intent(MainActivity.this, ReminderBroadcast.class);
                                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
                                Calendar calendar = Calendar.getInstance();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
                                String dateTime = simpleDateFormat.format(calendar.getTime());
                                tDateTv.setText(dateTime);


// convert to hijrah





                            }
                        });
                    }
                } catch (Exception e) {
                    tDateTv.setText(R.string.app_name);
                }
            }

            private void getLocation() {
                fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location !=null){


                                Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                            List<Address> addresses = null;
                            try {
                                addresses = geocoder.getFromLocation(
                                        location.getLatitude(), location.getLongitude(), 1);

                                tLocationTv1.setText(Html.fromHtml(addresses.get(0).getLocality()
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

        rb = new ReminderDatabase(MainActivity.this);
        mCalendar = Calendar.getInstance();
        mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        mMinute = mCalendar.get(Calendar.MINUTE);
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH) + 1;
        mDay = mCalendar.get(Calendar.DATE);

    }


    private void searchLocation() {


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String country = response.get("country").toString();
                            String state = response.get("state").toString();
                            String city = response.get("city").toString();
                            String location = country + ", " + state + ", " + city;


                            String mFajr = response.getJSONArray("items").getJSONObject(0).get("fajr").toString();
                            String mDhuhr = response.getJSONArray("items").getJSONObject(0).get("dhuhr").toString();
                            String mAsr = response.getJSONArray("items").getJSONObject(0).get("asr").toString();
                            String mMaghrib = response.getJSONArray("items").getJSONObject(0).get("maghrib").toString();
                            String mIsha = response.getJSONArray("items").getJSONObject(0).get("isha").toString();
                            String gregorianDate = response.getJSONArray("items").getJSONObject(0).get("date_for").toString();

                            tFajrTv.setText(mFajr);
                            tDhuhrTv.setText(mDhuhr);
                            tAsrTv.setText(mAsr);
                            tMaghribTv.setText(mMaghrib);
                            tIshaTv.setText(mIsha);
                            tLocationTv.setText(location);

                            namazModelArrayList = new ArrayList<>();
                            differenceModels = new ArrayList<>();
                            integerArrayList = new ArrayList<>();


                            namazModelArrayList.add(new NamazModel("Fajr",
                                    mFajr,
                                    gregorianDate, gregorianDate, false, false, false
                            ));
                            namazModelArrayList.add(new NamazModel("Dhuhr",
                                    mDhuhr,
                                    gregorianDate, gregorianDate, false, false, false
                            ));
                            namazModelArrayList.add(new NamazModel("Asr",
                                    mAsr,
                                    gregorianDate, gregorianDate, false, false, false
                            ));
                            namazModelArrayList.add(new NamazModel("Maghrib",
                                    mMaghrib,
                                    gregorianDate, gregorianDate, false, false, false
                            ));
                            namazModelArrayList.add(new NamazModel("Isha",
                                    mIsha,
                                    gregorianDate, gregorianDate, false, false, false
                            ));

                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                            String time = sdf.format(new Date());
                            Date currenTime, namazTimes;
                            for (int i = 0; i < namazModelArrayList.size(); i++) {
                                String namazTime = namazModelArrayList.get(i).getTime();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                                try {
                                    currenTime = simpleDateFormat.parse(time);
                                    namazTimes = simpleDateFormat.parse(namazTime);
                                    long difference = namazTimes.getTime() - currenTime.getTime();
                                    int days = (int) (difference / (1000 * 60 * 60 * 24));
                                    int hours = (int) ((difference - (1000 * 60 * 60 * 24 * days)) / (1000 * 60 * 60));
                                    int min = (int) (difference - (1000 * 60 * 60 * 24 * days) - (1000 * 60 * 60 * hours)) / (1000 * 60);
                                    int seconds = hours * 3600;
                                    hours = (hours < 0 ? -hours : hours);
                                    Log.i("difference", difference + " \n" + hours + " and " + min);
                                    integerArrayList.add(difference);
                                    if (hours == 0 && min < 0) {
                                        differenceModels.add(new DifferenceModel(namazModelArrayList.get(i).getNamazName(), namazTime, "It's prayer time "));
                                    } else if (hours == 0) {
                                        differenceModels.add(new DifferenceModel(namazModelArrayList.get(i).getNamazName(), namazTime, min + " minutes to go"));
                                    } else {
                                        differenceModels.add(new DifferenceModel(namazModelArrayList.get(i).getNamazName(), namazTime, hours + " hours and " + min + " minutes to go"));
                                    }

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                setReminders();
//                        check if namaz time has passed or gone

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(MainActivity.this, "Internet connection error", Toast.LENGTH_SHORT).show();

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }


    public void menus(View view) {
        startActivity(new Intent(this, SettingActivity.class));
    }

    public void quran(View view) {
        startActivity(new Intent(this, QnextActivity.class));
    }

    public void qibla(View view) {
        startActivity(new Intent(this, QiblaNextActivity.class));
    }

    public void Allah_names(View view) {
        startActivity(new Intent(this, NamesActivity.class));
    }

    public void azkar(View view) {
        startActivity(new Intent(this, DuaActivity.class));
    }

    public void talawat_next(View view) {
        startActivity(new Intent(this, TalwatNextActivity.class));
    }

    public void azan_next(View view) {
        startActivity(new Intent(this, AzanNext.class));
    }

    public void mosque_finder_next(View view) {

        startActivity(new Intent(this, MapsActivity.class));
    }

    public void tasbih_next(View view) {

        startActivity(new Intent(this, TasbihNextActivity.class));
    }

    public void prayer(View view) {
        startActivity(new Intent(this, AutomaticActivity.class));
    }


    private void setReminders() {
        if (!AppController.getInstance().getIsAlarmSet(MainActivity.this)) {
            for (int i = 0; i < namazModelArrayList.size(); i++) {
                Date currentDate, selectedDate;

                Calendar calendar = Calendar.getInstance();

                int year, month, day, hours, minutes;

                String stringDate = namazModelArrayList.get(i).getGregorianDate();
                String stringTime = namazModelArrayList.get(i).getTime();

                String[] timeAndMinutes = stringTime.split(":");
                String[] splittedDate = stringDate.split("-");

                day = Integer.parseInt(splittedDate[2]);
                month = Integer.parseInt(splittedDate[1]);
                year = Integer.parseInt(splittedDate[0]);

                hours = Integer.parseInt(timeAndMinutes[0]);
                minutes = Integer.parseInt(timeAndMinutes[1].substring(0, timeAndMinutes[1].length() - 3));

                currentDate = calendar.getTime();

                calendar.set(year, month - 1, day, hours, minutes);

                selectedDate = calendar.getTime();

//                Log.d("current", "Hours " + hours + "");
//                Log.d("current", "Minutes " + minutes + "");

                saveReminder(i, year, month, day, hours, minutes);
                AppController.getInstance().setIsAlarmSet(MainActivity.this, true);

               /*
                if (selectedDate.after(currentDate)) {
//                add reminder
                    saveReminder(i, year, month, day, hours, minutes);
                    AppController.getInstance().setIsAlarmSet(MainActivity.this, true);
                } else if (selectedDate.before(currentDate)) {
                    Log.d("response", "Previous Namaz");
                }*/
            }
        }


    }

    private void saveReminder(int position, int year, int month, int day, int hours, int minutes) {

        mTitle = "Time for " + namazModelArrayList.get(position).getNamazName();
        mDate = namazModelArrayList.get(position).getGregorianDate();
        mTime = namazModelArrayList.get(position).getTime();
        mActive = "true";
        mYear = year;
        mMonth = month;
        mDay = day;
        mHour = hours;
        mMinute = minutes;

        // Creating Reminder
        Reminder reminder = new Reminder(mTitle, mDate, mTime, mActive);

        int ID = rb.addReminder(reminder);

        // Set up calender for creating the notification
        mCalendar.set(Calendar.MONTH, --mMonth);
        mCalendar.set(Calendar.YEAR, mYear);
        mCalendar.set(Calendar.DAY_OF_MONTH, mDay);
        mCalendar.set(Calendar.HOUR_OF_DAY, mHour);
        mCalendar.set(Calendar.MINUTE, mMinute);
        mCalendar.set(Calendar.SECOND, 0);

        new AlarmReceiver().setAlarm(MainActivity.this, mCalendar, ID);

        Log.d("response", "Reminder saved: " + reminder.toString());
    }

}
