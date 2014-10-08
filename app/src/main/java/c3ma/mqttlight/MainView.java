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
                if (s.isChecked())
                    wt.lichtAn(index);
                else
                    wt.lichtAus(index);
                Log.i("c3ma", "Light " +  index + " " + s.isChecked());
                wt.updateLamp(index, s.isChecked());
            } catch (NumberFormatException nfe) {
                Log.e("c3ma", "Could not parse " + nfe.getMessage());
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        wt = new WorkerThread(this);
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

    public void updateLamp(final int index, final boolean on)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Switch s = null;

                switch (index)
                {
                    case 1:
                        s = (Switch) findViewById(R.id.switch1);
                        break;
                    case 2:
                        s = (Switch) findViewById(R.id.switch2);
                        break;
                    case 3:
                        s = (Switch) findViewById(R.id.switch3);
                        break;
                    case 4:
                        s = (Switch) findViewById(R.id.switch4);
                        break;
                    case 5:
                        s = (Switch) findViewById(R.id.switch5);
                        break;
                    case 6:
                        s = (Switch) findViewById(R.id.switch6);
                        break;
                }

                if (s != null)
                {
                    Log.d("c3ma", "Lamp" + index + " updated to " + on + " on the GUI.");
                    s.setChecked(on);
                }
                else
                {
                    Log.e("c3ma", "Lamp" + index + " is not supported.");
                }
            }
        });
    }
}
