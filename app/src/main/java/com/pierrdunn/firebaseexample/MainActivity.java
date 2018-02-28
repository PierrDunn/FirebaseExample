package com.pierrdunn.firebaseexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText mChildValue;
    private Button mAddButton, mRemoveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChildValue = (EditText) findViewById(R.id.childValueEditText);
        mAddButton = (Button) findViewById(R.id.addButton);
        mRemoveButton = (Button) findViewById(R.id.removeButton);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference mRef = database.getReference("hWorld");

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
    }
}
