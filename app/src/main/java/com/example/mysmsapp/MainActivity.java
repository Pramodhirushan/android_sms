package com.example.mysmsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
//import android.app.DownloadManager;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    EditText textMsg, textPhoneNo;
    String Msg, PhoneNo;
    Button Send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
            }

        }
        textMsg = findViewById(R.id.textMsg);
        textPhoneNo = findViewById(R.id.textPhoneNo);
        Send = findViewById(R.id.send);

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SendTextmessage();
            }
        });

    }

    public void onRequestpermisstionResult(int requestCode, String permission[],int[] grantResults)
    {
        switch(requestCode)
        {
            case MY_PERMISSIONS_REQUEST_SEND_SMS:
            {
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this,"permitted..!",Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(this,"not permitted..!",Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    protected void SendTextmessage(){
        Msg = textMsg.getText().toString();
        PhoneNo = textPhoneNo.getText().toString();

        SmsManager smsManager= SmsManager.getDefault();
        smsManager.sendTextMessage(PhoneNo,null,Msg,null,null);
        Toast.makeText(this,"sent..!",Toast.LENGTH_LONG).show();
    }
}



