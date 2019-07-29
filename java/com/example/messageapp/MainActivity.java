package com.example.messageapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txt_pno,txt_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_pno =findViewById(R.id.phn_number);
        txt_message =findViewById(R.id.phn_message);

    }

    public  void btn_send(View view){

        int permission_check = ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);
        
        if(permission_check == PackageManager.PERMISSION_GRANTED){
            
            Mymessage();
        }
        else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);
        }

    }

    private void Mymessage() {

        String phn_no = txt_pno.getText().toString().trim();
        String phn_message = txt_message.getText().toString().trim();

        if (!txt_pno.getText().toString().equals("") || !txt_message.getText().toString().equals("")) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phn_no, null, phn_message, null, null);

            Toast.makeText(this, "Message Send", Toast.LENGTH_SHORT).show();

        }

        else {
            Toast.makeText(this,"Enter phone number and Message",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 0:
                if (grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {


                    Mymessage();

                }
                else {
                    Toast.makeText(this,"You don`t have Permission",Toast.LENGTH_SHORT).show();
                }

            break;
        }
    }
}
