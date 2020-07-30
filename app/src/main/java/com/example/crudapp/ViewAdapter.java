package com.example.crudapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewClass>{
    private Context context;
    private ArrayList<Delete> viewArrayList;
    public ViewAdapter( ArrayList<Delete> viewArrayList) {
    this.viewArrayList=viewArrayList;
    }

    @NonNull
    @Override
    public ViewClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_layout,viewGroup,false);
        return  new ViewClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewClass viewClass, int i) {
        Delete delete=viewArrayList.get(i);
        viewClass.id.setText(delete.getmText0());
        viewClass.cstname.setText(delete.getmText1());
        viewClass.nickname.setText(delete.getmText2());
        viewClass.customerid.setText(delete.getmText3());
        viewClass.itemid.setText(delete.getmText4());
        viewClass.itemrange.setText(delete.getmText5());
        viewClass.itemcost.setText(delete.getmText6());
        viewClass.itemnumber.setText(delete.getmText7());
        viewClass.customercity.setText(delete.getmText8());
        viewClass.state.setText(delete.getmText9());
        viewClass.pincode.setText(delete.getmText10());
        viewClass.date.setText(delete.getmText11());
        viewClass.mobilenumber.setText(delete.getmText12());
        viewClass.emaiid.setText(delete.getmText13());
        viewClass.religion.setText(delete.getmText14());
        viewClass.landmark.setText(delete.getmText15());
        viewClass.orgname.setText(delete.getmText16());
        viewClass.orgcity.setText(delete.getmText17());
        viewClass.orgstate.setText(delete.getmText18());

    }

    @Override
    public int getItemCount() {
        return viewArrayList.size();
    }

    public void getdeletedetail(Delete delete){
        viewArrayList.add(delete);
        this.notifyDataSetChanged();

    }

    public class ViewClass extends RecyclerView.ViewHolder {
        TextView id, cstname,nickname,customerid,itemid,itemrange,itemcost,itemnumber,customercity,state,pincode,date,
                mobilenumber,emaiid,religion,landmark,orgname,orgcity,orgstate;
        public ViewClass(@NonNull View itemView) {
            super(itemView);

            id=itemView.findViewById(R.id.viewidlaytxt);
            cstname=itemView.findViewById(R.id.viewcstlaytxt);
            nickname=itemView.findViewById(R.id.viewncklaytxt);
            customerid=itemView.findViewById(R.id.viewcstidlaytxt);
            itemid=itemView.findViewById(R.id.viewitemidlaytxt);
            itemrange=itemView.findViewById(R.id.viewrangelaytxt);
            itemcost=itemView.findViewById(R.id.viewitemcostlaytxt);
            itemnumber=itemView.findViewById(R.id.viewitemnumberlaytxt);
            customercity=itemView.findViewById(R.id.viewcustomercitylaytxt);
            state=itemView.findViewById(R.id.viewstatelaytxt);
            pincode=itemView.findViewById(R.id.viewpincodlaytxt);
            date=itemView.findViewById(R.id.viewdatelaytxt);
            mobilenumber=itemView.findViewById(R.id.viewnumberlaytxt);
            emaiid=itemView.findViewById(R.id.viewemaillaytxt);
            religion=itemView.findViewById(R.id.viewreligionlaytxt);
            landmark=itemView.findViewById(R.id.viewlandmarklaytxt);
            orgname=itemView.findViewById(R.id.vieworglaytxt);
            orgcity=itemView.findViewById(R.id.vieworgcitylaytxt);
            orgstate=itemView.findViewById(R.id.vieworgstatelaytxt);
        }
    }

}
