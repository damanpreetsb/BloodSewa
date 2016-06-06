package daman.com.logindemo;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

/**
 * Created by DELL on 13-May-16.
 */
public class Credits extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        ActionBar actionBar = getActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

}
