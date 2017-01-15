package com.smart.develop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.smart.develop.training.TrainingActivity;

/**
 * FileName: MainActivity
 *
 * Des: Android Develop
 *
 * Time: 2017/1/4 下午9:40
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView mRootLV;
    private ArrayAdapter<String> mRootAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView(){
        mRootLV = (ListView) findViewById(R.id.root_lv);
        mRootLV.setOnItemClickListener(this);
    }

    private void initData(){
        String []dataResouce = getResources().getStringArray(R.array.root_menu);
        mRootAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataResouce);
        mRootLV.setAdapter(mRootAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                skipToSpecificActivity(TrainingActivity.class);
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
