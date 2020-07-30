package com.example.crudapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static com.example.crudapp.DatabaseHelper.TABLE_NAME;

public class MultipleActivity extends AppCompatActivity  {
    EditText edttxt;
    TextView txtmutiple;
    Button btn;
    MainActivity mainActivity;
    DatabaseHelper mydb;
    String id,CustomerName,NickName, CustomerId, ItemId,  ItemCost, ItemNumber, CustomerCity, State, PinCode,
            Date, MobileNumber, Emailid, Religion, Landmark, OrganisationName, OrganisationCity, OrganisationState;
    String ItemRange;
    //    String id;
    Delete delete;
    int counter=0;
    private static MultipleActivity parent;
    ProgressBar progress;
    Cursor cursor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple);
        mydb = new DatabaseHelper(this);
//        Intent intent = getIntent();
//        String id=delete.getmText12();
        progress = findViewById(R.id.progress);
        edttxt = findViewById(R.id.enterrcd);
        txtmutiple = findViewById(R.id.multipletxt);
        btn = findViewById(R.id.sbt);
//        mydb.openToRead();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(MainActivity.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                    cursor = mydb.getAllData();
                    if (cursor == null || cursor.getCount() == 0) {
                        Toast.makeText(MultipleActivity.this, "No record available,insert atleast 1 record ", Toast.LENGTH_LONG).show();
                    } else {

                        new Asyn_Task().execute();

                    }

                }
            }
        });
    }

   public class Asyn_Task extends AsyncTask<Void, Void, Void> {
        private final ProgressDialog dialog = new ProgressDialog(MultipleActivity.this);

       @Override
       protected Void doInBackground(Void... voids) {

           if (cursor.moveToFirst()) {
               id = cursor.getString(cursor.getColumnIndex(mydb.COL_0));
               CustomerName = cursor.getString(cursor.getColumnIndex(mydb.COL_1));
               NickName = cursor.getString(cursor.getColumnIndex(mydb.COL_2));
               CustomerId = cursor.getString(cursor.getColumnIndex(mydb.COL_3));
               ItemId = cursor.getString(cursor.getColumnIndex(mydb.COL_4));
               ItemRange = cursor.getString(cursor.getColumnIndex(mydb.COL_5));
               ItemCost = cursor.getString(cursor.getColumnIndex(mydb.COL_6));
               ItemNumber = cursor.getString(cursor.getColumnIndex(mydb.COL_7));
               CustomerCity = cursor.getString(cursor.getColumnIndex(mydb.COL_8));
               State = cursor.getString(cursor.getColumnIndex(mydb.COL_9));
               PinCode = cursor.getString(cursor.getColumnIndex(mydb.COL_10));
               Date = cursor.getString(cursor.getColumnIndex(mydb.COL_11));
               MobileNumber = cursor.getString(cursor.getColumnIndex(mydb.COL_12));
               Emailid = cursor.getString(cursor.getColumnIndex(mydb.COL_13));
               Religion = cursor.getString(cursor.getColumnIndex(mydb.COL_14));
               Landmark = cursor.getString(cursor.getColumnIndex(mydb.COL_15));
               OrganisationName = cursor.getString(cursor.getColumnIndex(mydb.COL_16));
               OrganisationCity = cursor.getString(cursor.getColumnIndex(mydb.COL_17));
               OrganisationState = cursor.getString(cursor.getColumnIndex(mydb.COL_18));

               String value = edttxt.getText().toString();
               int finalValue = Integer.parseInt(value);
               progress.setMax(Integer.parseInt(value));
               for (int i=1;i<=finalValue;i++) {
                   progress.setProgress(i);
                   mydb.insertData(CustomerName, NickName, CustomerId, ItemId, ItemRange, ItemCost, ItemNumber, CustomerCity, State, PinCode,
                           Date, MobileNumber, Emailid, Religion, Landmark, OrganisationName, OrganisationCity, OrganisationState);
                   //txtmutiple.setText(""+i);
                   //Toast.makeText(MultipleActivity.this, ""+i, Toast.LENGTH_SHORT).show();
                   Log.d("txtmutiple",""+i);
                   counter=i;

               }

               mydb.close();


           }
           return null;
       }

       // can use UI thread here
        protected void onPreExecute() {

            this.dialog.setMessage("Loading...");
            this.dialog.setCancelable(false);
            this.dialog.show();
        }


        @Override
        protected void onPostExecute(Void result) {

            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            Intent intent = new Intent(MultipleActivity.this, DeleteActivity.class);
            intent.putExtra("total",""+counter);
            startActivity(intent);
            finish();
        }
    }
    private  Boolean validate(){
        Boolean result=false;
        if (edttxt.getText().toString().isEmpty())   {
            Toast.makeText(MultipleActivity.this,"Enter number of times",Toast.LENGTH_LONG).show();
        }
        else{
            result=true;
        }
        return result;
    }

}




