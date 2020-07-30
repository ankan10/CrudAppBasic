package com.example.crudapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.crudapp.DatabaseHelper.COL_0;
import static com.example.crudapp.DatabaseHelper.TABLE_NAME;

public class DeleteActivity extends AppCompatActivity implements DeleteInterface {

    DatabaseHelper myDb;
    RecyclerView recyclerView;
    ArrayList<Delete> deleteArrayList;
    DatabaseHelper db;
    Context context;
    private DeleteAdapter deleteAdapter;
    TextView total_text;
    Button delete_all_btn;
    LinearLayout delete_arrow_upward,delete_arrow_downward,date_delete_arrow_upward,date_delete_arrow_downward;
    Delete delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
//        myDb=new DatabaseHelper(this);

        recyclerView = findViewById(R.id.recycleview);
        total_text = findViewById(R.id.total_text);
        delete_arrow_upward=findViewById(R.id.delete_upward_layout);
        delete_arrow_downward=findViewById(R.id.delete_downward_layout);
        date_delete_arrow_upward=findViewById(R.id.date_upward_layout);
        date_delete_arrow_downward=findViewById(R.id.date_downward_layout);
        delete_all_btn=findViewById(R.id.delete_all_btn);
        total_text.setText("Total inserted data : "+getIntent().getStringExtra("total"));
        deleteArrayList = new ArrayList<>();
        db = new DatabaseHelper(this);

        deleteAdapter = new DeleteAdapter(this, deleteArrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(deleteAdapter);
        fetchdata();

//        delete_all_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


        delete_arrow_upward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(deleteArrayList,new PriceSort1());
                deleteAdapter.notifyDataSetChanged();

                delete_arrow_upward.setVisibility(View.INVISIBLE);
                delete_arrow_downward.setVisibility(View.VISIBLE);
//              String arrow=  arrow_upward.setImageResource(R.drawable.arrow_downward);
//                arrow_downward.setVisibility(View.VISIBLE);


            }
        });
        delete_arrow_downward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(deleteArrayList,new PriceSort());
                delete_arrow_upward.setVisibility(View.VISIBLE);
                delete_arrow_downward.setVisibility(View.INVISIBLE);
                deleteAdapter.notifyDataSetChanged();
            }
        });

        date_delete_arrow_upward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(deleteArrayList,new DateSort1());
                deleteAdapter.notifyDataSetChanged();

                date_delete_arrow_upward.setVisibility(View.INVISIBLE);
                date_delete_arrow_downward.setVisibility(View.VISIBLE);
//              String arrow=  arrow_upward.setImageResource(R.drawable.arrow_downward);
//                arrow_downward.setVisibility(View.VISIBLE);


            }
        });
        date_delete_arrow_downward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(deleteArrayList,new DateSort());
                date_delete_arrow_upward.setVisibility(View.VISIBLE);
                date_delete_arrow_downward.setVisibility(View.INVISIBLE);
                deleteAdapter.notifyDataSetChanged();
            }
        });

        delete_all_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDataAll();
                Toast.makeText(DeleteActivity.this,"Delete All",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void deleteDataAll(){
        db.deleteAll();
        deleteArrayList.clear();
        deleteAdapter.notifyDataSetChanged();
        Toast.makeText(DeleteActivity.this,"Delete All",Toast.LENGTH_SHORT).show();
    }



    public void fetchdata() {
        Delete delete = null;
        Cursor cursor = null;

        cursor = db.getAllData();
//        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
//        Delete delete = null;
//
//        Cursor cursor = sqLiteDatabase.rawQuery("select * from Customer_table ORDER BY "+ COL_0 +" DESC ", null);
        while (cursor.moveToNext()) {
            delete = new Delete();
            delete.setmText0(cursor.getString(0));
            delete.setmText1(cursor.getString(1));
            delete.setmText2(cursor.getString(2));
            delete.setmText3(cursor.getString(3));
            delete.setmText4(cursor.getString(4));
            delete.setmText5(cursor.getString(5));
            delete.setmText6(cursor.getString(6));
            delete.setmText7(cursor.getString(7));
            delete.setmText8(cursor.getString(8));
            delete.setmText9(cursor.getString(9));
            delete.setmText10(cursor.getString(10));
            delete.setmText11(cursor.getString(11));
            delete.setmText12(cursor.getString(12));
            delete.setmText13(cursor.getString(13));
            delete.setmText14(cursor.getString(14));
            delete.setmText15(cursor.getString(15));
            delete.setmText16(cursor.getString(16));
            delete.setmText17(cursor.getString(17));
            delete.setmText18(cursor.getString(18));

            deleteAdapter.getdeletedetail(delete);

        }
    }

    @Override
    public void openEditor(Delete delete) {
        Intent intent=new Intent(DeleteActivity.this,UpdateActivity.class);
        intent.putExtra("data",delete);

        startActivity(intent);
    }

    @Override
    public void openDelete(final Delete delete) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete Data");
        alert.setMessage("Confirm to delete data id:" + " " +delete.getmText0());
        alert.setCancelable(false);
        alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteData(delete);
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }

//    private void deleteData(Delete delete) {
//        DatabaseHelper mdb = new DatabaseHelper(this);
//        SQLiteDatabase sqLiteDatabase=mdb.getWritableDatabase();
//        String id=String.valueOf(delete.getmText1());
//        if(!id.isEmpty()){
//            sqLiteDatabase.delete(TABLE_NAME,"ID=?",new String[]{id});
//            Toast.makeText(DeleteActivity.this,"Data deleted",Toast.LENGTH_LONG).show();
//            deleteAdapter.idDelete(delete);
//            sqLiteDatabase.close();
//        }
//        else
//            Toast.makeText(DeleteActivity.this,"Data not deleted",Toast.LENGTH_LONG).show();
//
//    }
    private void deleteData(Delete delete){
        Integer deleteRows=db.deleteDataId(String.valueOf(delete.getmText0()));
//        Toast.makeText(DeleteActivity.this,"Data Value:"+deleteRows,Toast.LENGTH_LONG).show();
        if (deleteRows>0){
            Toast.makeText(DeleteActivity.this,"Data deleted",Toast.LENGTH_LONG).show();
            deleteAdapter.idDelete(delete);
        }
        else
            Toast.makeText(DeleteActivity.this,"Data not deleted",Toast.LENGTH_LONG).show();


    }
    private class PriceSort implements java.util.Comparator<Delete> {
        @Override
        public int compare(Delete t1, Delete t2) {

            return ((Integer) Integer.parseInt(t1.mText0)).compareTo((Integer) Integer.parseInt(t2.getmText0()));
        }
    }

    private class PriceSort1 implements java.util.Comparator<Delete> {
        @Override
        public int compare(Delete t1, Delete t2) {

            return ((Integer) Integer.parseInt(t2.mText0)).compareTo((Integer) Integer.parseInt(t1.getmText0()));
        }
    }


    private class DateSort implements java.util.Comparator<Delete> {
        @Override
        public int compare(Delete t1, Delete t2) {

            return t1.getmText11().compareTo(t2.getmText11());
        }
    }

    private class DateSort1 implements java.util.Comparator<Delete> {
        @Override
        public int compare(Delete t1, Delete t2) {

            return t2.getmText11().compareTo(t1.getmText11());
        }
    }

}
