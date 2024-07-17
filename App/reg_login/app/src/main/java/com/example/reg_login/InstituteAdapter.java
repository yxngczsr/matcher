package com.example.reg_login;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reg_login.models.Institute;

import java.util.List;

public class InstituteAdapter extends RecyclerView.Adapter<InstituteAdapter.InstituteViewHolder> {

    private List<Institute> instituteList;

    public InstituteAdapter(List<Institute> instituteList) {
        this.instituteList = instituteList;
    }

    @NonNull
    @Override
    public InstituteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_institute, parent, false);
        return new InstituteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InstituteViewHolder holder, int position) {
        Institute institute = instituteList.get(position);
        holder.tvInstituteName.setText(institute.getUniversityName());
        holder.tvInstituteHours.setText(institute.getHours());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = holder.cardView.getContext();

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("uni_name", institute.getUniversityName());
                intent.putExtra("location", institute.getLocation());
                intent.putExtra("hours", institute.getHours());
                intent.putExtra("contact", institute.getContact());
                intent.putExtra("field_id", institute.getUniversityName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return instituteList.size();
    }

    public static class InstituteViewHolder extends RecyclerView.ViewHolder {
        TextView tvInstituteName;
        TextView tvInstituteHours;
        CardView cardView;

        public InstituteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvInstituteName = itemView.findViewById(R.id.tvInstituteName);
            tvInstituteHours = itemView.findViewById(R.id.tvInstituteHours);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
