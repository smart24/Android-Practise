package com.smart.develop.training.getting_started.working_with_system_permissions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.smart.develop.R;

/**
 * FileName: SystemPermissionsActivity
 *
 * Des: Training
 *
 *      --Getting Started
 *
 *      --Working with System Permissions
 *
 * Time: 2017/1/8 下午3:12
 */
public class SystemPermissionsActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mReadContact;
    private final static int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0x1;
    private boolean hasShowExplanation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_permissions);
        initView();
    }

    private void initView(){
        mReadContact = (Button)findViewById(R.id.read_contact);
        mReadContact.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        checkPermissions();
    }

    private void checkPermissions(){
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS) && !hasShowExplanation) {
                snackShow(getResources().getString(R.string.prompt_message));
                hasShowExplanation = true;
            } else {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        }else{
            snackShow("PERMISSION_GRANTED");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    snackShow("PERMISSION_GRANTED");
                } else {
                    snackShow("PERMISSION_DENIED");
                }
                return;

        }
    }

    /**
     * Des: Show Message
     *
     * Time: 2017/1/8 下午3:34
     */
    private void snackShow(String msg){
        Snackbar.make(mReadContact,msg,Snackbar.LENGTH_LONG).show();
    }
}
