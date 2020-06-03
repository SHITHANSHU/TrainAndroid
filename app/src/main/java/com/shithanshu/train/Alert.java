package com.shithanshu.train;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Alert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        TextView textView=findViewById(R.id.textView2);
        String message=getIntent().getStringExtra("message");
        textView.setText(message);
    }
}
