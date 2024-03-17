package com.computer.evisitor;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.net.*;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.*;


public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CardViewDesignObjectHolder>{
    private Context mContext;
    private List<Opinion> incommingCommentsList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("eVisitor");
    private RVAdapter adapter;
    private ArrayList<Opinion> commentList;
    private Object date;

    public RVAdapter(Context mContext,List<Opinion> incommingCommentsList ) {
        this.mContext = mContext;
        this.incommingCommentsList = incommingCommentsList;

    }

    public RVAdapter(Object o) {
    }

    public static class CardViewDesignObjectHolder extends RecyclerView.ViewHolder{
        public TextView fullName;
        public TextView comment;
        public TextView date;
        public CardView card;

        public CardViewDesignObjectHolder(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.editTextfullName);
            comment = itemView.findViewById(R.id.comment);
            date = itemView.findViewById(R.id.date);
            card = itemView.findViewById(R.id.card);
        }
    }


    @NonNull
    @Override
    public CardViewDesignObjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_design,parent,false);

        return new CardViewDesignObjectHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewDesignObjectHolder holder, int position) {
        Opinion opinion = incommingCommentsList.get(position);
        holder.fullName.setText(opinion.getFullName());
        holder.comment.setText(opinion.getComment());
        holder.date.setText(opinion.getDate().toString());

        String dateString = opinion.getDate().toString();
        holder.date.setText(dateString);

        Object date1 = date;
        private String formatDate(Object date1) {

            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            Date date;
            try {
                date = inputFormat.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
                return;
            } catch (java.text.ParseException e) {
                throw new RuntimeException(e);
            }

            SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.US);
            outputFormat.format(date);
        }



    }


    @Override
    public int getItemCount() {
        return incommingCommentsList.size();
    }


}
