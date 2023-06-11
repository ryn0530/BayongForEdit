package com.example.bayong.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.bayong.R;
import com.example.bayong.databinding.ActivitySeeResultBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SeeResultActivity extends AppCompatActivity {

    ActivitySeeResultBinding binding;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySeeResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.seeGraphicalPresentation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SeeResultActivity.this, ChartActivity.class));
            }
        });

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.seeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = binding.titleTv.getText().toString();
                if (!title.isEmpty()){
                    readData(title);
                }else{
                    Toast.makeText(SeeResultActivity.this, "Please Enter Category", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void readData(String title) {
        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(title).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        Toast.makeText(SeeResultActivity.this, "Successfully Read", Toast.LENGTH_SHORT).show();

                        DataSnapshot dataSnapshot = task.getResult();
                        String title = String.valueOf(dataSnapshot.child("title").getValue());
                        String itemCount = String.valueOf(dataSnapshot.child("itemCount").getValue());
                        String date = String.valueOf(dataSnapshot.child("date").getValue());
                        binding.titleTitle.setText("Category: " + title);
                        binding.itemCount.setText("Item Count: " + itemCount);
                        binding.date.setText("Date:" + date);

                    }else{
                        Toast.makeText(SeeResultActivity.this, "Category Doesn't Exists", Toast.LENGTH_SHORT).show();
                    }


                }else{
                    Toast.makeText(SeeResultActivity.this, "Failed to Read", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}