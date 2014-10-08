package c3ma.mqttlight;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

public class MainView extends Activity {

    WorkerThread wt;

    public void light(View v)
    {

        if (v instanceof Switch)
        {
            Switch s = (Switch) v;
            try {
                int index = Integer.parseInt(s.getHint().toString());

                Log.i("c3ma", "Light " +  index + " " + s.isChecked());
            } catch (NumberFormatException nfe) {
                Log.e("c3ma", "Could not parse " + nfe.getMessage());
            }
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        wt = new WorkerThread();
        wt.start();

        setContentView(R.layout.activity_main_view);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
