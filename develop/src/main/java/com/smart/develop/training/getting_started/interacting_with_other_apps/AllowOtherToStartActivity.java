package com.smart.develop.training.getting_started.interacting_with_other_apps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.smart.develop.R;
/**
 * FileName: AllowOtherToStartActivity
 *
 * Des: Training
 *
 *      --Getting Started
 *
 *      --Interacting with Other Apps
 *
 *      --Allowing Other Apps to Start Your Activity
 *
 *      PS:此处的调用方式和一般的应用内部跳转方式没有任何区别
 *
 * Time: 2017/1/8 上午11:23
 */
public class AllowOtherToStartActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mReceiveShow;
    private EditText mFeedbackInput;
    private Button mFinishConfirm;
    private final static String DATA_TRANSFER_KEY = "DATA_TRANSFER_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allow_other_to_start);
        initView();
        initData();
    }

    private void initView(){
        mReceiveShow = (TextView)findViewById(R.id.receive);
        mFeedbackInput = (EditText)findViewById(R.id.feedback);
        mFinishConfirm = (Button)findViewById(R.id.finish);
        mFinishConfirm.setOnClickListener(this);
    }

    private void initData(){
        Intent intent = getIntent();
        String receiveContent = intent.getStringExtra(DATA_TRANSFER_KEY);
        mReceiveShow.setText(receiveContent);
    }

    @Override
    public void onClick(View v) {
        String feedbackContent = mFeedbackInput.getText().toString();
        Intent feedbackIntent = new Intent();
        feedbackIntent.putExtra(DATA_TRANSFER_KEY,feedbackContent);
        setResult(Activity.RESULT_OK,feedbackIntent);
        finish();
    }
}
