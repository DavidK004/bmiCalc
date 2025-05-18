package com.example.bmi_calc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import javax.xml.transform.Result;

public class resultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String gender = intent.getStringExtra("gender");
        int age = intent.getIntExtra("age", 0);
        double bmi = intent.getDoubleExtra("bmi",0);

        TextView tvResult = findViewById(R.id.tvResult);
        Button btnDetail = findViewById(R.id.btnDetail);

        String category;
        if (bmi < 18.5) {
            category = "Underweight";
        } else if (bmi < 25) {
            category = "Normal weight";
        } else if (bmi < 30) {
            category = "Overweight";
        } else {
            category = "Obese";
        }

        String resultText = "Name: " + name +
                "\nGender: " + gender +
                "\nAge: " + age +
                "\nBMI: " + String.format("%.1f", bmi) +
                "\nCategory: " + category;

        tvResult.setText(resultText);

    btnDetail.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent detail = new Intent(resultActivity.this,detailActivity.class);
            detail.putExtra("bmi",bmi);
            startActivity(detail);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    });

    }
}