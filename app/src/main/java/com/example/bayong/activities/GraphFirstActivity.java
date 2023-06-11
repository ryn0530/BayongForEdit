
package com.example.bayong.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bayong.Details;
import com.example.bayong.R;
import com.example.bayong.databinding.ActivityGraphFirstBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class GraphFirstActivity extends AppCompatActivity {

    ActivityGraphFirstBinding binding;
    String title, itemCount, date;
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGraphFirstBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.updateButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GraphFirstActivity.this, UpdateActivity.class));
            }
        });
        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                title = binding.title.getText().toString();
                itemCount = binding.itemCount.getText().toString();
                date = binding.date.getText().toString();


                if (!title.isEmpty() && !itemCount.isEmpty() && !date.isEmpty()){

                    Details details = new Details(title, itemCount, date);
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Users");
                    reference.child(title).setValue(details).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            binding.title.setText("");
                            binding.itemCount.setText("");
                            binding.date.setText("");
                            Toast.makeText(GraphFirstActivity.this, "Successfully Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                }
            }
        });

    }
}