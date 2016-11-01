package com.example.acd45.roboremotev2;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import android.app.Activity;
import android.media.MediaPlayer;

public class RemoteControl extends AppCompatActivity {

    private final static int REQUEST_ENABLE_BT = 1;
    final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    MediaPlayer mp = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_control);
        BluetoothSocket btSocket = null;
        final Button buttonBT = (Button) findViewById(R.id.button_c);   //BlueTooth button
        final Button buttonF = (Button) findViewById(R.id.button2);     //Forward button
        final Button buttonB = (Button) findViewById(R.id.button1);     //Backward button
        final Button buttonL = (Button) findViewById(R.id.button4);     //Left button
        final Button buttonR = (Button) findViewById(R.id.button);      //Right button
        final Button buttonAttack = (Button) findViewById(R.id.button3); //Attack 1
        final Button buttonAttack2 = (Button) findViewById(R.id.button5); //Attack 2
        final SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar2);
        final byte[] commands = new byte[] { (byte) 0, (byte) 1, (byte)2,
                (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9};

        //mediaplayer


        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
        }

        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        buttonBT.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String address = "98:76:B6:00:64:4A";
                BluetoothDevice dispositivo = mBluetoothAdapter.getRemoteDevice(address);
            }
        });



        buttonF.setOnClickListener(new Button.OnClickListener() {
                                       public void onClick(View v) {
                                           threadBT.write(commands[1]);
                                       }
        });

        buttonB.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                threadBT.write(commands[2]);
            }
        });

        buttonL.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                threadBT.write(commands[4]);
            }
        });

        buttonR.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                threadBT.write(commands[3]);
            }
        });

        buttonAttack.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                threadBT.write(commands[5]);
                mp = MediaPlayer.create(this, raw.uav);
                mp.start();
            }
        });

        buttonAttack2.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                threadBT.write(commands[6]);
                mp = MediaPlayer.create(this, raw.tangodown);
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               if(progress < 34){
                    threadBT.write(commands[7]);
               }
                else if(progress < 67 && progress > 33){
                    threadBT.write(commands[8]);
               }
                else{
                    threadBT.write(commands[9]);
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
