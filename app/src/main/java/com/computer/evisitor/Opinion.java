package com.computer.evisitor;

import android.widget.EditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Date;

public class Opinion {

    private String opinion_key;
    private String fullName;
    private String comment;
    private Date date;

    public Opinion(EditText editTextfullName, EditText editTextyourComment){

    }

    public Opinion(String opinion_key, String fullName) {
        this.opinion_key = opinion_key;
        this.fullName = fullName;
    }

    public Opinion(String fullName, String comment, String opinion_key) {
        this.fullName = fullName;
        this.comment = comment;
        this.opinion_key = opinion_key;
    }


    public Opinion(String opinion_key, String fullName, String comment, Date date) {
        this.opinion_key = opinion_key;
        this.fullName = fullName;
        this.comment = comment;
        this.date = date;
    }

    public String getOpinion_key() {
        return opinion_key;
    }

    public void setOpinion_key(String opinion_key) {
        this.opinion_key = opinion_key;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
