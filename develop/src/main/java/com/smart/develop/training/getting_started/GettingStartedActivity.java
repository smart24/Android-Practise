package com.smart.develop.training.getting_started;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.smart.develop.R;
import com.smart.develop.training.getting_started.building_dynamic_ui.DynamicUIActivity;
import com.smart.develop.training.getting_started.building_your_first_app.FirstActivity;
import com.smart.develop.training.getting_started.interacting_with_other_apps.InteractingActivity;
import com.smart.develop.training.getting_started.saving_data.SavingDataActivity;
import com.smart.develop.training.getting_started.supporting_different_devices.AdaptationActivity;
import com.smart.develop.training.getting_started.working_with_system_permissions.SystemPermissionsActivity;

/**
 * FileName: GettingStartedActivity
 *
 * Des:  Training
 *
 *      --Getting Started
 *
 * Time: 2017/1/4 下午11:16
 */
public class GettingStartedActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView mGettingStartedLV;
    private ArrayAdapter<String> mGettingStartedAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started);
        initView();
        initData();
    }

    private void initView(){
        mGettingStartedLV = (ListView) findViewById(R.id.getting_started_lv);
        mGettingStartedLV.setOnItemClickListener(this);
    }

    private void initData(){
        String []dataResouce = getResources().getStringArray(R.array.getting_started_menu);
        mGettingStartedAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataResouce);
        mGettingStartedLV.setAdapter(mGettingStartedAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                skipToSpecificActivity(FirstActivity.class);
                break;
            case 1:
                skipToSpecificActivity(AdaptationActivity.class);
                break;
            case 2:
                skipToSpecificActivity(DynamicUIActivity.class);
                break;
            case 3:
                skipToSpecificActivity(SavingDataActivity.class);
                break;
            case 4:
                skipToSpecificActivity(InteractingActivity.class);
                break;
            case 5:
                skipToSpecificActivity(SystemPermissionsActivity.class);
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
