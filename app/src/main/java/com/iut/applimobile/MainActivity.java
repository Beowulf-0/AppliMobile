package com.iut.applimobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button submit_button = (Button) findViewById(R.id.submit_button);
        Button sign_in_button = (Button) findViewById(R.id.signin_button);
        EditText email_edit = (EditText) findViewById(R.id.editTextEmail);
        EditText password_edit = (EditText) findViewById(R.id.editTextPassword);



        submit_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = email_edit.getText().toString();
                String password = password_edit.getText().toString();
                if(!email.equals("") || !password.equals("")){
                    //setContentView(R.layout.activity_menu);
                    Intent intMenuAct = new Intent(MainActivity.this, MenuActivity.class);
                    Toast t = Toast.makeText(v.getContext(), "Connexion réussie", Toast.LENGTH_SHORT);
                    t.show();
                    startActivity(intMenuAct);
                }
                else{
                    Toast t = Toast.makeText(v.getContext(), "Champ(s) vide(s)", Toast.LENGTH_SHORT);
                    t.show();
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