package com.bivinvinod.firebasechat;

import android.app.ProgressDialog;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActivityViewAll extends AppCompatActivity {

    RelativeLayout Rlt;
    DatabaseReference myDatabaseReference;

    SwipeRefreshLayout swipeLayout;
    RecyclerView  mRecyclerView;
    MyAdapter mAdapter;
    ProgressDialog pd;
    EditText editTextSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextSearch=findViewById(R.id.edittextSearch);
        editTextSearch.setVisibility(View.GONE);

        swipeLayout = findViewById(R.id.swipe_container);
        myDatabaseReference=FirebaseDatabase.getInstance().getReference("Datas");


        Rlt=findViewById(R.id.myrelative);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        pd = new ProgressDialog(ActivityViewAll.this);

        callMethod();
       // pd.dismiss();


        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pd.show();
                callMethod();
               // editTextSearch.setVisibility(View.VISIBLE);
                //pd.dismiss();
                swipeLayout.setRefreshing(false);
            }
        });

        swipeLayout.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int Y =swipeLayout.getScrollY();
                Log.d("Y-values",Y+""+swipeLayout.getScrollX());
                if(Y>0){

                    editTextSearch.setVisibility(View.VISIBLE);
                }
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            swipeLayout.setOnScrollChangeListener(new SwipeRefreshLayout.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                    Log.d("Valuesss",i+"") ;
                }
            });
        }


    }

    public void callMethod() {
        //dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT)
        //pd = new ProgressDialog(this.getApplicationContext());
       // pd.setMessage("loading");
        //pd.show();

        myDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // collectAllDatas((Map<Datas,Object>) dataSnapshot.getValue());

                List<Datas> adsList = new ArrayList<Datas>();
                for (DataSnapshot adSnapshot: dataSnapshot.getChildren()) {
                    adsList.add(adSnapshot.getValue(Datas.class));
                }

                Log.d("FireBaseList","no of records of the search is "+adsList.size());


               // Log.d("FIREBASEDATA",adsList.get(1).getDataName());


                mAdapter = new MyAdapter(adsList);
                mRecyclerView.setAdapter(mAdapter);
                //pd.dismiss();
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
pd.dismiss();


            }
        });

    }


    private void collectAllDatas(Map<Datas,Object> value) {
        ArrayList<Datas> names=new ArrayList<>();

        for(Map.Entry<Datas,Object> entry:value.entrySet()){

            Map singleUser=(Map)entry.getValue();
            names.add((Datas) singleUser.get("dataName"));
        }

        Log.d("FirebaseDatas",names.get(1).getDataName().toString());

    }

}
