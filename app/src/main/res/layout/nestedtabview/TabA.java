package layout.nestedtabview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by DELL on 01-Jun-16.
 */
public class TabA extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

/* Second Tab Content */
        TextView textView = new TextView(this);
        textView.setText("Second Tab");
        setContentView(textView);

    }
}
