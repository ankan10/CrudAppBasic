package com.example.crudapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DeleteAdapter extends RecyclerView.Adapter<DeleteAdapter.deleteView> {

    private List<Delete>deleteList=new ArrayList<>();
    private Context context;
    private ArrayList<Delete>deleteArrayList;
    private DeleteInterface deleteInterface;

    public DeleteAdapter(DeleteInterface deleteInterface,ArrayList<Delete> deleteList) {
        this.deleteInterface=deleteInterface;
        this.deleteList = deleteList;
        this.deleteArrayList=deleteList;
    }

    @NonNull
    @Override
    public deleteView onCreateViewHolder( ViewGroup viewGroup, int i) {
    View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_layout,viewGroup,false);
    return new deleteView(view);
    }

    @Override
    public void onBindViewHolder(deleteView deleteView, int i) {
        Delete delete=deleteList.get(i);
        deleteView.id.setText(delete.getmText0());
        deleteView.cstname.setText(delete.getmText1());
        deleteView.nickname.setText(delete.getmText2());
        deleteView.customerid.setText(delete.getmText3());
        deleteView.itemid.setText(delete.getmText4());
        deleteView.itemrange.setText(delete.getmText5());
        deleteView.itemcost.setText(delete.getmText6());
        deleteView.itemnumber.setText(delete.getmText7());
        deleteView.customercity.setText(delete.getmText8());
        deleteView.state.setText(delete.getmText9());
        deleteView.pincode.setText(delete.getmText10());
        deleteView.date.setText(delete.getmText11());
        deleteView.mobilenumber.setText(delete.getmText12());
        deleteView.emaiid.setText(delete.getmText13());
        deleteView.religion.setText(delete.getmText14());
        deleteView.landmark.setText(delete.getmText15());
        deleteView.orgname.setText(delete.getmText16());
        deleteView.orgcity.setText(delete.getmText17());
        deleteView.orgstate.setText(delete.getmText18());
        deleteView.delbtn.setOnClickListener(new eventtoDelete(delete));
        deleteView.editbtn.setOnClickListener(new eventtoEditor(delete));

    }

    @Override
    public int getItemCount() {
        return deleteList.size();
    }

    public void getdeletedetail(Delete delete){
        deleteList.add(delete);
        this.notifyDataSetChanged();

    }

    public void idDelete(Delete delete){
        deleteList.remove(delete);
        this.notifyDataSetChanged();
    }
    public void idDeleteAll(Delete delete){
        deleteList.remove(delete);
        this.notifyDataSetChanged();
    }
    class eventtoDelete implements View.OnClickListener{
        private Delete delete;
        public eventtoDelete(Delete delete) {
            this.delete=delete;
        }

        @Override
        public void onClick(View v) {
        deleteInterface.openDelete(delete);
        }
    }

    class eventtoEditor implements View.OnClickListener{
        private Delete delete;
        public eventtoEditor(Delete delete) {
            this.delete=delete;
        }

        @Override
        public void onClick(View v) {
        deleteInterface.openEditor(delete );
        }
    }

    public class deleteView extends RecyclerView.ViewHolder{

        TextView id, cstname,nickname,customerid,itemid,itemrange,itemcost,itemnumber,customercity,state,pincode,date,
                mobilenumber,emaiid,religion,landmark,orgname,orgcity,orgstate;

        Button delbtn,editbtn;

        public deleteView(@NonNull View itemView) {
            super(itemView);

            id=itemView.findViewById(R.id.idlaytxt);
            cstname=itemView.findViewById(R.id.cstlaytxt);
            nickname=itemView.findViewById(R.id.ncklaytxt);
            customerid=itemView.findViewById(R.id.cstidlaytxt);
            itemid=itemView.findViewById(R.id.itemidlaytxt);
            itemrange=itemView.findViewById(R.id.rangelaytxt);
            itemcost=itemView.findViewById(R.id.itemcostlaytxt);
            itemnumber=itemView.findViewById(R.id.itemnumberlaytxt);
            customercity=itemView.findViewById(R.id.customercitylaytxt);
            state=itemView.findViewById(R.id.statelaytxt);
            pincode=itemView.findViewById(R.id.pincodlaytxt);
            date=itemView.findViewById(R.id.datelaytxt);
            mobilenumber=itemView.findViewById(R.id.numberlaytxt);
            emaiid=itemView.findViewById(R.id.emaillaytxt);
            religion=itemView.findViewById(R.id.religionlaytxt);
            landmark=itemView.findViewById(R.id.landmarklaytxt);
            orgname=itemView.findViewById(R.id.orglaytxt);
            orgcity=itemView.findViewById(R.id.orgcitylaytxt);
            orgstate=itemView.findViewById(R.id.orgstatelaytxt);
            delbtn=itemView.findViewById(R.id.delbtn);
            editbtn=itemView.findViewById(R.id.update);
        }
    }
}
