package com.smart.develop.training.getting_started.interacting_with_other_apps;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.smart.develop.R;

;
/**
 * FileName: GettingResultActivity
 *
 * Des: Training
 *
 *      --Getting Started
 *
 *      --Interacting with Other Apps
 *
 *      --Getting a Result from an Activity
 *
 * Time: 2017/1/8 上午10:05
 */
public class GettingResultActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mContactShow;
    private Button mContactRead;
    private static final int PICK_CONTACT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_result);
        initView();
    }

    private void initView(){
        mContactShow = (TextView)findViewById(R.id.getting_contact_tv);
        mContactRead = (Button)findViewById(R.id.getting_contact_bt);
        mContactRead.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (PICK_CONTACT_REQUEST == requestCode){
            if(RESULT_OK == resultCode){
                Uri contactUri = data.getData();
                String[] projection = {Phone.DISPLAY_NAME,Phone.NUMBER};

                Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
                cursor.moveToFirst();

                String name = cursor.getString(cursor.getColumnIndex(Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));

                mContactShow.setText(name + "   " + number);
            }
        }
    }
}
