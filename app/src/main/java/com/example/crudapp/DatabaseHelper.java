package com.example.crudapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Customer.db";
    public static final String TABLE_NAME="Customer_table";
    public static final String COL_0="ID";
    public static final String COL_1="CustomerName";
    public static final String COL_2="NickName";
    public static final String COL_3="CustomerId";
    public static final String COL_4="ItemId";
    public static final String COL_5="ItemRange";
    public static final String COL_6="ItemCost";
    public static final String COL_7="ItemNumber";
    public static final String COL_8="CustomerCity";
    public static final String COL_9="State";
    public static final String COL_10="PinCode";
    public static final String COL_11="Date";
    public static final String COL_12="MobileNumber";
    public static final String COL_13="Emailid";
    public static final String COL_14="Religion";
    public static final String COL_15="Landmark";
    public static final String COL_16="OrganisationName";
    public static final String COL_17="OrganisationCity";
    public static final String COL_18="OrganisationState";



    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,CustomerName Varchar,NickName Text,CustomerId Integer,ItemId Integer, ItemRange Real, ItemCost Float,ItemNumber Integer,CustomerCity Text,State Text,PinCode Integer,Date Date,MobileNumber Integer,Emailid Text, Religion Text,Landmark Text,OrganisationName Text,OrganisationCity Text,OrganisationState Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public boolean  insertData(String CustomerName,String NickName,String CustomerId,String ItemId,String ItemRange,
                              String ItemCost,String ItemNumber,String CustomerCity,String State,String PinCode,
                              String Date,String MobileNumber,String Emailid,String Religion,String Landmark,
                              String OrganisationName,String OrganisationCity,String OrganisationState){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
//        contentValues.put(COL_0,ID);
        contentValues.put(COL_1,CustomerName);
        contentValues.put(COL_2,NickName);
        contentValues.put(COL_3,CustomerId);
        contentValues.put(COL_4,ItemId);
        contentValues.put(COL_5,ItemRange);
        contentValues.put(COL_6,ItemCost);
        contentValues.put(COL_7,ItemNumber);
        contentValues.put(COL_8,CustomerCity);
        contentValues.put(COL_9,State);
        contentValues.put(COL_10,PinCode);
        contentValues.put(COL_11,Date);
        contentValues.put(COL_12,MobileNumber);
        contentValues.put(COL_13,Emailid);
        contentValues.put(COL_14,Religion);
        contentValues.put(COL_15,Landmark);
        contentValues.put(COL_16,OrganisationName);
        contentValues.put(COL_17,OrganisationCity);
        contentValues.put(COL_18,OrganisationState);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("Select * from " +TABLE_NAME +" ORDER BY "+ COL_0 +" DESC ", null );
        return res;
    }
    public Integer deleteDataId(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return   db.delete(TABLE_NAME,"ID=?",new String[]{id});
    }

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
        db.close();
    }



    public boolean updateData(String id,String CustomerName,String NickName,String CustomerId,String ItemId,String ItemRange,
                              String ItemCost,String ItemNumber,String CustomerCity,String State,String PinCode,
                              String Date,String MobileNumber,String Emailid,String Religion,String Landmark,
                              String OrganisationName,String OrganisationCity,String OrganisationState){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL_0,id);
        values.put(COL_1,CustomerName);
        values.put(COL_2,NickName);
        values.put(COL_3,CustomerId);
        values.put(COL_4,ItemId);
        values.put(COL_5,ItemRange);
        values.put(COL_6,ItemCost);
        values.put(COL_7,ItemNumber);
        values.put(COL_8,CustomerCity);
        values.put(COL_9,State);
        values.put(COL_10,PinCode);
        values.put(COL_11,Date);
        values.put(COL_12,MobileNumber);
        values.put(COL_13,Emailid);
        values.put(COL_14,Religion);
        values.put(COL_15,Landmark);
        values.put(COL_16,OrganisationName);
        values.put(COL_17,OrganisationCity);
        values.put(COL_18,OrganisationState);

        db.update(TABLE_NAME,values,"ID= ?",new String[]{id});

        return true;
    }
}
