package com.emransac.emaapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import android.widget.Toast;


import com.emransac.emaapp.Adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnRegister; // Set Button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }
    public void mercapp(View view) {
        startActivity(new Intent(getApplicationContext(),MercAppActivity.class));
    }

    private void initUI() {
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
    }


    // Performs tasks after click on the elements
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRegister) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            EditText etFirstName = new EditText(this);
            EditText etLastName = new EditText(this);

            etFirstName.setHint("First Name");
            etLastName.setHint("Last Name");

            linearLayout.addView(etFirstName);
            linearLayout.addView(etLastName);


            alert.setView(linearLayout);

            alert.setPositiveButton("Ok", (dialog, whichButton) -> {
                if (!etFirstName.getText().toString().isEmpty() &&
                        !etLastName.getText().toString().isEmpty()
                       ) {
                    Toast.makeText(this, "You are taken to the next screen", Toast.LENGTH_LONG).show();
                }
            });

            alert.show();
        }
    }
}