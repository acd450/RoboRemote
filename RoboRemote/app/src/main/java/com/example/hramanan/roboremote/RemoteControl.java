package com.example.hramanan.roboremote;

import android.graphics.Canvas;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RemoteControl extends AppCompatActivity {

    private Button leftBtn;
    private Button rightBtn;
    private Button upBtn;
    private Button downBtn;

    public

    protected void onCreate(Bundle savedInstanceState) {
        Canvas canv1 = new Canvas();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_control);

    }

    private void onClickLeft(View v)
    {
        Toast.makeText(this, "Rover moving left", Toast.LENGTH_SHORT).show();
    }


}


