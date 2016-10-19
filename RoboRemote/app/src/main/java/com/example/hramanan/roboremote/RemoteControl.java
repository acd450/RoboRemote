package com.example.hramanan.roboremote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class RemoteControl extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_control);
    }

    public void forwardButtonOnClick(View v)
    {
        Toast.makeText(getBaseContext(), "Robot moving forward.", Toast.LENGTH_LONG).show();
    }

    public void backwardButtonOnClick(View v)
    {
        Toast.makeText(getBaseContext(), "Robot moving backward.", Toast.LENGTH_LONG).show();
    }

    public void rightButtonOnClick(View v)
    {
        Toast.makeText(getBaseContext(), "Robot turning right.", Toast.LENGTH_LONG).show();
    }

    public void leftButtonOnClick(View v)
    {
        Toast.makeText(getBaseContext(), "Robot turning left.", Toast.LENGTH_LONG).show();
    }
}
