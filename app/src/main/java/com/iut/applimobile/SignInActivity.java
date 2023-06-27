package com.iut.applimobile;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {


    private DatabaseReference myRef;
    private FirebaseAuth mFireAuth;

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

        FirebaseDatabase db = FirebaseDatabase.getInstance("https://applimob-6a6c4-default-rtdb.europe-west1.firebasedatabase.app");
        this.myRef = db.getReference();

        submit_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String u_text = user_text.getText().toString();
                String mail_text = email_text.getText().toString();
                String pwd_text = password_text.getText().toString();
                String coord_x = coord_x_text.getText().toString();
                String coord_y = coord_y_text.getText().toString();

                if(TextUtils.isEmpty(u_text)){
                    Toast.makeText(v.getContext(), "Entrez votre nom d'utilisateur", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(mail_text)){
                    Toast.makeText(v.getContext(), "Entrez votre email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(pwd_text)){
                    Toast.makeText(v.getContext(), "Entrez votre mot de passe", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(coord_x)){
                    Toast.makeText(v.getContext(), "Entrez votre position X", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(coord_y)){
                    Toast.makeText(v.getContext(), "Entrez votre position X", Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseReference dataChild = myRef.child("users");

                dataChild.addListenerForSingleValueEvent(new ValueEventListener() {
                    boolean email_existant = false;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for(DataSnapshot user_snap : snapshot.getChildren()){

                            User user = user_snap.getValue(User.class);

                            if(user != null && user.getEmail().equals(mail_text)){
                                email_existant = true;
                                break;
                            }

                        }
                        if(email_existant){
                            Toast.makeText(v.getContext(), "Un utilisateur avec ce mail existe déjà", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            writeUserData(u_text, mail_text, pwd_text, Double.parseDouble(coord_x), Double.parseDouble(coord_y));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("tag", "Erreur lors de la vérification de l'email", error.toException());
                    }
                });
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

    public void writeUserData(String uname, String mail, String pwd,
                                double coord_X, double coord_Y){

        DatabaseReference usersRef = myRef.child("users");
        String userId = "uti-" + uname;


        User user = new User(uname, mail, pwd, coord_X, coord_Y);

        usersRef.child(userId).setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignInActivity.this, "Utilisateur ajouté", Toast.LENGTH_SHORT).show();
                            Intent intAct = new Intent(SignInActivity.this, MainActivity.class);
                            startActivity(intAct);
                            finish();
                        }
                        else{
                            Toast.makeText(SignInActivity.this, "Erreur d'ajout de l'utilisateur", Toast.LENGTH_SHORT).show();
                            Log.e("tag", "Erreur lors de l'ajout de l'utilisateur : ", task.getException());
                        }
                    }
                });
    }
}
