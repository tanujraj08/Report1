package com.gsr.report1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DisplayData extends AppCompatActivity {
    private TextView mDisplay;
    private Button mEdit;
    private Button mSend;
    private FirebaseFirestore db;
    private static final String School_Percent_Report = "School Percent Report";
    private static final String Staff_Absent_Percentage = "Staff Absent Percentage";
    private static final String Staff_Leave_Percentage = "Staff Leave Percentage";
    private static final String Staff_Present_Percentage = "Staff Present Percentage";
    private static final String Staff_Reported_Percentage = "Staff Reported Percentage";
    private static final String Student_Absent_Percentage = "Student Absent Percentage";
    private static final String Student_Leave_Percentage = "Student Leave Percentage";
    private static final String Student_Present_Percentage = "Student Present Percentage";
    private static final String Student_Reported_Percentage = "Student Reported Percentage";
    private static final String TAG = "DisplayData";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        db = FirebaseFirestore.getInstance();

        mDisplay = findViewById(R.id.Display);
        mDisplay.setMovementMethod(new ScrollingMovementMethod());
        mEdit = findViewById(R.id.Edit);
        mSend = findViewById(R.id.Send);

        ReadSingleContact();

        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DisplayData.this,EditData.class);
                startActivity(intent);

            }
        });



    }


    private void ReadSingleContact() {
        DocumentReference docRef = db.collection("Report 1").document("Data");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    if (doc.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + doc.getData());
                        StringBuilder fields = new StringBuilder("");
                        fields.append("Staff Absent Percentage ").append(doc.get("Staff Absent Percentage"));
                        fields.append("\nSchool Percent Report ").append(doc.get("School Percent Report"));
                        fields.append("\n Staff Leave Percentage ").append(doc.get("Staff Leave Percentage"));
                        fields.append("\n Staff Present Percentage ").append(doc.get("Staff Present Percentage"));
                        fields.append("\n Staff Reported Percentage ").append(doc.get("Staff Reported Percentage"));
                        fields.append("\n Student Absent Percentage ").append(doc.get("Student Absent Percentage"));
                        fields.append("\n Student Leave Percentage ").append(doc.get("Student Leave Percentage"));
                        fields.append("\n Student Present Percentage ").append(doc.get("Student Present Percentage"));
                        fields.append("\n Student Reported Percentage ").append(doc.get("Student Reported Percentage"));
                        mDisplay.setText(fields.toString());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        DocumentReference user = db.collection("Report1").document("Data");
//        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot doc = task.getResult();
//                    if (doc.exists()) {
//                        StringBuilder fields = new StringBuilder("");
//                        fields.append(" School Percent Report ").append(doc.get("School Percent Report"));
//                        fields.append("\n Staff Absent Percentage ").append(doc.get("Staff Absent Percentage"));
//                        fields.append("\n Staff Leave Percentage ").append(doc.get("Staff Leave Percentage"));
//                        fields.append("\n Staff Present Percentage ").append(doc.get("Staff Present Percentage"));
//                        fields.append("\n Staff Reported Percentage ").append(doc.get("Staff Reported Percentage"));
//                        fields.append("\n Student Absent Percentage ").append(doc.get("Student Absent Percentage"));
//                        fields.append("\n Student Leave Percentage ").append(doc.get("Student Leave Percentage"));
//                        fields.append("\n Student Present Percentage ").append(doc.get("Student Present Percentage"));
//                        fields.append("\n Student Reported Percentage ").append(doc.get("Student Reported Percentage"));
//                        mDisplay.setText(fields.toString());
//                    } else {
//                        Log.d(TAG, "No such document");
//                    }
//                }
//            }
//        })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                    }
//                });
//    }
}

