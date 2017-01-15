package com.smart.develop.training.getting_started.interacting_with_other_apps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.smart.develop.R;

/**
 * FileName: InteractingActivity
 *
 * Des: Training
 *
 *      --Getting Started
 *
 *      --Interacting with Other Apps
 *
 * Time: 2017/1/8 上午12:04
 */
public class InteractingActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView mLV;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interacting);
        initView();
        initData();
    }

    private void initView(){
        mLV = (ListView) findViewById(R.id.interacting_lv);
        mLV.setOnItemClickListener(this);
    }

    private void initData(){
        String []dataResouce = getResources().getStringArray(R.array.interacting_menu);
        mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataResouce);
        mLV.setAdapter(mAdapter);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                skipToSpecificActivity(SendingTheUserActivity.class);
                break;
            case 1:
                skipToSpecificActivity(GettingResultActivity.class);
                break;
            case 2:
                skipToSpecificActivity(AllowOtherToStartActivity.class);
                break;
        }
    }

    /**
     * Des: 跳转到指定 Activity
     *
     * Time: 2017/1/4 下午10:30
     */
    private void skipToSpecificActivity(Class<?>cls){
        Intent intent = new Intent(this,cls);
        startActivity(intent);
    }
}
