package com.example.bayong.activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.example.bayong.R;
import com.example.bayong.adapters.AdapterOrderedItem;
import com.example.bayong.models.ModelOrderedItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InputDataActivity extends AppCompatActivity {

    private ImageButton backBtn;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);
        firebaseAuth = FirebaseAuth.getInstance();
        loadData();

        List<DataEntry> data = new ArrayList<>();

        //back button
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void loadData(){
        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        CardView cardView = findViewById(R.id.cardView);
        TextView itemNameTextView = cardView.findViewById(R.id.itemNameTextView);
        TextView itemQuantityTextView = cardView.findViewById(R.id.itemQuantityTextView);

        Cartesian cartesian = AnyChart.column();
        List<DataEntry> data = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");

        ref.child(firebaseAuth.getUid()).child("Orders")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Set<String> uniqueItemNames = new HashSet<>();
                        Map<String, Integer> itemQuantities = new HashMap<>();
                        for (DataSnapshot orderSnapshot : snapshot.getChildren()) {
                            for (DataSnapshot itemSnapshot : orderSnapshot.child("Items").getChildren()) {
                                String itemName = itemSnapshot.child("name").getValue(String.class);
                                String itemQuantityString = itemSnapshot.child("quantity").getValue(String.class);

                                int itemQuantity = Integer.parseInt(itemQuantityString);


                                // Check if the item name is already in the uniqueItemNames set
                                // If the item name is already in the itemQuantities map, add the quantity to the existing value, para d paulit-ulit
                                if (itemQuantities.containsKey(itemName)) {
                                    int existingQuantity = itemQuantities.get(itemName);
                                    itemQuantities.put(itemName, existingQuantity + itemQuantity);

                                } else {
                                    // If the item name is not already in the itemQuantities map, add it with the quantity as the value
                                    itemQuantities.put(itemName, itemQuantity);

                                }

                            }


                        }
                        // Add each item name and quantity pair to the data set
                        LinearLayout summaryLayout = cardView.findViewById(R.id.summaryLayout);
                        for (Map.Entry<String, Integer> entry : itemQuantities.entrySet()) {
                            String itemName = entry.getKey();
                            int itemQuantity = entry.getValue();


                            // Create a new LinearLayout to hold the item name and quantity TextViews
                            LinearLayout itemLayout = new LinearLayout(getApplicationContext());
                            itemLayout.setOrientation(LinearLayout.HORIZONTAL);
                            itemLayout.setLayoutParams(new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT
                            ));

                            // Create a TextView for the item name and add it to the itemLayout
                            TextView itemNameTextView = new TextView(getApplicationContext());
                            itemNameTextView.setLayoutParams(new LinearLayout.LayoutParams(
                                    0,
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    1.0f
                            ));
                            itemNameTextView.setText(itemName);
                            itemLayout.addView(itemNameTextView);

                            // Create a TextView for the item quantity and add it to the itemLayout
                            TextView itemQuantityTextView = new TextView(getApplicationContext());
                            itemQuantityTextView.setLayoutParams(new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT
                            ));
                            itemQuantityTextView.setText(String.valueOf(itemQuantity));
                            itemLayout.addView(itemQuantityTextView);

                            // Add the itemLayout to the summaryLayout in the cardView
                            summaryLayout.addView(itemLayout);

                            // Add the item data to the chart data
                            data.add(new ValueDataEntry(itemName, itemQuantity));

                        }

                        Column column = cartesian.column(data);

                        column.tooltip()
                                .titleFormat("{%X}")
                                .position(Position.CENTER_BOTTOM)
                                .anchor(Anchor.CENTER_BOTTOM)
                                .offsetX(0d)
                                .offsetY(5d)
                                .format("{%Value}");

                        cartesian.animation(true);
                        cartesian.title("Statistics of Ordered Products");

                        cartesian.yScale().minimum(0d);

                        cartesian.yAxis(0).labels().format("{%Value}");
                        cartesian.xAxis(0).labels().rotation(-90);
                        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
                        cartesian.interactivity().hoverMode(HoverMode.BY_X);

                        cartesian.xAxis(0).title("Product");
                        cartesian.yAxis(0).title("# of Items");


                        anyChartView.setChart(cartesian);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e(TAG, "onCancelled", error.toException());
                        Toast.makeText(InputDataActivity.this, "Failed to load data.", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}