package com.example.songs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class signup extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://songs-d6b51-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final EditText usernameET = findViewById(R.id.usernameInput);
        final EditText passwordET = findViewById(R.id.passwordInput);

        Objects.requireNonNull(getSupportActionBar()).hide();

        Button signup = findViewById(R.id.sngup);

        signup.setOnClickListener(v->{
            final String unText = usernameET.getText().toString();
            final String pwText = passwordET.getText().toString();

            if (unText.isEmpty() || pwText.isEmpty()){
                Toast.makeText(signup.this, "Enter Username and Password", Toast.LENGTH_SHORT).show();
            }else {
                databaseReference.child("account").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        if (snapshot.child("rgst").child(unText).exists()){
                            Toast.makeText(signup.this, "Account Existing", Toast.LENGTH_SHORT).show();
                        }else{
                            databaseReference.child("account").child("rgst").child(unText).child("password").setValue(pwText);
                            Toast.makeText(signup.this,"Successfully Registered", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });
            }
        });
    }
}