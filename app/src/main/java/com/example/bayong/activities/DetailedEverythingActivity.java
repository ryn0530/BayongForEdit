package com.example.bayong.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.bayong.R;
import com.example.bayong.databinding.ActivityDetailedEverythingBinding;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DetailedEverythingActivity extends AppCompatActivity {

    private EditText categoryTitleEt, itemsEt, dateOrderedEt;
    private Button seeNowBtn, seeGraphBtn;
    private ImageButton backBtn;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_everything);

        categoryTitleEt = findViewById(R.id.categoryTitleEt);
        itemsEt = findViewById(R.id.itemsEt);
        dateOrderedEt = findViewById(R.id.dateOrderedEt);
        seeNowBtn = findViewById(R.id.seeNowBtn);
        backBtn = findViewById(R.id.backBtn);
        seeGraphBtn = findViewById(R.id.seeGraphBtn);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        seeGraphBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailedEverythingActivity.this, SeeDetailedResultActivity.class));
            }
        });
        seeNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputData();
            }

            private String itemCategory, itemCount, date;
            private void inputData() {
                itemCategory = categoryTitleEt.getText().toString().trim();
                itemCount = itemsEt.getText().toString().trim();
                date = dateOrderedEt.getText().toString().trim();



                if (itemCategory.isEmpty()){
                    Toast.makeText(DetailedEverythingActivity.this, "Category is Required!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (itemCount.isEmpty()){
                    Toast.makeText(DetailedEverythingActivity.this, "Items Ordered is Required!", Toast.LENGTH_SHORT).show();
                    return;
                }if (date.isEmpty()){
                    Toast.makeText(DetailedEverythingActivity.this, "Date is Required!", Toast.LENGTH_SHORT).show();
                    return;
                }
                addItem();
            }

            private void addItem() {
                progressDialog.setMessage("Adding Item...");
                progressDialog.show();

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("itemCategory" , ""+itemCategory);
                hashMap.put("itemCount" , ""+itemCount);
                hashMap.put("date" , ""+date);

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                reference.child("Details").child(itemCategory).setValue(hashMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                progressDialog.dismiss();
                                Toast.makeText(DetailedEverythingActivity.this, "Item Added", Toast.LENGTH_SHORT).show();
                                clearData();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(DetailedEverythingActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }

            private void clearData() {
                categoryTitleEt.setText("");
                itemsEt.setText("");
                dateOrderedEt.setText("");
            }
        });

    }
}