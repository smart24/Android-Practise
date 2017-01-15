package com.smart.develop.training.getting_started.interacting_with_other_apps;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.smart.develop.R;

import java.util.List;

/**
 * FileName: SendingTheUserActivity
 *
 * Des: Training
 *
 *      --Getting Started
 *
 *      --Interacting with Other Apps
 *
 *      --Sending the User to Another App
 *
 *      PS：此处通过 Intent 调用完成的功能并不需要任何权限
 *
 * Time: 2017/1/8 上午12:22
 */
public class SendingTheUserActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mDial,mMap,mWeb,mVerify,mShowChooser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending_the_user);
        initView();
    }

    private void initView(){
        mDial = (Button)findViewById(R.id.dial_a_phone);
        mMap = (Button)findViewById(R.id.view_a_map);
        mWeb = (Button)findViewById(R.id.view_a_web);
        mVerify = (Button)findViewById(R.id.verify_intent);
        mShowChooser = (Button)findViewById(R.id.show_chooser);
        mDial.setOnClickListener(this);
        mMap.setOnClickListener(this);
        mWeb.setOnClickListener(this);
        mVerify.setOnClickListener(this);
        mShowChooser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.dial_a_phone:
                dialAPhone();
            break;
            case R.id.view_a_map:
                viewAMap();
            break;
            case R.id.view_a_web:
                viewAWeb();
            break;
            case R.id.verify_intent:
                verifyIntent();
            break;
            case R.id.show_chooser:
                showChooser();
            break;
        }
    }

    /**
     * Des: Dial a Phone
     *
     * Time: 2017/1/8 上午1:02
     */
    private void dialAPhone(){
        Uri number = Uri.parse("tel:123456");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);
    }

    /**
     * Des: View a Map
     *
     * Time: 2017/1/8 上午1:07
     */
    private void viewAMap(){
        Uri location = Uri.parse("geo:34.422219,108.08364");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
        startActivity(mapIntent);
    }

    /**
     * Des: View a Web Page
     *
     * Time: 2017/1/8 上午1:09
     */
    private void viewAWeb(){
        Uri webPage = Uri.parse("https://developer.android.google.cn/index.html");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, webPage);
        startActivity(mapIntent);
    }

    /**
     * Des: Verify is there any app can response this Intent
     *
     * Time: 2017/1/8 上午1:17
     */
    private void verifyIntent(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        PackageManager packageManager = getPackageManager();
        List activities = packageManager.queryIntentActivities(intent,PackageManager.MATCH_DEFAULT_ONLY);
        boolean isIntentSafe = activities.size() > 0;
        if(isIntentSafe){
            startActivity(intent);
        }else{
            Toast.makeText(this, "UnSafe", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Des: Show a Chooser
     *
     * 这个函数和上面的唯一不同就是：上面的函数有"下次默认"选项，这个函数没有这个选项，剩下没有什么不同
     *
     * Time: 2017/1/8 上午1:26
     */
    private void showChooser(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Intent chooser = intent.createChooser(intent,getResources().getString(R.string.show_chooser));
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(chooser);
        }else{
            Toast.makeText(this, "UnSafe", Toast.LENGTH_SHORT).show();
        }
    }
}


















































