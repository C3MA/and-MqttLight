package c3ma.mqttlight;

import android.content.ContextWrapper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.internal.MemoryPersistence;

/**
 * Created by c3ma on 8/28/14.
 */
public class WorkerThread extends Thread {

    public static final String BROKER_URL = "tcp://10.23.42.31:1883";

    private MqttClient client;

    private MainView mainView = null;

    public WorkerThread(MainView mainView) {
        this.mainView = mainView;
    }

    public void run() {

        try {

            client = new MqttClient(BROKER_URL, MqttClient.generateClientId(), new MemoryPersistence());
            client.setCallback(new WorkerCallback(this));
            client.connect();

            client.subscribe("/room/light/+/state");


        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while(true) { }
    }

    public void lichtAn(int i) {
        try {
            final MqttTopic temperatureTopic = client.getTopic("/room/light/"+ i +"/command");
            final MqttMessage message = new MqttMessage("on".getBytes());
            temperatureTopic.publish(message);
        } catch (Exception e) {

        }

    }

    public void lichtAus(int i) {
        try {
            final MqttTopic temperatureTopic = client.getTopic("/room/light/"+ i +"/command");
            final MqttMessage message = new MqttMessage("off".getBytes());
            temperatureTopic.publish(message);
        } catch (Exception e) {

        }

    }

    public void updateLamp(int index, boolean on)
    {
        if (mainView != null)
            mainView.updateLamp(index, on);
    }
}
