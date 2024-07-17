package com.example.reg_login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class ReserveActivity extends AppCompatActivity {

    private EditText etDate, etTime;
    private Button btnReserve;
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);
        btnReserve = findViewById(R.id.btnReserve);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        String fieldId = getIntent().getStringExtra("field_id");

        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = etDate.getText().toString();
                String time = etTime.getText().toString();

                if (date.isEmpty() || time.isEmpty()) {
                    Toast.makeText(ReserveActivity.this, "Please enter date and time", Toast.LENGTH_SHORT).show();
                    return;
                }

                checkAvailabilityAndReserve(fieldId, date, time);
            }
        });
    }

    private void checkAvailabilityAndReserve(String fieldId, String date, String time) {
        db.collection("reservas")
                .whereEqualTo("fieldId", fieldId)
                .whereEqualTo("date", date)
                .whereEqualTo("time", time)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().isEmpty()) {
                                reserveField(fieldId, date, time);
                            } else {
                                Toast.makeText(ReserveActivity.this, "Field not available at this time", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ReserveActivity.this, "Error checking availability", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void reserveField(String fieldId, String date, String time) {
        String userId = auth.getCurrentUser().getUid();

        Map<String, Object> reservation = new HashMap<>();
        reservation.put("fieldId", fieldId);
        reservation.put("userId", userId);
        reservation.put("date", date);
        reservation.put("time", time);

        db.collection("reservas")
                .add(reservation)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(ReserveActivity.this, "Field reserved successfully", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(ReserveActivity.this, "Error reserving field", Toast.LENGTH_SHORT).show();
                });
    }
}
