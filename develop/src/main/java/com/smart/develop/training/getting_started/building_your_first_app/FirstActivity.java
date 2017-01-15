package com.smart.develop.training.getting_started.building_your_first_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.smart.develop.R;
/**
 * FileName: FirstActivity
 *
 * Des: Training
 *
 *      --Getting Started
 *
 *      --Building Your First App
 *
 * Time: 2017/1/4 下午11:07
 */
public class FirstActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mContent;
    private Button mSend;
    public static final String TRANSFER_KEY = "TRANSFER_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initView();
    }

    private void initView(){
        mContent = (EditText) findViewById(R.id.content);
        mSend = (Button) findViewById(R.id.send);
        mSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.send:
                Intent intent = new Intent(this,SecondActivity.class);
                String content = mContent.getText().toString();
                intent.putExtra(TRANSFER_KEY,content);
                startActivity(intent);
            break;
        }
    }
}
