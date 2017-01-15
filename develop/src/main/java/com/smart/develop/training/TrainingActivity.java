package com.smart.develop.training;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.smart.develop.R;
import com.smart.develop.training.content_sharing.ContentSharingActivity;
import com.smart.develop.training.getting_started.GettingStartedActivity;

/**
 * FileName: TrainingActivity
 *
 * Des: Training
 *
 * Time: 2017/1/4 下午10:25
 */
public class TrainingActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView mTrainingLV;
    private ArrayAdapter<String> mTrainingAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        initView();
        initData();
    }

    private void initView(){
        mTrainingLV = (ListView) findViewById(R.id.training_lv);
        mTrainingLV.setOnItemClickListener(this);
    }

    private void initData(){
        String []dataResouce = getResources().getStringArray(R.array.training_menu);
        mTrainingAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataResouce);
        mTrainingLV.setAdapter(mTrainingAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                skipToSpecificActivity(GettingStartedActivity.class);
                break;
            case 1:
                skipToSpecificActivity(ContentSharingActivity.class);
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
