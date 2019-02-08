package com.bivinvinod.firebasechat;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //FirebaseFirestore firebaseFirestore;

    DatabaseReference mDatabaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabaseReference=FirebaseDatabase.getInstance().getReference("Datas");
        //firebaseFirestore.getInstance();
        final EditText txtData=findViewById(R.id.editText);
        final EditText txtPhonenum=findViewById(R.id.editTextPhone);
        Button btnAdd=findViewById(R.id.button);
        Button btnViewAll=findViewById(R.id.buttonViewAll);

        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ActivityViewAll.class));
            }
        });



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txtName=txtData.getText().toString().trim();
                String txtPhone=txtPhonenum.getText().toString().trim();

                /*Map<String,String> mMap=new HashMap<>();
                mMap.put("details",Data);
                mMap.put("Value",Data+"123");
                firebaseFirestore.collection("details").add(mMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,"Faild",Toast.LENGTH_SHORT).show();

                    }
                });*/



                String Id=mDatabaseReference.push().getKey();
                Datas mDatas=new Datas(Id,txtName,txtPhone);
                mDatabaseReference.child(Id).setValue(mDatas);

                alertDialog();
                clearEditText();

                Log.d("UNIQUE ID",Id);




            }

            private void clearEditText() {
                txtData.setText("");
                txtPhonenum.setText("");
            }
        });

    }



    private void alertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Firebase Database");
        builder.setIcon(R.drawable.ic_launcher_background);
        builder.setMessage("Data Added Successfully")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
