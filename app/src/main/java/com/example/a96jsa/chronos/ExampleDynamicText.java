package com.example.a96jsa.chronos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExampleDynamicText extends AppCompatActivity {

    private Button addElement;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_dynamic_text);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_example);
        addElement = findViewById(R.id.addElement);
        addElement.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView textView1 = new TextView(ExampleDynamicText.this);
                textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                textView1.setText("programmatically created TextView "+index);
                index++;
                textView1.setBackgroundColor(0xff66ff66); // hex color 0xAARRGGBB
                textView1.setPadding(20, 20, 20, 20);// in pixels (left, top, right, bottom)
                linearLayout.addView(textView1);
            }
        });
    }
}
