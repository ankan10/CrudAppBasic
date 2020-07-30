package com.example.crudapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    TextView AddData,ViewData,DeleteData,ImportData;
    SimpleDateFormat dateFormatter;
    private DatePickerDialog fromDatePickerDialog;
    Calendar newDate;

    EditText cstname,nickname,customerid,itemid,itemrange,itemcost,itemnumber,customercity,state,pincode,editdate,
            mobilenumber,emaiid,religion,landmark,orgname,orgcity,orgstate;

    Button submit,datebtn;
    String startdatestr = "", enddatestr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb=new DatabaseHelper(this);

        cstname=findViewById(R.id.cstedit);
        nickname=findViewById(R.id.nckedit);
        customerid=findViewById(R.id.cstidedit);
        itemid=findViewById(R.id.itemidedit);
        itemrange=findViewById(R.id.rangeedit);
        itemcost=findViewById(R.id.itemcostedit);
        itemnumber=findViewById(R.id.itemnumberedit);
        customercity=findViewById(R.id.customercityedit);
        state=findViewById(R.id.stateedit);
        pincode=findViewById(R.id.pincodedit);
        editdate=findViewById(R.id.edtdate);
        datebtn=findViewById(R.id.dateedit);
        mobilenumber=findViewById(R.id.numberedit);
        emaiid=findViewById(R.id.emailedit);
        religion=findViewById(R.id.religionedit);
        landmark=findViewById(R.id.landmarkedit);
        orgname=findViewById(R.id.orgedit);
        orgcity=findViewById(R.id.orgcityedit);
        orgstate=findViewById(R.id.orgstateedit);

        AddData=findViewById(R.id.addtxt);
        ViewData=findViewById(R.id.viewtxt);
        DeleteData=findViewById(R.id.deletetxt);
//        UpdateData=findViewById(R.id.updatetxt);
        ImportData=findViewById(R.id.importtxt);
        submit=findViewById(R.id.submitbtn);

        datebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);


                Calendar newCalendar = Calendar.getInstance();
                fromDatePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        startdatestr = dateFormatter.format(newDate.getTime());
                        editdate.setText(startdatestr);
                        makeText(MainActivity.this, "DAte Selected :" + startdatestr, LENGTH_SHORT).show();
                    }
                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                fromDatePickerDialog.show();
            }
        });

        ViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,ViewActivity.class);
                startActivity(i);
            }
        });

        ImportData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,MultipleActivity.class);
                startActivity(i);
            }
        });

        DeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DeleteActivity.class);
                startActivity(intent);
            }
        });

//        viewAll();
        addData();
    }
    public void addData(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {

                    boolean inserted = myDb.insertData(cstname.getText().toString(),
                            nickname.getText().toString(),
                            customerid.getText().toString(),
                            itemid.getText().toString(),
                            itemrange.getText().toString(),
                            itemcost.getText().toString(),
                            itemnumber.getText().toString(),
                            customercity.getText().toString(),
                            state.getText().toString(),
                            pincode.getText().toString(),
                            editdate.getText().toString(),
                            mobilenumber.getText().toString(),
                            emaiid.getText().toString(),
                            religion.getText().toString(),
                            landmark.getText().toString(),
                            orgname.getText().toString(),
                            orgcity.getText().toString(),
                            orgstate.getText().toString());
                    if (inserted = true) {
                        editempty();
                        Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void  editempty(){
                cstname.setText(null);
                nickname.setText(null);
                customerid.setText(null);
                itemid.setText(null);
                itemrange.setText(null);
                itemcost.setText(null);
                itemnumber.setText(null);
                customercity.setText(null);
                state.setText(null);
                pincode.setText(null);
                datebtn.setText(null);
                mobilenumber.setText(null);
                emaiid.setText(null);
                religion.setText(null);
                landmark.setText(null);
                orgname.setText(null);
                orgcity.setText(null);
                orgstate.setText(null);

    }

    public void viewAll() {
        ViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=myDb.getAllData();
                if (res.getCount()==0)
                {
                    showMessage("Error","No data found");
                    return;
                }
                else
                {
                    StringBuffer buffer=new StringBuffer();
                    while (res.moveToNext())
                    {
                        buffer.append("Id :"+res.getString(0)+"\n \n");
                        buffer.append("Customer Name :"+res.getString(1)+"\n \n");
                        buffer.append("Nick Name :"+res.getString(2)+"\n \n");
                        buffer.append("Customer Id :"+res.getString(3)+"\n \n");
                        buffer.append("Item Id :"+res.getString(4)+"\n \n");
                        buffer.append("Item range:"+res.getString(5)+"\n \n");
                        buffer.append("Item Cost:"+res.getString(6)+"\n \n");
                        buffer.append("Item number:"+res.getString(7)+"\n \n");
                        buffer.append("Customer City:"+res.getString(8)+"\n \n");
                        buffer.append("State:"+res.getString(9)+"\n \n");
                        buffer.append("Pincode:"+res.getString(10)+"\n \n");
                        buffer.append("Date:"+res.getString(11)+"\n \n");
                        buffer.append("Mobile Number:"+res.getString(12)+"\n \n");
                        buffer.append("Email id:"+res.getString(13)+"\n \n");
                        buffer.append("Religion:"+res.getString(14)+"\n \n");
                        buffer.append("Landmark:"+res.getString(15)+"\n \n");
                        buffer.append("Organisation name:"+res.getString(16)+"\n \n");
                        buffer.append("Organisation city:"+res.getString(17)+"\n \n");
                        buffer.append("Organisation state:"+res.getString(18)+"\n \n \n");

                    }

                    //Now show all Data
                    showMessage("Data",buffer.toString());


                }
            }
        });
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    private  Boolean validate(){
        Boolean result=false;


        if (cstname.getText().toString().isEmpty() || nickname.getText().toString().isEmpty() || customerid.getText().toString().isEmpty() ||
                itemid.getText().toString().isEmpty() || itemrange.getText().toString().isEmpty() || itemcost.getText().toString().isEmpty() ||
                itemnumber.getText().toString().isEmpty() || customercity.getText().toString().isEmpty() ||
                state.getText().toString().isEmpty() || pincode.getText().toString().isEmpty() || editdate.getText().toString().isEmpty() ||
                mobilenumber.getText().toString().isEmpty() || emaiid.getText().toString().isEmpty() || religion.getText().toString().isEmpty() ||
                landmark.getText().toString().isEmpty() || orgname.getText().toString().isEmpty() || orgcity.getText().toString().isEmpty() ||
                orgstate.getText().toString().isEmpty()){
            Toast.makeText(this,"Please enter all details",Toast.LENGTH_LONG).show();
        }
        else{
            result=true;
        }
        return result;
    }
}
