package com.example.bayong.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bayong.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SeeDetailedResultActivity extends AppCompatActivity {
    private ImageButton backBtn;
    private Button graphBtn;
    private TextView beveragesTv, beautyTv, babyKidsTv, biscuitsTv, breakfastTv, cookingTv, frozenTv, fruitsTv
            ,petTv, pharmacyTv, vegetableTv, othersTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_detailed_result);

        backBtn = findViewById(R.id.backBtn);
        beveragesTv = findViewById(R.id.beveragesTv);
        beautyTv = findViewById(R.id.beautyTv);
        babyKidsTv = findViewById(R.id.babyKidsTv);
        biscuitsTv = findViewById(R.id.biscuitsTv);
        breakfastTv = findViewById(R.id.breakfastTv);
        cookingTv = findViewById(R.id.cookingTv);
        frozenTv = findViewById(R.id.frozenTv);
        fruitsTv = findViewById(R.id.fruitsTv);
        petTv = findViewById(R.id.petTv);
        pharmacyTv = findViewById(R.id.pharmacyTv);
        vegetableTv = findViewById(R.id.vegetableTv);
        othersTv = findViewById(R.id.othersTv);
        graphBtn = findViewById(R.id.graphBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        graphBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SeeDetailedResultActivity.this, ChartActivity.class));
            }
        });

        seeResult();
        seeResultTwo();
        seeResultThree();
        seeResultFour();
        seeResultFive();
        seeResultSix();
        seeResultSeven();
        seeResultEight();
        seeResultNine();
        seeResultTen();
        seeResultEleven();
        seeResultTwelve();
    }

    private void seeResult() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child("Details").child("Beverages").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){

                        DataSnapshot dataSnapshot = task.getResult();
                        String itemCat = String.valueOf(dataSnapshot.child("itemCategory").getValue());
                        String itemCou = String.valueOf(dataSnapshot.child("itemCount").getValue());
                        String dateI = String.valueOf(dataSnapshot.child("date").getValue());
                        beveragesTv.setText("Category: "+itemCat+" = "+itemCou+ " Sales "+" ["+dateI +"]");
                    }else{
                        Toast.makeText(SeeDetailedResultActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(SeeDetailedResultActivity.this, "Failed to Load", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void seeResultTwo() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child("Details").child("Baby Kids").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        Toast.makeText(SeeDetailedResultActivity.this, "Success", Toast.LENGTH_SHORT).show();

                        DataSnapshot dataSnapshot = task.getResult();
                        String itemCat = String.valueOf(dataSnapshot.child("itemCategory").getValue());
                        String itemCou = String.valueOf(dataSnapshot.child("itemCount").getValue());
                        String dateI = String.valueOf(dataSnapshot.child("date").getValue());
                        babyKidsTv.setText("Category: "+itemCat+" = "+itemCou+ " Sales "+" ["+dateI +"]");
                    }else{
                        Toast.makeText(SeeDetailedResultActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(SeeDetailedResultActivity.this, "Failed to Load", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void seeResultThree() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child("Details").child("Beauty and Personal Care").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        Toast.makeText(SeeDetailedResultActivity.this, "Success", Toast.LENGTH_SHORT).show();

                        DataSnapshot dataSnapshot = task.getResult();
                        String itemCat = String.valueOf(dataSnapshot.child("itemCategory").getValue());
                        String itemCou = String.valueOf(dataSnapshot.child("itemCount").getValue());
                        String dateI = String.valueOf(dataSnapshot.child("date").getValue());
                        beautyTv.setText("Category: "+itemCat+" = "+itemCou+ " Sales "+" ["+dateI +"]");
                    }else{
                        Toast.makeText(SeeDetailedResultActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(SeeDetailedResultActivity.this, "Failed to Load", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void seeResultFour() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child("Details").child("Biscuits Snacks and Chocolates").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        Toast.makeText(SeeDetailedResultActivity.this, "Success", Toast.LENGTH_SHORT).show();

                        DataSnapshot dataSnapshot = task.getResult();
                        String itemCat = String.valueOf(dataSnapshot.child("itemCategory").getValue());
                        String itemCou = String.valueOf(dataSnapshot.child("itemCount").getValue());
                        String dateI = String.valueOf(dataSnapshot.child("date").getValue());
                        biscuitsTv.setText("Category: "+itemCat+" = "+itemCou+ " Sales "+" ["+dateI +"]");
                    }else{
                        Toast.makeText(SeeDetailedResultActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(SeeDetailedResultActivity.this, "Failed to Load", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void seeResultFive() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child("Details").child("Breakfast and Dairy").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        Toast.makeText(SeeDetailedResultActivity.this, "Success", Toast.LENGTH_SHORT).show();

                        DataSnapshot dataSnapshot = task.getResult();
                        String itemCat = String.valueOf(dataSnapshot.child("itemCategory").getValue());
                        String itemCou = String.valueOf(dataSnapshot.child("itemCount").getValue());
                        String dateI = String.valueOf(dataSnapshot.child("date").getValue());
                        breakfastTv.setText("Category: "+itemCat+" = "+itemCou+ " Sales "+" ["+dateI +"]");
                    }else{
                        Toast.makeText(SeeDetailedResultActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(SeeDetailedResultActivity.this, "Failed to Load", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void seeResultSix() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child("Details").child("Cooking Needs").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        Toast.makeText(SeeDetailedResultActivity.this, "Success", Toast.LENGTH_SHORT).show();

                        DataSnapshot dataSnapshot = task.getResult();
                        String itemCat = String.valueOf(dataSnapshot.child("itemCategory").getValue());
                        String itemCou = String.valueOf(dataSnapshot.child("itemCount").getValue());
                        String dateI = String.valueOf(dataSnapshot.child("date").getValue());
                        cookingTv.setText("Category: "+itemCat+" = "+itemCou+ " Sales "+" ["+dateI +"]");
                    }else{
                        Toast.makeText(SeeDetailedResultActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(SeeDetailedResultActivity.this, "Failed to Load", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void seeResultSeven() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child("Details").child("Frozen Foods").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        Toast.makeText(SeeDetailedResultActivity.this, "Success", Toast.LENGTH_SHORT).show();

                        DataSnapshot dataSnapshot = task.getResult();
                        String itemCat = String.valueOf(dataSnapshot.child("itemCategory").getValue());
                        String itemCou = String.valueOf(dataSnapshot.child("itemCount").getValue());
                        String dateI = String.valueOf(dataSnapshot.child("date").getValue());
                        frozenTv.setText("Category: "+itemCat+" = "+itemCou+ " Sales "+" ["+dateI +"]");
                    }else{
                        Toast.makeText(SeeDetailedResultActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(SeeDetailedResultActivity.this, "Failed to Load", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void seeResultEight() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child("Details").child("Fruits").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        Toast.makeText(SeeDetailedResultActivity.this, "Success", Toast.LENGTH_SHORT).show();

                        DataSnapshot dataSnapshot = task.getResult();
                        String itemCat = String.valueOf(dataSnapshot.child("itemCategory").getValue());
                        String itemCou = String.valueOf(dataSnapshot.child("itemCount").getValue());
                        String dateI = String.valueOf(dataSnapshot.child("date").getValue());
                        fruitsTv.setText("Category: "+itemCat+" = "+itemCou+ " Sales "+" ["+dateI +"]");
                    }else{
                        Toast.makeText(SeeDetailedResultActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(SeeDetailedResultActivity.this, "Failed to Load", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void seeResultNine() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child("Details").child("Pet Needs").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        Toast.makeText(SeeDetailedResultActivity.this, "Success", Toast.LENGTH_SHORT).show();

                        DataSnapshot dataSnapshot = task.getResult();
                        String itemCat = String.valueOf(dataSnapshot.child("itemCategory").getValue());
                        String itemCou = String.valueOf(dataSnapshot.child("itemCount").getValue());
                        String dateI = String.valueOf(dataSnapshot.child("date").getValue());
                        petTv.setText("Category: "+itemCat+" = "+itemCou+ " Sales "+" ["+dateI +"]");
                    }else{
                        Toast.makeText(SeeDetailedResultActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(SeeDetailedResultActivity.this, "Failed to Load", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void seeResultTen() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child("Details").child("Pharmacy").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        Toast.makeText(SeeDetailedResultActivity.this, "Success", Toast.LENGTH_SHORT).show();

                        DataSnapshot dataSnapshot = task.getResult();
                        String itemCat = String.valueOf(dataSnapshot.child("itemCategory").getValue());
                        String itemCou = String.valueOf(dataSnapshot.child("itemCount").getValue());
                        String dateI = String.valueOf(dataSnapshot.child("date").getValue());
                        pharmacyTv.setText("Category: "+itemCat+" = "+itemCou+ " Sales "+" ["+dateI +"]");
                    }else{
                        Toast.makeText(SeeDetailedResultActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(SeeDetailedResultActivity.this, "Failed to Load", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void seeResultEleven() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child("Details").child("Vegetables").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        Toast.makeText(SeeDetailedResultActivity.this, "Success", Toast.LENGTH_SHORT).show();

                        DataSnapshot dataSnapshot = task.getResult();
                        String itemCat = String.valueOf(dataSnapshot.child("itemCategory").getValue());
                        String itemCou = String.valueOf(dataSnapshot.child("itemCount").getValue());
                        String dateI = String.valueOf(dataSnapshot.child("date").getValue());
                        vegetableTv.setText("Category: "+itemCat+" = "+itemCou+ " Sales "+" ["+dateI +"]");
                    }else{
                        Toast.makeText(SeeDetailedResultActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(SeeDetailedResultActivity.this, "Failed to Load", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void seeResultTwelve() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child("Details").child("Others").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        Toast.makeText(SeeDetailedResultActivity.this, "Success", Toast.LENGTH_SHORT).show();

                        DataSnapshot dataSnapshot = task.getResult();
                        String itemCat = String.valueOf(dataSnapshot.child("itemCategory").getValue());
                        String itemCou = String.valueOf(dataSnapshot.child("itemCount").getValue());
                        String dateI = String.valueOf(dataSnapshot.child("date").getValue());
                        othersTv.setText("Category: "+itemCat+" = "+itemCou+ " Sales "+" ["+dateI +"]");
                    }else{
                        Toast.makeText(SeeDetailedResultActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(SeeDetailedResultActivity.this, "Failed to Load", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}