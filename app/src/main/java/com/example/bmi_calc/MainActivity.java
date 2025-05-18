package com.example.bmi_calc;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText etName = findViewById(R.id.etName);
        EditText etAge = findViewById(R.id.etAge);
        EditText etHeight = findViewById(R.id.etHeight);
        EditText etWeight = findViewById(R.id.etWeight);
        RadioGroup rgGender = findViewById(R.id.rgGender);
        Button btnSubmit = findViewById(R.id.btnSubmit);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                int age = Integer.parseInt(etAge.getText().toString());
                int height = Integer.parseInt(etHeight.getText().toString());
                int weight = Integer.parseInt(etWeight.getText().toString());
                String gender = "";
                int selectedGenderId = rgGender.getCheckedRadioButtonId();
                if (selectedGenderId == R.id.rbMale) {
                    gender = "male";
                } else if (selectedGenderId == R.id.rbFemale) {
                    gender = "female";
                }
                double heightM = height / 100.0;
                double bmi = 1.0 * weight / (heightM * heightM);

                Intent result = new Intent(MainActivity.this,resultActivity.class);
                result.putExtra("name",name);
                result.putExtra("age",age);
                result.putExtra("gender",gender);
                result.putExtra("bmi",bmi);
                startActivity(result);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.detailMenu) {
            Intent detail = new Intent(MainActivity.this,detailActivity.class);
            startActivity(detail);
            return true;
        } else if (id == R.id.exitMenu) {
            finishAffinity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}