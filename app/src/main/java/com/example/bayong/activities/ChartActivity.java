package com.example.bayong.activities;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.bayong.OhMySalesData;
import com.example.bayong.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChartActivity extends AppCompatActivity {

    BarChart barChart;
    ArrayList<BarEntry> barEntryArrayList;
    ArrayList<String> labelsNames;

    ArrayList<OhMySalesData> ohMySalesDataArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        barChart = findViewById(R.id.barChart);

        barEntryArrayList = new ArrayList<>();
        labelsNames = new ArrayList<>();
        okKayo();
        for (int i = 0; i < ohMySalesDataArrayList.size(); i++){
            String category = ohMySalesDataArrayList.get(i).getCategory();
            int sales = ohMySalesDataArrayList.get(i).getSales();
            barEntryArrayList.add(new BarEntry(i, sales));
            labelsNames.add(category);
        }

        BarDataSet barDataSet = new BarDataSet(barEntryArrayList, "Sales");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        Description description = new Description();
        description.setText("Category");
        barChart.setDescription(description);
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labelsNames));
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(labelsNames.size());
        xAxis.setLabelRotationAngle(270);
        barChart.animateY(2000);
        barChart.invalidate();
    }

    private void okKayo() {
                        ohMySalesDataArrayList.clear();
                        ohMySalesDataArrayList.add(new OhMySalesData("Beverages", 30));
                        ohMySalesDataArrayList.add(new OhMySalesData("Beauty and Personal Care", 24));
                        ohMySalesDataArrayList.add(new OhMySalesData("Baby Kids", 30));
                        ohMySalesDataArrayList.add(new OhMySalesData("Biscuits Snacks and Chocolates", 40));
                        ohMySalesDataArrayList.add(new OhMySalesData("Breakfast and Dairy", 50));
                        ohMySalesDataArrayList.add(new OhMySalesData("Cooking Needs", 70));
                        ohMySalesDataArrayList.add(new OhMySalesData("Frozen Foods", 34));
                        ohMySalesDataArrayList.add(new OhMySalesData("Fruits", 29));
                        ohMySalesDataArrayList.add(new OhMySalesData("Pet Needs", 11));
                        ohMySalesDataArrayList.add(new OhMySalesData("Pharmacy", 30));
                        ohMySalesDataArrayList.add(new OhMySalesData("Vegetables", 34));
                        ohMySalesDataArrayList.add(new OhMySalesData("Others", 30));
    }

}



