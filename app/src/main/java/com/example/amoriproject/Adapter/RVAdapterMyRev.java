package com.example.amoriproject.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amoriproject.EditReview;
import com.example.amoriproject.R;
import com.example.amoriproject.utils.DBHelper;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class RVAdapterMyRev extends RecyclerView.Adapter<RVAdapterMyRev.RVviewHolder> {

    private Context context;
    private ArrayList product_name, product_category, review_detail, review_date, username;

    public RVAdapterMyRev(Context context, ArrayList product_name, ArrayList product_category,
                     ArrayList review_detail, ArrayList review_date, ArrayList username){
        this.context = context;
        this.product_name = product_name;
        this.product_category = product_category;
        this.review_date = review_date;
        this.review_detail = review_detail;
        this.username = username;
    }

    @NonNull
    @Override
    public RVviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_review_item_my, parent, false);

        return new RVAdapterMyRev.RVviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVviewHolder holder, int position) {
        holder.txt_productName.setText(String.valueOf(product_name.get(position)));
        holder.txt_productCategory.setText(String.valueOf(product_category.get(position)));
        holder.txt_reviewDet.setText(String.valueOf(review_detail.get(position)));
        holder.txt_reviewDate.setText(String.valueOf(review_date.get(position)));
        holder.txt_username.setText(String.valueOf(username.get(position)));

    }

    @Override
    public int getItemCount() {
        return product_name.size();
    }

    public class RVviewHolder extends RecyclerView.ViewHolder{

        TextView txt_productName, txt_productCategory,txt_reviewDet, txt_reviewDate, txt_username;
        Button deleteRev, editRev;

        DBHelper dbHelper = new DBHelper(context);

        String KEY_REVIEWID = "idRev";
        String SP_NAME = "mypref";
        SharedPreferences sp;

        public RVviewHolder(@NonNull final View itemView) {
            super(itemView);

            sp = itemView.getContext().getSharedPreferences(SP_NAME, MODE_PRIVATE);

            txt_productName = itemView.findViewById(R.id.text_productName);
            txt_productCategory = itemView.findViewById(R.id.text_productCategory);
            txt_reviewDet = itemView.findViewById(R.id.text_reviewDet);
            txt_reviewDate = itemView.findViewById(R.id.text_reviewDate);
            txt_username = itemView.findViewById(R.id.text_username);
            deleteRev = itemView.findViewById(R.id.btn_deleteRev);
            editRev = itemView.findViewById(R.id.btn_editRev);

            editRev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = dbHelper.getIdRev(txt_productName.toString(), txt_productCategory.toString(),
                            txt_reviewDet.toString(), txt_username.toString(),  txt_reviewDate.toString());
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(KEY_REVIEWID, String.valueOf(id));
                    editor.commit();
                    Intent intent = new Intent(itemView.getContext(), EditReview.class);
                    itemView.getContext().startActivity(intent);
                }
            });

            deleteRev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int id = dbHelper.getIdRev(txt_productName.toString(), txt_productCategory.toString(),
                            txt_reviewDet.toString(), txt_username.toString(),  txt_reviewDate.toString());
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    builder.setCancelable(false);
                    builder.setMessage("Are you sure to delete this review?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            boolean res = dbHelper.deleteReview(String.valueOf(id));

                            if (res){
                                Toast.makeText(itemView.getContext(), "This post has been successfully deleted", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });

        }


    }
}
