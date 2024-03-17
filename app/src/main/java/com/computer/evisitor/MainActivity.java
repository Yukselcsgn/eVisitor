package com.computer.evisitor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/*import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;*/

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Context mContent;

    private RecyclerView rv;

    private EditText editTextfullName;
    private EditText editTextyourComment;
    private Button kaydet;

    private ArrayList<Opinion> commentList;
    private RVAdapter adapter;
    private FirebaseDatabase database;
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("onCreate Aktif","Aktif");

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("eVisitor");
        toolbar.setSubtitle("Medeniyetler Müzesi");
        toolbar.setLogo(R.drawable.baseline_temple_buddhist_24);
        //toolbar.setTitleTextColor();
        setSupportActionBar(toolbar);

        editTextfullName = findViewById(R.id.editTextfullName);
        editTextyourComment = findViewById(R.id.editTextyourComment);
        kaydet = findViewById(R.id.kaydet);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("eVisitor");

        Log.e("Tanımlamalar Yapıldı","Aktif");

        rv=findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        commentList = new ArrayList<>();
        adapter = new RVAdapter(this, commentList);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        kaydet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String fullName = editTextfullName.getText().toString();
                String comment = editTextyourComment.getText().toString();
                Opinion opinion = new Opinion(fullName, comment);
                myRef.push().setValue(opinion);
            }
        });
        Log.e("Click Aktif","Aktif");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Opinion opinion = dataSnapshot.getValue(Opinion.class);
                commentList.add(opinion);
                adapter.notifyItemInserted(commentList.size() - 1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Opinion opinion = snapshot.getValue(Opinion.class);
                int index = commentList.indexOf(opinion);
                commentList.set(index, opinion);
                adapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Opinion opinion = snapshot.getValue(Opinion.class);
                int index = commentList.indexOf(opinion);
                commentList.remove(index);
                adapter.notifyItemRemoved(index);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        Log.e("Childlar aktif","Aktif");
        FirebaseRecyclerOptions<Opinion> options =
                new FirebaseRecyclerOptions.Builder<Opinion>()
                        .setQuery(myRef, Opinion.class)
                        .build();

        Log.e("FireRcyclerOptions Aktif","Aktif");

        FirebaseRecyclerAdapter<Opinion, RVAdapter.CardViewDesignObjectHolder> adapter =
                new FirebaseRecyclerAdapter<Opinion, RVAdapter.CardViewDesignObjectHolder>(options) {

                    @Override
                    public RVAdapter.CardViewDesignObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.card_design, parent, false);

                        return new RVAdapter.CardViewDesignObjectHolder(view);
                    }

                    @Override
                    protected void onBindViewHolder(RVAdapter.CardViewDesignObjectHolder holder, int position, Opinion model) {
                        holder.fullName.setText(model.getFullName());
                        holder.comment.setText(model.getComment());
                        //date
                    }
                };

        Log.e("FireRcyclerOptions Aktif","Aktif");
        adapter.startListening();


        super.onStop();
        adapter.stopListening();


    }
}