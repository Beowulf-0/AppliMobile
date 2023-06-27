package com.iut.applimobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button submit_button = findViewById(R.id.submit_button);
        Button sign_in_button = findViewById(R.id.signin_button);
        EditText email_edit = findViewById(R.id.editTextEmail);
        EditText password_edit = findViewById(R.id.editTextPassword);

        FirebaseDatabase db = FirebaseDatabase.getInstance("https://applimob-6a6c4-default-rtdb.europe-west1.firebasedatabase.app");

        this.dbRef = db.getReference();

        submit_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = email_edit.getText().toString();
                String password = password_edit.getText().toString();
                if(!email.equals("") || !password.equals("")){
                    //setContentView(R.layout.activity_menu);
                    /*Intent intMenuAct = new Intent(MainActivity.this, MenuActivity.class);
                    Toast t = Toast.makeText(v.getContext(), "Connexion réussie", Toast.LENGTH_SHORT);
                    startActivity(intMenuAct);*/
                    DatabaseReference dataChild = dbRef.child("users");

                    dataChild.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot snap_user : snapshot.getChildren()){
                                User user = snap_user.getValue(User.class);

                                assert user != null;
                                if(Objects.equals(user.getEmail(), email) && Objects.equals(user.getPassword(), password)){
                                    Toast.makeText(MainActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                                    Intent intMenuAct = new Intent(MainActivity.this, MenuActivity.class);
                                    startActivity(intMenuAct);
                                    finish();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else{
                    Toast.makeText(v.getContext(), "Champ(s) vide(s)", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sign_in_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intSignAct = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intSignAct);
                finish();
            }
        });
    }

}