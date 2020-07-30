package com.example.crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText id, cstname,nickname,customerid,itemid,itemrange,itemcost,itemnumber,customercity,state,pincode,date,
            mobilenumber,emaiid,religion,landmark,orgname,orgcity,orgstate;
    Button editbtn;
    private Delete delete;

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        myDb = new DatabaseHelper(this);
        id=findViewById(R.id.updateid);
        cstname=findViewById(R.id.updatecstedit);
        nickname=findViewById(R.id.updatenckedit);
        customerid=findViewById(R.id.updatecstidedit);
        itemid=findViewById(R.id.updateitemidedit);
        itemrange=findViewById(R.id.updaterangeedit);
        itemcost=findViewById(R.id.updateitemcostedit);
        itemnumber=findViewById(R.id.updateitemnumberedit);
        customercity=findViewById(R.id.updatecustomercityedit);
        state=findViewById(R.id.updatestateedit);
        pincode=findViewById(R.id.updatepincodedit);
        date=findViewById(R.id.updatedateedit);
        mobilenumber=findViewById(R.id.updatenumberedit);
        emaiid=findViewById(R.id.updateemailedit);
        religion=findViewById(R.id.updatereligionedit);
        landmark=findViewById(R.id.updatelandmarkedit);
        orgname=findViewById(R.id.updateorgedit);
        orgcity=findViewById(R.id.updateorgcityedit);
        orgstate=findViewById(R.id.updateorgstateedit);
        editbtn=findViewById(R.id.upbtn);
//        editdetails();
        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Updatedata(v);
            }
        });

        delete=(Delete)getIntent().getSerializableExtra("data");
        editdetails();
    }

    private void Updatedata(View v){
        boolean isupdated =myDb.updateData(id.getText().toString(),cstname.getText().toString(),
                nickname.getText().toString(),customerid.getText().toString(),itemid.getText().toString(),
                itemrange.getText().toString(),itemcost.getText().toString(),itemnumber.getText().toString(),
                customercity.getText().toString(),state.getText().toString(),pincode.getText().toString(),
                date.getText().toString(),mobilenumber.getText().toString(),emaiid.getText().toString(),
                religion.getText().toString(),landmark.getText().toString(),orgname.getText().toString(),
                orgcity.getText().toString(),orgstate.getText().toString());
        if (isupdated== true){
            Toast.makeText(UpdateActivity.this,"Data Updated",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(UpdateActivity.this,DeleteActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Toast.makeText(UpdateActivity.this,"Data Not Updated",Toast.LENGTH_LONG).show();
        }

    }

    private void editdetails(){
        id.setText(String.valueOf(delete.getmText0()));
        id.setEnabled(false);
        cstname.setText(delete.getmText1());
        nickname.setText(delete.getmText2());
        customerid.setText(delete.getmText3());
        itemid.setText(delete.getmText4());
        itemrange.setText(delete.getmText5());
        itemcost.setText(delete.getmText6());
        itemnumber.setText(delete.getmText7());
        customercity.setText(delete.getmText8());
        state.setText(delete.getmText9());
        pincode.setText(delete.getmText10());
        date.setText(delete.getmText11());
        mobilenumber.setText(delete.getmText12());
        emaiid.setText(delete.getmText13());
        religion.setText(delete.getmText14());
        landmark.setText(delete.getmText15());
        orgname.setText(delete.getmText16());
        orgcity.setText(delete.getmText17());
        orgstate.setText(delete.getmText18());
    }

    private void editdata(View v){
//        SQLiteDatabase sqLiteDatabase = myDb.getWritableDatabase();
//           String custname= cstname.getText().toString();
//        String nikname=nickname.getText().toString();
//        String custid=customerid.getText().toString();
//        String itmid=itemid.getText().toString();
//        String itmrange=itemrange.getText().toString();
//        String itmcst=itemcost.getText().toString();
//        String itnnum=itemnumber.getText().toString();
//        String cstcty=customercity.getText().toString();
//        String cststate=state.getText().toString();
//        String pin=pincode.getText().toString();
//        String dat=date.getText().toString();
//        String mblnum=mobilenumber.getText().toString();
//        String emailid=emaiid.getText().toString();
//        String rlg=religion.getText().toString();
//        String lndmark=landmark.getText().toString();
//        String org=orgname.getText().toString();
//        String orgcty=orgcity.getText().toString();
//        String orgstat=orgstate.getText().toString();
//
//        ContentValues values=new ContentValues();
//        values.put("custname",custname);
//        values.put("nikname",nikname);
//        values.put("custid",custid);
//        values.put("itmid",itmid);
//        values.put("itmrange",itmrange);
//        values.put("itmcst",itmcst);
//        values.put("itnnum",itnnum);
//        values.put("cstcty",cstcty);
//        values.put("cststate",cststate);
//        values.put("pin",pin);
//        values.put("dat",dat);
//        values.put("mblnum",mblnum);
//        values.put("emailid",emailid);
//        values.put("rlg",rlg);
//        values.put("lndmark",lndmark);
//        values.put("org",org);
//        values.put("orgcty",orgcty);
//        values.put("orgstat",orgstat);
//
//        sqLiteDatabase.update("Customer_table",values,"cstname"+cstname,null);
//        sqLiteDatabase.close();
//        finish();
//
//        Toast.makeText(UpdateActivity.this,"Update Data",Toast.LENGTH_LONG).show();
//
    }
}
