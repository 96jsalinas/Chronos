package com.example.a96jsa.chronos;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        PieChart pieChart = (PieChart)findViewById(R.id.pie_chart);
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        ArrayList<String> categories = new ArrayList<>();
        categories = db.getCategories();


        List<PieEntry> entries = new ArrayList<>();

        for(String category : categories){
            Float totalTime = (float)db.getCategoryTotalTime(category);
            entries.add(new PieEntry(totalTime,category));
        }

//        entries.add(new PieEntry(20,"Mex"));
//        entries.add(new PieEntry(10,"Finland"));
//        entries.add(new PieEntry(5,"Canada"));
//        entries.add(new PieEntry(21,"Rusia"));

        Legend legend = pieChart.getLegend();
        legend.setTextSize(23);
        legend.setEnabled(false);
        pieChart.getDescription().setEnabled(false);


        PieDataSet set = new PieDataSet(entries,"");
        set.setColors(new int[]{Color.RED,Color.BLUE,Color.CYAN,Color.DKGRAY});
        PieData data = new PieData(set);
        pieChart.setData(data);
        pieChart.invalidate();
    }
}
