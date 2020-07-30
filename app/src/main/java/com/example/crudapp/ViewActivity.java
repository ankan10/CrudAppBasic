package com.example.crudapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.crudapp.DatabaseHelper.COL_0;

public class ViewActivity extends AppCompatActivity {
    ViewAdapter viewAdapter;
    DatabaseHelper db;
    RecyclerView recyclerView;
    LinearLayout layout;
    LinearLayout arrow_upward,arrow_downward;
    LinearLayout date_arrow_upward,date_arrow_downward;
    ArrayList<Delete> viewArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        recyclerView=findViewById(R.id.Viewrecycleview);
//        layout=findViewById(R.id.idlaytxt);
        arrow_upward=findViewById(R.id.upward_layout);
        arrow_downward=findViewById(R.id.downward_layout);

        date_arrow_downward=findViewById(R.id.date_downward_layout);
        date_arrow_upward=findViewById(R.id.date_upward_layout);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        viewArrayList=new ArrayList<>();
        db=new DatabaseHelper(this);

        viewAdapter=new ViewAdapter(viewArrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(viewAdapter);
        fetchdata();

        arrow_upward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(viewArrayList,new PriceSort1());
                viewAdapter.notifyDataSetChanged();

                arrow_upward.setVisibility(View.INVISIBLE);
                arrow_downward.setVisibility(View.VISIBLE);
//              String arrow=  arrow_upward.setImageResource(R.drawable.arrow_downward);
//                arrow_downward.setVisibility(View.VISIBLE);


            }
        });
        arrow_downward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(viewArrayList,new PriceSort());
                arrow_upward.setVisibility(View.VISIBLE);
                arrow_downward.setVisibility(View.INVISIBLE);
                viewAdapter.notifyDataSetChanged();
            }
        });

        date_arrow_upward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(viewArrayList,new DateSort1());
                viewAdapter.notifyDataSetChanged();

                date_arrow_upward.setVisibility(View.INVISIBLE);
                date_arrow_downward.setVisibility(View.VISIBLE);
//              String arrow=  arrow_upward.setImageResource(R.drawable.arrow_downward);
//                arrow_downward.setVisibility(View.VISIBLE);


            }
        });
        date_arrow_downward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(viewArrayList,new DateSort());
                date_arrow_upward.setVisibility(View.VISIBLE);
                date_arrow_downward.setVisibility(View.INVISIBLE);
                viewAdapter.notifyDataSetChanged();
            }
        });
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

            viewAdapter.getdeletedetail(delete);

        }
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
