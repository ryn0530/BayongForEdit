package com.example.bayong.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.bayong.R;
import com.example.bayong.databinding.ActivityUpdateBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UpdateActivity extends AppCompatActivity {

    ActivityUpdateBinding binding;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.seeResultTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateActivity.this, SeeResultActivity.class));
            }
        });
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.updateButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = binding.title.getText().toString();
                String itemCount = binding.itemCount.getText().toString();
                String date = binding.date.getText().toString();

                updateData(title, itemCount, date);
            }
        });
    }

    private void updateData(String title, String itemCount, String date) {

        HashMap mapHash = new HashMap();
        mapHash.put("title",title);
        mapHash.put("itemCount",itemCount);
        mapHash.put("date",date);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child(title).updateChildren(mapHash).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()){

                    binding.title.setText("");
                    binding.itemCount.setText("");
                    binding.date.setText("");
                    Toast.makeText(UpdateActivity.this, "Successfully Updated", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(UpdateActivity.this, "Failed to Update", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}