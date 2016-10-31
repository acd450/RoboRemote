package com.example.acd45.roboremotev2;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RemoteControl extends AppCompatActivity {

    private final static int REQUEST_ENABLE_BT = 1;
    BluetoothSocket bs = null;
    ConnectedThread threadBT = new ConnectedThread(bs);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_control);
        final Button buttonBT = (Button) findViewById(R.id.button_c);   //BlueTooth button
        final Button buttonF = (Button) findViewById(R.id.button2);     //Forward button
        final Button buttonB = (Button) findViewById(R.id.button1);     //Backward button
        final Button buttonL = (Button) findViewById(R.id.button4);     //Left button
        final Button buttonR = (Button) findViewById(R.id.button);      //Right button
        final SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar2);

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
        }

        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        buttonBT.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               if(progress < 34){
                   //threadBT.
               }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

}
