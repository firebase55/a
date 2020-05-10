package waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    public static final String EXTRA_REMINDER_ID = "Reminder_ID";

    private static AppController mInstance;

    @Override
    public void onCreate(){
        super.onCreate();
        mInstance = this;
    }
    public static synchronized AppController getInstance(){
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }
    public ImageLoader getmImageLoader(){
        getRequestQueue();
        if (mImageLoader == null){
            mImageLoader = new ImageLoader(this.mRequestQueue, new LruBitmapCache());
        }
        return this.mImageLoader;
    }
    public <T> void addToRequestQueue(Request<T> req, String tag){

        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }
    public <T> void addToRequestQueue(Request <T> req){
        req.setTag(TAG);
        getRequestQueue().add(req);
    }
    public void cancelPendingRequests(Object tag){
        if (mRequestQueue != null){
            mRequestQueue.cancelAll(tag);
        }
    }



    public static String LOG = "response";
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    Context context;
    String isAlarmSet = "isAlarmSet";
    private String shared_preferences_name = "muslimPro";
    private String location = "location";


    public void setIsAlarmSet(Context context, boolean action) {

        sharedPreferences = context.getSharedPreferences(shared_preferences_name, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if (action)
            editor.putBoolean(isAlarmSet, true);
        else
            editor.putBoolean(isAlarmSet, false);
        editor.apply();
    }


    public void setData(Context context, String key, String data) {
        sharedPreferences = context.getSharedPreferences(shared_preferences_name, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(key, data);
        editor.apply();
    }

    public String getData(Context context, String key) {
        sharedPreferences = context.getSharedPreferences(shared_preferences_name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "0");
    }


    public boolean isLocationSet(Context context) {
        sharedPreferences = context.getSharedPreferences(shared_preferences_name, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(location, false);
    }

    public boolean getIsAlarmSet(Context context) {
        sharedPreferences = context.getSharedPreferences(shared_preferences_name, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(isAlarmSet, false);
    }
}
