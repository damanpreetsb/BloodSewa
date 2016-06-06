package daman.com.logindemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

/**
 * Created by DELL on 02-Jun-16.
 */
public class WritePost extends Activity {
    protected EditText ContentText;
    protected EditText BGText;
    protected EditText EmailText;
    protected Button PostButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writepost_layout);
        Firebase.setAndroidContext(this);
        final Firebase ref = new Firebase("https://intense-heat-2851.firebaseio.com");
        ContentText = (EditText) findViewById(R.id.content_text);
        BGText = (EditText) findViewById(R.id.bgText);
        EmailText = (EditText) findViewById(R.id.EmailText);
        PostButton = (Button) findViewById(R.id.postbutton);
        PostButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String s1 =  ContentText.getText().toString();
                String s2 =  EmailText.getText().toString();
                String s = s2.concat("\n").concat(s1);

                new Firebase("https://intense-heat-2851.firebaseio.com/"+BGText.getText().toString() )
                        .push()
                        .child("text")
                        .setValue(s);
                ContentText.getText().clear();
                Intent intent = new Intent(WritePost.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }
}
