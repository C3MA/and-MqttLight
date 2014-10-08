package c3ma.mqttlight;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.*;

/**
 * Created by c3ma on 8/28/14.
 */
public class WorkerCallback implements MqttCallback {

    private WorkerThread workerThread;

    public WorkerCallback(WorkerThread workerThread) {
        this.workerThread = workerThread;
    }

    @Override
    public void connectionLost(Throwable cause) {
        //We should reconnect here
    }

    @Override
    public void messageArrived(MqttTopic topic, MqttMessage message) throws Exception {
        Log.i("c3ma.Message", topic.getName() +" - "+ message.toString());
        // Example for topic name: /room/light/8/state
        // Example for message: on
        try {
            int i = Integer.parseInt(topic.getName().split("/")[3]);
            this.workerThread.updateLamp(i, message.toString() == "on");
        } catch (Exception ex) {
            // on problems with the array index, a text that is no number
            Log.e("c3ma", "Cannot extract lamp number (" + ex.getMessage());
        }
    }

    @Override
    public void deliveryComplete(MqttDeliveryToken token) {
        Log.i("c3ma", "#MSG_PUB");
        //We do not need this because we do not publish
    }

}
