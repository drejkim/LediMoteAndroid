package com.drejkim.ledimoteandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    private Socket mSocket;
    {
        try {
            // MODIFY THIS WITH THE APPROPRIATE URL
            // Note: For some reason, Edison's name does not resolve, and I had to use its IP address
            mSocket = IO.socket("http://123.456.7.890:8080");
        } catch(URISyntaxException e) { }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Switch redSwitch;
        redSwitch = (Switch) findViewById(R.id.switch_red);
        redSwitch.setChecked(false);
        redSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sendSwitchState("red", isChecked);
            }
        });

        Switch greenSwitch;
        greenSwitch = (Switch) findViewById(R.id.switch_green);
        greenSwitch.setChecked(false);
        greenSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sendSwitchState("green", isChecked);
            }
        });

        Switch blueSwitch;
        blueSwitch = (Switch) findViewById(R.id.switch_blue);
        blueSwitch.setChecked(false);
        blueSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sendSwitchState("blue", isChecked);
            }
        });

        Switch yellowSwitch;
        yellowSwitch = (Switch) findViewById(R.id.switch_yellow);
        yellowSwitch.setChecked(false);
        yellowSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sendSwitchState("yellow", isChecked);
            }
        });

        mSocket.connect();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mSocket.disconnect();
    }

    private void sendSwitchState(String color, boolean state) {
        JSONObject data = new JSONObject();
        try {
            data.put("state", state);
            mSocket.emit(color, data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
