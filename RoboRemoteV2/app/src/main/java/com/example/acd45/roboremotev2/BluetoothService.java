package com.example.acd45.roboremotev2;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BluetoothService  extends Service {

    // For logging
    final String TAG = "BluetoothService";

    // Bluetooth stuff
    private BluetoothAdapter btAdapater;
    private BluetoothSocket btSocket;
    public BluetoothDevice btDevice;
    public InputStream iStream;
    public OutputStream oStream;

    // An executor service to act as a FIFO job queue
    public ExecutorService commService;



    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Service created...");
        btAdapater = BluetoothAdapter.getDefaultAdapter();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"Service destroyed...");
        super.onDestroy();
    }

    /////////////////////// Stuff for interacting with the Bluetooth device
    /**
     * Connect to a Bluetooth device
     *
     * @param MAC The MAC address to connect to. The MAC address should be formatted as
     *            XX:XX:XX:XX:XX:XX (UPPER CASE, 2 digits, colon, 2 digits, colon, ...).
     *            <p/>
     * @return Returns true upon successful connection; false otherwise.
     */
    public boolean btConnect(String MAC, BluetoothConnectCallback myCallback) {
        // Just in case BT wasn't enabled when we started the app
        if (btAdapater == null) {
            btAdapater = BluetoothAdapter.getDefaultAdapter();
        }

        // Are we already connected?

        if (btSocket != null && btSocket.isConnected()) {
            return true;
        }

        // This will let you pass null if you don't want to implement the callback
        if (myCallback == null) {
            myCallback = emptyCallback;
        }

        // MAC needs to be upper case. Help the user out.
        MAC = MAC.toUpperCase();

        // Get our device
        btDevice = btAdapater.getRemoteDevice(MAC);

        // UUID for Serial Port Protocol
        UUID mUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

        // Try to connect
        try {
            btSocket = btDevice.createInsecureRfcommSocketToServiceRecord(mUUID);

        } catch (IOException e) {
            Log.d(TAG,"Failed to open the bluetooth socket...");
            // Trigger the fail method
            myCallback.doOnConnectionFailed();
            return false;
        }

        // Connect ot the socket
        try {
            btSocket.connect();
            iStream = btSocket.getInputStream();
            oStream = btSocket.getOutputStream();

            // Trigger the success event
            myCallback.doOnConnect();
        } catch (IOException e) {
            Log.d(TAG, "Failed to connect to the bluetooth socket, or open the I/O streams...");
            // Trigger the fail method
            myCallback.doOnConnectionFailed();
            return false;
        }

        // If we've made it this far, everything is good to go!
        commService = Executors.newSingleThreadExecutor();
        return true;
    }

    public boolean btDisconnect(BluetoothConnectCallback myCallback) {
        // This will let you pass null if you don't want to implement the callback
        if (myCallback == null) {
            myCallback = emptyCallback;
        }

        // This is an API 14 if statement! Won't work on older devices!
        if (btSocket != null && btSocket.isConnected()) {
            try {
                // Try and close down the I/O streams and close the socket.
                iStream.close();
                oStream.close();
                btSocket.close();
                if (commService != null && !commService.isShutdown()) {
                    commService.shutdown();
                }
            } catch (IOException e) {
                Log.d(TAG,"Had trouble properly closing down the I/O and Bluetooth socket...");
            }
        } else {
            // Return false if we weren't connected
            return false;
        }

        myCallback.doOnDisconnect();

        // Return true if we made it this far
        return true;
    }

    // Can return null! Be sure to parse for this in main app!
    // This method will also block the UI if it takes too long!
    public byte[] readData(DataReadCallback myCallback) {

        // This will let you pass null if you don't want to implement the callback
        if (myCallback == null) {
            myCallback = anotherEmptyCallback;
        }
        // Don't bother if we're not connected
        if (!btSocket.isConnected()) {
            myCallback.doOnReadFail();
            return null;
        }
        // how much data is available?
        try {
            int avail = iStream.available();
            byte[] data = new byte[avail];
            iStream.read(data, 0, avail);
            myCallback.doOnDataRead(data);
            return data;
        } catch (IOException e) {
            Log.d(TAG,"Trouble reading from device!");
            return null;
        }
    }

    public byte rawRead() {
        if (btSocket == null || !btSocket.isConnected()) {
            return 0x00;
        }
        try {
            return (byte) (iStream.read() & 0xff);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0x00;
    }

    public boolean writeData(byte[] data) {
        // Don't bother if we're not connected
        if (btSocket == null || !btSocket.isConnected()) {
            return false;
        }

        try {
            oStream.write(data);
        } catch (IOException e) {
            Log.d(TAG,"Trouble writing to device!");
        }

        return true;
    }

    /////// CALLBACKS for bluetooth stuff. You don't need to implement anything in them, but you'll probably want to
    public interface BluetoothConnectCallback {
        public void doOnConnect();
        public  void doOnConnectionFailed();
        public void doOnDisconnect();
    }



    public interface DataReadCallback {
        public void doOnDataRead(byte[] theData);
        public  void doOnReadFail();
    }


    // Empty callbacks, in case the user doesn't want to implement one (by passing null).
    private BluetoothConnectCallback emptyCallback = new BluetoothConnectCallback() {
        @Override
        public void doOnConnect() {

        }

        @Override
        public void doOnConnectionFailed() {

        }

        @Override
        public void doOnDisconnect() {

        }
    };

    private DataReadCallback anotherEmptyCallback = new DataReadCallback() {
        @Override
        public void doOnDataRead(byte[] theData) {

        }

        @Override
        public void doOnReadFail() {

        }
    };

    ////////////////////////////////////////////////////////////////////////////////////

    //////////////////// Stuff for binding to an Activity /////////////////////

    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();


    public class LocalBinder extends Binder {
        public BluetoothService getService() {
            // Return this instance so clients can access the d
            return BluetoothService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"Service bound...");
        return mBinder;
    }

    // BluetoothSocket.isCnnected was added in API 14!
    // We have the minSDK at 16, but if it is lowered, this can cause an issue!
    public boolean isConnected() {
        if (btSocket != null) {
            if (btSocket.isConnected()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
