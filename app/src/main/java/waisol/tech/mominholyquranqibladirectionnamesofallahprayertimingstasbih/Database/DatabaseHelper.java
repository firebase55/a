package waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih.Model.NamazModel;
import waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih.R;

/**
 * Created by Tehrim Abbas on 2/19/2017.
 **/

public class DatabaseHelper extends SQLiteOpenHelper {
    //    public Blob blobImage;
    // Database Version

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "muslimpro.db";
    // Table Names
    private static final String DB_TABLE_NAMAZ = "NAMAZ";
    // Table create statement
    private static final String CREATE_TABLE_CATEGORIES = "CREATE TABLE `NAMAZ` (`id`  INTEGER PRIMARY KEY , `namaz_name` varchar(255) ,`date` varchar(255), `status` varchar(11))";


    ////

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        // db.delete(TABLE_NAME,null,null);
        //db.execSQL("delete * from"+ TABLE_NAME);

        db.execSQL("delete from " + DB_TABLE_NAMAZ);
        db.close();

    }

    public void AddNamaz(NamazModel namazModel, String status, Context context, View view) {


        if (isNamazExist(namazModel)) {
            //delete namaz
//            deleteRecord(namazModel);
            Snackbar snackbar = Snackbar.make(view, "You've already adda this Namaz", Snackbar.LENGTH_SHORT);
            snackbar.getView().setBackgroundColor(Color.RED);
            snackbar.show();
            return;
        }
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("namaz_name", namazModel.getNamazName());
        contentValues.put("date", namazModel.getGregorianDate());
        contentValues.put("status", status);
        database.insert(DB_TABLE_NAMAZ, null, contentValues);


        Snackbar snackbar = Snackbar.make(view, "Successfully Saved", Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        snackbar.show();

        Log.d("Entry", namazModel.toString());
    }

    public void QazaNamaz(NamazModel namazModel, Context context, View view) {
        if (isNamazExist(namazModel)) {
            //delete namaz
            deleteRecord(namazModel);
            Snackbar snackbar = Snackbar.make(view, "Successfully Deleted", Snackbar.LENGTH_SHORT);
            snackbar.getView().setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            snackbar.show();
        } else {
            Snackbar snackbar = Snackbar.make(view, "Error! Please Adda Namaz firstly", Snackbar.LENGTH_SHORT);
            snackbar.getView().setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            snackbar.show();
        }
    }

    public Boolean isNamazExist(NamazModel namazModel) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + DB_TABLE_NAMAZ + " WHERE `namaz_name` ='" + namazModel.getNamazName() + "' AND `date`='" + namazModel.getGregorianDate() + "'", null);
        return cursor.getCount() > 0;
    }

    public int getNamazCount(String namazname, String startDate, String endDate) {

        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + DB_TABLE_NAMAZ + " WHERE `namaz_name` ='" + namazname + "' AND `date`>'" + startDate + "' AND `date` <'" + endDate + "'";
//        String query = "SELECT * FROM " + DB_TABLE_NAMAZ + " WHERE `namaz_name` ='" + namazname + "' AND `date` <'" + endDate + "'";
        Log.d("query", query);
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            return cursor.getCount();
        } else {
            return 0;
        }
    }


    public void deleteRecord(NamazModel namazModel) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(DB_TABLE_NAMAZ, " `namaz_name` ='" + namazModel.getNamazName() + "' AND `date`='" + namazModel.getGregorianDate() + "'", null);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating table

        db.execSQL(CREATE_TABLE_CATEGORIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_NAMAZ);
        // create new table
        onCreate(db);
    }


}
