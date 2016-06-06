package daman.com.logindemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.authentication.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DELL on 30-May-16.
 */
public class Signup extends Activity{
    protected EditText passwordEditText;
    protected EditText emailEditText;
    protected EditText nameEditText;
    protected EditText areaEditText;
    protected EditText bgEditText;
    protected Button signUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);
        Firebase.setAndroidContext(this);

        passwordEditText = (EditText)findViewById(R.id.passwordField);
        emailEditText = (EditText)findViewById(R.id.emailField);
        nameEditText = (EditText)findViewById(R.id.nameText);
        areaEditText = (EditText)findViewById(R.id.areaText);
        bgEditText = (EditText)findViewById(R.id.bgText);
        signUpButton = (Button)findViewById(R.id.signupButton);

        final Firebase ref = new Firebase("https://intense-heat-2851.firebaseio.com");

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passwordEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String name = nameEditText.getText().toString();
                String area = areaEditText.getText().toString();
                String bloodgroup = bgEditText.getText().toString();

                password = password.trim();
                email = email.trim();
                name = name.trim();
                area = area.trim();
                bloodgroup = bloodgroup.trim();

                if (password.isEmpty() || email.isEmpty() || name.isEmpty() || area.isEmpty() || bloodgroup.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
                    builder.setMessage(R.string.signup_error_message)
                            .setTitle(R.string.signup_error_title)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    final Posts posts = new Posts();

                    //Adding values
                    posts.setName(name);
                    posts.setArea(area);
                    posts.setEmail(email);
                    posts.setBloodGroup(bloodgroup);

                    //Storing values to firebase
                    ref.child("SignUp").push().setValue(posts);

                    final String emailAddress = email;
                    final String passwordKey = password;

                    // signup
                    ref.createUser(emailAddress, passwordKey, new Firebase.ResultHandler() {
                        @Override
                        public void onSuccess() {
                            ref.authWithPassword(emailAddress, passwordKey, new Firebase.AuthResultHandler() {
                                @Override
                                public void onAuthenticated(AuthData authData) {
                                    // Authenticated successfully with payload authData

                                    Intent intent = new Intent(Signup.this, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }

                                @Override
                                public void onAuthenticationError(FirebaseError firebaseError) {
                                    // Authenticated failed with error firebaseError
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
                                    builder.setMessage(firebaseError.getMessage())
                                            .setTitle(R.string.login_error_title)
                                            .setPositiveButton(android.R.string.ok, null);
                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                }
                            });
                        }

                        @Override
                        public void onError(FirebaseError firebaseError) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
                            builder.setMessage(firebaseError.getMessage())
                                    .setTitle(R.string.signup_error_title)
                                    .setPositiveButton(android.R.string.ok, null);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });

                }
            }
        });
    }
}