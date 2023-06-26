package com.iut.applimobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        EditText user_text = (EditText) findViewById(R.id.editTextUsername);
        EditText email_text = (EditText) findViewById(R.id.editTextEmailSignin2);
        EditText password_text = (EditText) findViewById(R.id.editTextPasswordSignin);
        EditText coord_x_text = (EditText) findViewById(R.id.editTextCoordX);
        EditText coord_y_text = (EditText) findViewById(R.id.editTextCoordY);

        Button submit_button = (Button) findViewById(R.id.submit);
        Button login_page_button = (Button) findViewById(R.id.login_page);

        submit_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String u_text = user_text.getText().toString();
                String mail_text = email_text.getText().toString();
                String pwd_text = password_text.getText().toString();
                String coord_x = coord_x_text.getText().toString();
                String coord_y = coord_y_text.getText().toString();

                if(u_text != "" && mail_text != "" && pwd_text != "" && coord_x != "" && coord_y != ""){
                    setContentView(R.layout.activity_main);
                }
                else{
                    Log.e("tag", "Veuillez renseigner tous les champs");
                }

            }
        });

        login_page_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intMainAct = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(intMainAct);
                finish();
            }
        });
    }

    /*public void addUserFirebase(String uname, String mail, String pwd,
                                double coord_X, double coord_Y){

    }*/
}
