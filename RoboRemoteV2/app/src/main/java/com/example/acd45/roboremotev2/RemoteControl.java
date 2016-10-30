package com.example.acd45.roboremotev2;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RemoteControl extends AppCompatActivity {

    private final static int REQUEST_ENABLE_BT = 1;
    private final BluetoothSocket mmSocket;
    //private final InputStream mmInStream;
    private final OutputStream mmOutStream;

    public RemoteControl(BluetoothSocket socket) {
        mmSocket = socket;
        OutputStream tmpOut = null;

        try {
            tmpOut = socket.getOutputStream();
        }
        catch (IOException e) {}

        mmOutStream = tmpOut;
    }

    public void write(byte[] bytes){
        try {
            mmOutStream.write(bytes);
        } catch(IOException e){}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_control);

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
        }

        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }
}
