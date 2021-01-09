package com.example.amoriproject.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amoriproject.R;

public class RVAdapterMyRev extends RecyclerView.Adapter<RVAdapterMyRev.RVviewHolder> {

    @NonNull
    @Override
    public RVviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RVviewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RVviewHolder extends RecyclerView.ViewHolder{

        TextView txt_productName, txt_productCategory,txt_reviewDet, txt_reviewDate, txt_username;
        Button deleteRev, editRev;

        public RVviewHolder(@NonNull View itemView) {
            super(itemView);

            txt_productName = itemView.findViewById(R.id.text_productName);
            txt_productCategory = itemView.findViewById(R.id.text_productCategory);
            txt_reviewDet = itemView.findViewById(R.id.text_reviewDet);
            txt_reviewDate = itemView.findViewById(R.id.text_reviewDate);
            txt_username = itemView.findViewById(R.id.text_username);
            deleteRev = itemView.findViewById(R.id.btn_deleteRev);
            editRev = itemView.findViewById(R.id.btn_editRev);

        }
    }
}
