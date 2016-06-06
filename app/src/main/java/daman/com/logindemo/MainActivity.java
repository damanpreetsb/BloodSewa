package daman.com.logindemo;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);


        final Firebase ref = new Firebase("https://intense-heat-2851.firebaseio.com");
        ref.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData != null) {
                    // user is logged in
                    authData = ref.getAuth();
                    if (authData != null) {
                        // user authenticated
                        /** TabHost will have Tabs */
                        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);

/** TabSpec used to create a new tab.
 * By using TabSpec only we can able to setContent to the tab.
 * By using TabSpec setIndicator() we can set name to tab. */

/** tid1 is firstTabSpec Id. Its used to access outside. */
                        TabHost.TabSpec firstTabSpec = tabHost.newTabSpec("tid1");
                        TabHost.TabSpec secondTabSpec = tabHost.newTabSpec("tid1");

/** TabSpec setIndicator() is used to set name for the tab. */
/** TabSpec setContent() is used to set content for a particular tab. */
                        firstTabSpec.setIndicator("A").setContent(new Intent(MainActivity.this, FragmentOne.class));
                        secondTabSpec.setIndicator("B").setContent(new Intent(MainActivity.this, FragmentTwo.class));

/** Add tabSpec to the TabHost to display. */
                        tabHost.addTab(firstTabSpec);
                        tabHost.addTab(secondTabSpec);



                    } else {
                        // no user authenticated
                        loadLoginView();
                    }
                } else {
                    // user is not logged in
                    Intent intent = new Intent(MainActivity.this,Login.class);
                    startActivity(intent);
                }
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Firebase ref = new Firebase("https://intense-heat-2851.firebaseio.com");
            ref.unauth();
            loadLoginView();
        }
        if(id == R.id.action_post){
            Intent intent = new Intent(this,WritePost.class);
            startActivity(intent);
        }
        if(id ==R.id.action_credits){
        Intent intent = new Intent(this,Credits.class);
        startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadLoginView() {
        Intent intent = new Intent(this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
