package com.smart.develop.training.content_sharing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.smart.develop.R;
import com.smart.develop.training.content_sharing.sharing_file.SharingFileActivity;
import com.smart.develop.training.content_sharing.sharing_file_nfc.SharingFileNFCActivity;
import com.smart.develop.training.content_sharing.sharing_simple_data.SharingSimpleDataActivity;

/**
 * FileName: ContentSharingActivity
 *
 * Des: Training
 *
 *      --Building App with Content Sharing
 *
 *      --Sharing Simple Data
 *
 * Time: 2017/1/9 下午10:23
 */
public class ContentSharingActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView mLV;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_sharing);
        initView();
        initData();
    }

    private void initView(){
        mLV = (ListView) findViewById(R.id.content_sharing_lv);
        mLV.setOnItemClickListener(this);
    }

    private void initData(){
        String []dataResouce = getResources().getStringArray(R.array.content_sharing_menu);
        mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataResouce);
        mLV.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                skipToSpecificActivity(SharingSimpleDataActivity.class);
                break;
            case 1:
                skipToSpecificActivity(SharingFileActivity.class);
                break;
            case 2:
                skipToSpecificActivity(SharingFileNFCActivity.class);
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
