package com.pierrdunn.firebaseexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText mChildValue;
    private TextView mChildValueTextView;
    private Button mAddButton, mRemoveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChildValue = (EditText) findViewById(R.id.childValueEditText);
        mChildValueTextView = (TextView) findViewById(R.id.childValueTextView);
        mAddButton = (Button) findViewById(R.id.addButton);
        mRemoveButton = (Button) findViewById(R.id.removeButton);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference mRef = database.getReference("hWorld");

        //ЗАПИСЬ В БАЗУ ДАННЫХ
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String childValue = mChildValue.getText().toString();
                mRef.setValue(childValue);

                Toast.makeText(MainActivity.this, childValue + " added!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef.removeValue();
                Toast.makeText(MainActivity.this, mRef.toString() + " removed succses!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //ЧТЕНИЕ ИЗ БАЗЫ
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String childValue = String.valueOf(dataSnapshot.getValue());
                mChildValueTextView.setText(childValue);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
