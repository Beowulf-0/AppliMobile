package com.iut.applimobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference bdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button submit_button = (Button) findViewById(R.id.submit_button);
        Button sign_in_button = (Button) findViewById(R.id.signin_button);
        EditText email_edit = (EditText) findViewById(R.id.editTextEmail);
        EditText password_edit = (EditText) findViewById(R.id.editTextPassword);

        //this.bdd = FirebaseDatabase.getInstance().getReference("https://applimob-6a6c4-default-rtdb.europe-west1.firebasedatabase.app/");
        /*if(bdd == null){
            throw new NullPointerException("Référence à la base de données inexistante");
        }*/

        submit_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = email_edit.getText().toString();
                String password = password_edit.getText().toString();
                if(email != "" && password != ""){
                    setContentView(R.layout.activity_menu);
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

    public void print(View v){
        Log.d("tag", "click");
    }
}