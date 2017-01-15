package com.smart.develop.training.getting_started.building_your_first_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smart.develop.R;
/**
 * FileName: SecondActivity
 *
 * Des: Training
 *
 *      --Getting Started
 *
 *      --Building Your First App 接收数据
 *
 * Time: 2017/1/4 下午11:07
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        Intent intent = getIntent();
        String message = intent.getStringExtra(FirstActivity.TRANSFER_KEY);


        TextView textView = new TextView(this);
        textView.setTextSize(32);
        if(TextUtils.isEmpty(message)){
            message = getResources().getString(R.string.nothing);
        }
        textView.setText(message);


        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        textView.setLayoutParams(layoutParams);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_second);
        layout.addView(textView);
    }
}
