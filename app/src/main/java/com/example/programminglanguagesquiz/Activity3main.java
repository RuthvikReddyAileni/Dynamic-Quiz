package com.example.programminglanguagesquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Activity3main extends AppCompatActivity {
    static Button button1 , button2 , button3 , check1;
    String userID;
    FirebaseAuth fAuth;
    static String c1 = null;
    static String c2 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity3main );

        check1 =  findViewById(R.id.check);
        check1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fAuth = FirebaseAuth.getInstance();
                userID = fAuth.getCurrentUser().getUid();

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference reff = db.collection("Mobile Quiz").document(userID);
                reff.get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {
                                    c1 = documentSnapshot.getString("StatusC1");
                                    c2 = documentSnapshot.getString("StatusC2");
                                    String b1 = "true";
                                    if(documentSnapshot.getString("StatusC1") != null && documentSnapshot.getString("StatusC2") != null){
                                        openActivity3main3();
                                        Toast.makeText(Activity3main.this, "Checked Successfully.", Toast.LENGTH_SHORT).show();
                                    } else if(documentSnapshot.getString("StatusC1")!= null){
                                        openActivity3main2();
                                        Toast.makeText(Activity3main.this, "Checked Successfully.", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(Activity3main.this, "Checked Successfully.", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(Activity3main.this, "Document does not exist.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });

            }
        });


        button1 =  findViewById(R.id.round1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });
    }
    public void openActivity3() {
        Intent intent =  new Intent (this , Activity3.class);
        startActivity(intent);
    }
    public void openActivity3main2() {
        Intent intent =  new Intent (this , Activity3main2.class);
        startActivity(intent);
    }
    public void openActivity3main3() {
        Intent intent =  new Intent (this , Activity3main3.class);
        startActivity(intent);
    }
}