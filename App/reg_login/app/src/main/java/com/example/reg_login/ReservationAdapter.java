package com.example.reg_login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reg_login.models.Reservation;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder> {

    private List<Reservation> reservationList;

    public ReservationAdapter(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    @NonNull
    @Override
    public ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reservation, parent, false);
        return new ReservationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationViewHolder holder, int position) {
        Reservation reservation = reservationList.get(position);
        holder.tvFieldId.setText(reservation.getFieldId());
        holder.tvDate.setText(reservation.getDate());
        holder.tvTime.setText(reservation.getTime());

        holder.btnDelete.setOnClickListener(v -> {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("reservas")
                    .document(reservation.getId())
                    .delete()
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(v.getContext(), "Reservation deleted", Toast.LENGTH_SHORT).show();
                        reservationList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, reservationList.size());
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(v.getContext(), "Error deleting reservation", Toast.LENGTH_SHORT).show();
                    });
        });
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    public static class ReservationViewHolder extends RecyclerView.ViewHolder {
        TextView tvFieldId, tvDate, tvTime;
        Button btnDelete;

        public ReservationViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFieldId = itemView.findViewById(R.id.tvFieldId);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
