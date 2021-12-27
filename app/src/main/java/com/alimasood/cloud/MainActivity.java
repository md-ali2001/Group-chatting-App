package com.alimasood.cloud;

//import android.support.v7.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button rc;
    DatabaseReference db;
    EditText key,value;
    TextView t;

     ListView listv;
    ArrayList<String> list=new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db= FirebaseDatabase.getInstance().getReference().child("p");
        key=findViewById(R.id.keytext);
        t=findViewById(R.id.ret);
        t.setMovementMethod(new ScrollingMovementMethod());

        value=findViewById(R.id.valuetext);


        rc=findViewById(R.id.recb);




        rc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //list.clear();
                       if(snapshot.exists())
                       {
                           t.setText(snapshot.getValue().toString());
                       }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });






    }












    public void send(View view)
    {
        String k=key.getText().toString();
        String v=value.getText().toString();
        Toast.makeText(MainActivity.this, "Details Saved Successfully.", Toast.LENGTH_SHORT).show();

        db.child(k).setValue(v);

    }

}