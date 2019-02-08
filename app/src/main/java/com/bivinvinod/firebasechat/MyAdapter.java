package com.bivinvinod.firebasechat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Datas> myDatasList=new ArrayList<>();

    public MyAdapter(List<Datas> myDatasList) {

        this.myDatasList = myDatasList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_datalisting,null);
        itemLayoutView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        MyViewHolder viewHolder=new MyViewHolder(itemLayoutView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {


        myViewHolder.txtName.setText(myDatasList.get(i).getDataName());
        myViewHolder.txtPhne.setText(myDatasList.get(i).getDataValue());

        myViewHolder.imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData(myDatasList.get(i).getDataId());

            }
        });



    }

    private void deleteData(String dataId) {
        //ActivityViewAll  activityViewAll=new ActivityViewAll();

        DatabaseReference myDatabaseReference=FirebaseDatabase.getInstance().getReference("Datas").child(dataId);
        myDatabaseReference.removeValue();
        //Toast.makeText(ActivityViewAll.class,"Deleted Successfully",Toast.LENGTH_SHORT).show();

        notifyDataSetChanged();
       // activityViewAll.callMethod();
    }

    @Override
    public int getItemCount() {
        return myDatasList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtPhne;

        ImageButton imageDelete;
        ImageButton imageEdit;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName=itemView.findViewById(R.id.txtName);
            txtPhne=itemView.findViewById(R.id.txtPhoneNum);
            imageDelete=itemView.findViewById(R.id.imageDelete);

        }
    }
}
