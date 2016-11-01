package com.example.acd45.roboremotev2;

import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by acd45 on 10/30/2016.
 */

public class ConnectedThread extends Thread{

    private final BluetoothSocket mmSocket;

    //private final InputStream mmInStream;
    private final OutputStream mmOutStream;

    public ConnectedThread(BluetoothSocket socket) {
        mmSocket = socket;
        OutputStream tmpOut = null;

        try {
            tmpOut = socket.getOutputStream();
        }
        catch (IOException e) {}

        mmOutStream = tmpOut;
    }

    public void write(byte bytes){
        try {
            mmOutStream.write(bytes);
        } catch(IOException e){}
    }

    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) {}
    }
}
