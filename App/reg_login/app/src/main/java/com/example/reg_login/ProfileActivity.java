package com.example.reg_login;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reg_login.models.Reservation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvUserEmail;
    private RecyclerView recyclerViewReservations;
    private ReservationAdapter reservationAdapter;
    private List<Reservation> reservationList = new ArrayList<>();
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvUserEmail = findViewById(R.id.tvUserEmail);
        recyclerViewReservations = findViewById(R.id.recyclerViewReservations);

        recyclerViewReservations.setLayoutManager(new LinearLayoutManager(this));
        reservationAdapter = new ReservationAdapter(reservationList);
        recyclerViewReservations.setAdapter(reservationAdapter);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            tvUserEmail.setText(user.getEmail());
            loadReservations(user.getUid());
        }
    }

    private void loadReservations(String userId) {
        db.collection("reservas")
                .whereEqualTo("userId", userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            reservationList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Reservation reservation = document.toObject(Reservation.class);
                                reservation.setId(document.getId());
                                reservationList.add(reservation);
                            }
                            reservationAdapter.notifyDataSetChanged();
                        } else {
                        }
                    }
                });
    }
}

