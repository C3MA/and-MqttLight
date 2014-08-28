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

    @Override
    public void connectionLost(Throwable cause) {
        //We should reconnect here
    }

    @Override
    public void messageArrived(MqttTopic topic, MqttMessage message) throws Exception {
        Log.i("c3ma.Message", topic.getName() +" - "+ message.toString());


    }

    @Override
    public void deliveryComplete(MqttDeliveryToken token) {
        Log.i("c3ma", "#MSG_PUB");
        //We do not need this because we do not publish
    }

}
