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

public class detailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvDetail = findViewById(R.id.tvDetail);
        Button btnBack = findViewById(R.id.btnBack);
        Intent intent = getIntent();
        double bmi = intent.getDoubleExtra("bmi",0);

        String message;
        if (bmi == 0){
            message = "BMI Categories & Advice:\n\n" +
                    "Underweight (BMI < 18.5):\n" +
                    "You are underweight. Consider a balanced diet and consult a healthcare provider.\n\n" +
                    "Normal weight (18.5 ≤ BMI < 25):\n" +
                    "You have a normal weight. Keep up the good work with a healthy lifestyle!\n\n" +
                    "Overweight (25 ≤ BMI < 30):\n" +
                    "You are overweight. Regular exercise and a healthy diet are recommended.\n\n" +
                    "Obese (BMI ≥ 30):\n" +
                    "You are obese. It's advisable to consult a healthcare provider for guidance.";
        }
        else if (bmi < 18.5 && bmi > 0) {
            message = "You are underweight. Consider a balanced diet and consult a healthcare provider.";
        } else if (bmi < 25) {
            message = "You have a normal weight. Keep up the good work with a healthy lifestyle!";
        } else if (bmi < 30) {
            message = "You are overweight. Regular exercise and a healthy diet are recommended.";
        } else {
            message = "You are obese. It's advisable to consult a healthcare provider for guidance.";
        }

        if(bmi > 0){
        tvDetail.setText(String.format("Your BMI is %.1f\n\n%s", bmi, message));
        } else {
            tvDetail.setText( message);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(detailActivity.this,MainActivity.class);
                startActivity(back);
            }
        });


    }
}