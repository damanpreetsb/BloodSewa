package daman.com.logindemo;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by DELL on 01-Jun-16.
 */
public class FragmentOne extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        firstTabSpec.setIndicator("A+").setContent(new Intent(this, TabA.class));
        secondTabSpec.setIndicator("A-").setContent(new Intent(this, TabAminus.class));

/** Add tabSpec to the TabHost to display. */
        tabHost.addTab(firstTabSpec);
        tabHost.addTab(secondTabSpec);

    }
}
