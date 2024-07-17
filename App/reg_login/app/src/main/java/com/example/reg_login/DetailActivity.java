package com.example.reg_login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private TextView tvDetailName, tvDetailLocation, tvDetailHours, tvDetailContact;
    private Button btnReserve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetailName = findViewById(R.id.tvDetailName);
        tvDetailLocation = findViewById(R.id.tvDetailLocation);
        tvDetailHours = findViewById(R.id.tvDetailHours);
        tvDetailContact = findViewById(R.id.tvDetailContact);
        btnReserve = findViewById(R.id.btnReserve);

        String name = getIntent().getStringExtra("uni_name");
        String location = getIntent().getStringExtra("location");
        String hours = getIntent().getStringExtra("hours");
        String contact = getIntent().getStringExtra("contact");
        String fieldId = getIntent().getStringExtra("field_id");

        tvDetailName.setText(name);
        tvDetailLocation.setText(location);
        tvDetailHours.setText(hours);
        tvDetailContact.setText(contact);

        btnReserve.setOnClickListener(v -> {
            Intent intent = new Intent(DetailActivity.this, ReserveActivity.class);
            intent.putExtra("field_id", fieldId);
            startActivity(intent);
        });
    }
}
