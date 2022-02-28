package com.example.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "FacebookLogin";

    private FirebaseAuth mAuth;
    private FirebaseUser mUsers;

    private CallbackManager mCallBackManager;

    private GoogleSignInClient mGoogleSignInClient;
    private final int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).hide();

        CardView sgnup = findViewById(R.id.sgnup);

        EditText username = findViewById(R.id.usernameLogin), password = findViewById(R.id.passwordLogin);
        CardView login = findViewById(R.id.loginButton);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        login.setOnClickListener(v-> {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child("account").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    String userInput = username.getText().toString();
                    String passInput = password.getText().toString();

                    if (userInput.equals("") || passInput.equals("")){
                        Toast.makeText(MainActivity.this, "Enter Valid Username and Password", Toast.LENGTH_SHORT).show();
                    }else {
                        if (snapshot.child("rgst").child(userInput).exists()){
                            if (snapshot.child("rgst").child(userInput).child("password").getValue(String.class).equals(passInput)){
                                startActivity(new Intent(MainActivity.this, category_panel.class));
                            }else{
                                Toast.makeText(MainActivity.this,"INCORRECT PASSWORD", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this,"ACCOUNT NOT REGISTERED", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });

        });


        sgnup.setOnClickListener(v-> {
            startActivity(new Intent(MainActivity.this, signup.class));
        });



        mAuth = FirebaseAuth.getInstance();
        mUsers = mAuth.getCurrentUser();

        mCallBackManager = CallbackManager.Factory.create();
        LoginButton loginButton = findViewById(R.id.facebook_sign_in);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallBackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess"+loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() { Log.d(TAG, "facebook:onCanel");   }

            @Override
            public void onError(@NotNull FacebookException e) { Log.d(TAG, "facebook:onError"); }
        });

        CardView signInButton = findViewById(R.id.google_sign_in);
        signInButton.setOnClickListener(v -> {
//            signIn();
            startActivity(new Intent(MainActivity.this, google_login.class));
        });

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onStart(){
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        mCallBackManager.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Toast.makeText(MainActivity.this, "Signed In Successfully", Toast.LENGTH_SHORT).show();
                FirebaseGoogleAuth(account);
            }catch (ApiException e) {
                Toast.makeText(MainActivity.this, "Signed In Unsuccessful", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void FirebaseGoogleAuth(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), account.getEmail());
        mAuth.signInWithCredential(credential).addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken token){
        Log.d(TAG, "handleFacebookAccessToken"+token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d(TAG, "signInWithCredential:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.getException());
                    Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }

    private void updateUI(FirebaseUser user){
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            Intent intent = new Intent(MainActivity.this, category_panel.class);
            startActivity(intent);
        }
    }



}