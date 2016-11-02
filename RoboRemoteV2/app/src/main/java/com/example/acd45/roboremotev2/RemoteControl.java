package com.example.acd45.roboremotev2;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.IOException;
import java.util.UUID;

import android.app.Activity;
import android.media.MediaPlayer;
import android.widget.Toast;

import static com.example.acd45.roboremotev2.R.*;



public class RemoteControl extends AppCompatActivity {

    private final static int REQUEST_ENABLE_BT = 1;
    final byte[] commands = new byte[] { (byte) 1, (byte) 1, (byte)2,
            (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9};
    int command = 0;

    MediaPlayer mp = null;
    private Context context;
    private ProgressDialog progress;
    String address = "98:76:B6:00:64:4A";
    BluetoothService BT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_remote_control);
        BT = new BluetoothService();


        final Button buttonBT = (Button) findViewById(id.button_c);   //BlueTooth button
        final Button buttonF = (Button) findViewById(id.button2);     //Forward button
        final Button buttonB = (Button) findViewById(id.button1);     //Backward button
        final Button buttonL = (Button) findViewById(id.button4);     //Left button
        final Button buttonR = (Button) findViewById(id.button);      //Right button
        final Button buttonAttack = (Button) findViewById(id.button3); //Attack 1
        final Button buttonAttack2 = (Button) findViewById(id.button5); //Attack 2
        final SeekBar seekBar = (SeekBar) findViewById(id.seekBar2);
        //final ConnectBT BT = new ConnectBT();


        //mediaplayer


//        if (myBluetooth == null) {
//            // Device does not support Bluetooth
//        }
//
//        if (!myBluetooth.isEnabled()) {
//            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
//        }

        buttonBT.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                BT.btConnect(address, new BluetoothService.BluetoothConnectCallback() {
                    @Override
                    public void doOnConnect() {

                    }

                    @Override
                    public void doOnConnectionFailed() {

                    }

                    @Override
                    public void doOnDisconnect() {

                    }
                });
//                try {
//                        ConnectBT();
////                    System.out.print("got the address ");
////                    BluetoothDevice dispositivo = mBluetoothAdapter.getRemoteDevice(address);
////                    System.out.print("hello ");
////                    btSocket[0] = dispositivo.createInsecureRfcommSocketToServiceRecord(UUID.randomUUID());
////                    System.out.print("there ");
////                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
////                    System.out.print("guy");
////                    btSocket[0].connect();
////                    System.out.print(" super");
//                }catch(IOException e){}
            }
        });

            buttonF.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                        if(event.getAction() == MotionEvent.ACTION_DOWN) {
                            command = 1;
                            //BT.sendMessage();
                            BT.writeData("1".getBytes());
                        }
                        else if (event.getAction() == MotionEvent.ACTION_UP){
                            command = 0;
                            //BT.sendMessage();
                            BT.writeData("0".getBytes());
                        }
                    return true;
                }
            });

        buttonB.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_DOWN) {
                        command = 2;
                        //BT.sendMessage();
                        BT.writeData("2".getBytes());
                    }
                    else if (event.getAction() == MotionEvent.ACTION_UP) {
                        command = 0;
                        //BT.sendMessage();
                        BT.writeData("0".getBytes());
                    }
                return true;
            }
        });

        buttonL.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_DOWN) {
                        command = 4;
                        //BT.sendMessage();
                        BT.writeData("4".getBytes());
                    }
                    else if (event.getAction() == MotionEvent.ACTION_UP) {
                        command = 0;
                        //BT.sendMessage();
                        BT.writeData("0".getBytes());
                    }
                return true;
            }
        });

        buttonR.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_DOWN) {
                        command = 3;
                        //BT.sendMessage();
                        BT.writeData("3".getBytes());
                    }
                    else if (event.getAction() == MotionEvent.ACTION_UP) {
                        command = 0;
                        //BT.sendMessage();
                        BT.writeData("0".getBytes());
                    }
                return true;
            }
        });


        buttonAttack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_DOWN) {
                        command = 5;
                        //BT.sendMessage();
                        BT.writeData("5".getBytes());
                        mp = MediaPlayer.create(context, R.raw.uav);
                        mp.start();
                    }
                    else if (event.getAction() == MotionEvent.ACTION_UP) {
                        command = 0;
                        //BT.sendMessage();
                        BT.writeData("0".getBytes());
                    }
                return true;
            }
        });

        buttonAttack2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_DOWN) {
                        command = 6;
                        //BT.sendMessage();
                        BT.writeData("6".getBytes());
                        mp = MediaPlayer.create(context, R.raw.tangodown);
                        mp.start();
                    }
                    else if (event.getAction() == MotionEvent.ACTION_UP) {
                        command = 0;
                        //BT.sendMessage();
                        BT.writeData("0".getBytes());
                    }
                return true;
            }
        });


            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (progress < 34) {
                            command = 7;
                            //BT.sendMessage();
                            BT.writeData("7".getBytes());
                        } else if (progress < 67 && progress > 33) {
                            command = 8;
                            //BT.sendMessage();
                            BT.writeData("8".getBytes());
                        } else {
                            command = 9;
                            //BT.sendMessage();
                            BT.writeData("9".getBytes());
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

    class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        final BluetoothAdapter myBluetooth = BluetoothAdapter.getDefaultAdapter();

        private boolean isBtConnected = false;
        BluetoothSocket btSocket = null;


        //System.out.print("hello ");
        private boolean ConnectSuccess = true; //if it's here, it's almost connected
        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try
            {
                if (btSocket == null || !isBtConnected)
                {
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(UUID.randomUUID());//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                }
            }
            catch (IOException e)
            {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);
            if (!ConnectSuccess)
            {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
                finish();
            }
            else
            {
                msg("Connected.");
                isBtConnected = true;
            }
            progress.dismiss();
        }

        private void msg(String s)
        {
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
        }

        private void Disconnect()
        {
            if (btSocket!=null) //If the btSocket is busy
            {
                try
                {
                    btSocket.close(); //close connection
                }
                catch (IOException e)
                { msg("Error");}
            }
            finish(); //return to the first layout
        }

        private void sendMessage()
        {
            if (btSocket!=null)
            {
                try
                {
                    btSocket.getOutputStream().write(commands[command]);
                }
                catch (IOException e)
                {
                    msg("Error");
                }
            }
        }

    }

}

